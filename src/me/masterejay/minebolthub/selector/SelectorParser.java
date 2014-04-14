package me.masterejay.minebolthub.selector;

import me.masterejay.minebolthub.Hub;
import me.masterejay.minebolthub.utils.InvUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class SelectorParser {

	static List<ServerItem> items = new ArrayList<ServerItem>();


	public static void parseSelector(){
		List<String> entries = Hub.getInstance().getConfig().getStringList("picker");
		for (String e : entries){
			String[] args = e.split(",");
			int slot = Integer.parseInt(args[0]);
			ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])));
			String name = args[2];
			ItemStack namedItem = InvUtils.setDisplayName(item, name);
			ItemStack finishItem = InvUtils.setLore(namedItem, ChatColor.RED + "Click to connect!");
			String command = args[3];
			items.add(new ServerItem(finishItem, command, slot));
		}
	}

	public static List<ServerItem> getItems(){
		return items;
	}

	public static Inventory buildInv(){
		Inventory i = Bukkit.createInventory(null, 9, "Servers");
		for (ServerItem it : items){
		   i.setItem(it.getSlot(), it.getItem());
		}
		return i;
	}

	public static ServerItem getItemFromItemStack(ItemStack itemStack){
		for (ServerItem i : items){
			if (i.getItem().isSimilar(itemStack)){
				return i;
			}
		}
		return null;
	}
}
