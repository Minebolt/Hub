package me.masterejay.minebolthub.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.masterejay.minebolthub.Hub;
import me.masterejay.minebolthub.hider.HideManager;
import me.masterejay.minebolthub.selector.SelectorParser;
import me.masterejay.minebolthub.selector.ServerItem;
import me.masterejay.minebolthub.utils.InvUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author MasterEjay
 */
public class InvListener implements Listener{

	@EventHandler
	public void onInvClick(InventoryClickEvent event){
		event.setCancelled(true);
		if (event.getInventory().getName().equals("Servers")){
			ServerItem item = SelectorParser.getItemFromItemStack(event.getCurrentItem());
			if (item != null){
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF(item.getCommand());
				Player p = (Player) event.getWhoClicked();
				p.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
				event.getWhoClicked().closeInventory();
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if (event.getItem() != null){
			if (event.getItem().getType() == Material.COMPASS){
				event.getPlayer().openInventory(SelectorParser.buildInv());
			}
			else if (event.getItem().getType() == Material.BLAZE_ROD){
				boolean success = HideManager.onHide(event.getPlayer());
				if (success){
					ItemStack i = new ItemStack(Material.STICK);
					event.getPlayer().sendMessage(ChatColor.GREEN + "You have hidden all players!");
					event.getPlayer().getInventory().setItem(4, InvUtils.setDisplayName(i, ChatColor.YELLOW + "Show players!"));
				}

			}
			else if (event.getItem().getType() == Material.STICK){
				boolean success = HideManager.onUnhide(event.getPlayer());
				if (success){
					ItemStack i = new ItemStack(Material.BLAZE_ROD);
					event.getPlayer().sendMessage(ChatColor.GREEN + "You have unhidden all players!");
					event.getPlayer().getInventory().setItem(4, InvUtils.setDisplayName(i, ChatColor.YELLOW + "Hide players!"));
				}

			}
		}

	}


}
