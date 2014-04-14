package me.masterejay.minebolthub.listeners;

import me.masterejay.minebolthub.Hub;
import me.masterejay.minebolthub.book.BookHandler;
import me.masterejay.minebolthub.hider.HideManager;
import me.masterejay.minebolthub.utils.InvUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author MasterEjay
 */
public class ConnectionListener implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		String[] coords = Hub.getInstance().getConfig().getString("spawn").split(",");
		event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), Double.parseDouble(coords[0]),
				Double.parseDouble(coords[1]),  Double.parseDouble(coords[2])));
		event.getPlayer().getInventory().clear();
		Player p = event.getPlayer();
		ItemStack br = new ItemStack(Material.BLAZE_ROD);
		ItemStack compass = new ItemStack(Material.COMPASS);
		p.getInventory().setItem(4, InvUtils.setDisplayName(br, ChatColor.YELLOW + "Hide Players"));
		p.getInventory().setItem(0, InvUtils.setDisplayName(compass, ChatColor.RED + "Server Picker"));
		p.getInventory().setItem(8, BookHandler.getBook());
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999 * 99, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999 * 9999, 1));
		for (Player p1 : HideManager.getToggledHide()){
			p1.hidePlayer(p);
		}
	}
}
