package me.masterejay.minebolthub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author MasterEjay
 */
public class WeatherListener implements Listener{

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event){
		event.setCancelled(true);
	}
}
