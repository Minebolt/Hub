package me.masterejay.minebolthub.hider;

import me.masterejay.minebolthub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class HideManager {

	static List<Player> toggledHide = new ArrayList<Player>();
	static List<String> timeout = new ArrayList<String>();

	public static boolean onHide(final Player p){
		if (timeout.contains(p.getName())){
			p.sendMessage(ChatColor.RED + "You can't use that yet!");
			return false;
		}
		if (toggledHide.contains(p.getName())){
			return false;
		}
		toggledHide.add(p);
		timeout.add(p.getName());
		for (Player p1 : Bukkit.getOnlinePlayers()){
			if (p != p1){
				p.hidePlayer(p1);
			}
		}
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Hub.getInstance(),new Runnable(){
			@Override
			public void run(){
				timeout.remove(p.getName());
			}
		},20*10);
		return true;
	}

	public static boolean onUnhide(final Player p) {
		if (timeout.contains(p.getName())){
			p.sendMessage(ChatColor.RED + "You can't use that yet!");
			return false;
		}
		if (!toggledHide.contains(p)){
			return false;
		}
		toggledHide.remove(p);
		for (Player p1 : Bukkit.getOnlinePlayers()){
			if (p != p1){
				p.showPlayer(p1);
			}
		}
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Hub.getInstance(), new Runnable(){
			@Override
			public void run(){
				timeout.remove(p.getName());
			}
		}, 20 * 10);
		return true;
	}

	public static List<Player> getToggledHide(){
		return toggledHide;
	}
}
