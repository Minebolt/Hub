package me.masterejay.minebolthub.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MasterEjay
 */
public class InvUtils {

	public static ItemStack setDisplayName(ItemStack stack, String displayName) {
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(displayName);
		stack.setItemMeta(meta);
		return stack;
	}


	public static ItemStack setLore(ItemStack stack, String... lore){
		List<String> strings = new ArrayList<String>();
		Collections.addAll(strings, lore);
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(strings);
		stack.setItemMeta(meta);
		return stack;
	}

}
