package me.masterejay.minebolthub;

import me.masterejay.minebolthub.book.BookHandler;
import me.masterejay.minebolthub.listeners.ConnectionListener;
import me.masterejay.minebolthub.listeners.InvListener;
import me.masterejay.minebolthub.listeners.WeatherListener;
import me.masterejay.minebolthub.selector.SelectorParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author MasterEjay
 */
public class Hub extends JavaPlugin {

	static Hub instance;

	@Override
	public void onEnable(){
		instance = this;
		File configFile = new File(this.getDataFolder(), "config.yml");
		if (!configFile.exists()){
			saveDefaultConfig();
		}
		getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		getServer().getPluginManager().registerEvents(new InvListener(), this);
		getServer().getPluginManager().registerEvents(new WeatherListener(), this);
		SelectorParser.parseSelector();
		BookHandler.parseBook();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	public static Hub getInstance(){
		return instance;
	}


}
