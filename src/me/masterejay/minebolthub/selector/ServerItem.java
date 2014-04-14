package me.masterejay.minebolthub.selector;

import org.bukkit.inventory.ItemStack;

/**
 * @author MasterEjay
 */
public class ServerItem {

	ItemStack item;
	int slot;
	String command;

	public ServerItem(ItemStack item,String command, int slot){
		this.item=item;
		this.command=command;
		this.slot = slot;
	}

	public ItemStack getItem(){
		return item;
	}

	public String getCommand(){
		return command;
	}

	public int getSlot(){
		return slot;
	}
}
