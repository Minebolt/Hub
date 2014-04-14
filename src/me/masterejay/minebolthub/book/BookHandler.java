package me.masterejay.minebolthub.book;

import me.masterejay.minebolthub.Hub;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @author MasterEjay
 */
public class BookHandler {

	static ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

	public static void parseBook(){
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setDisplayName(Hub.getInstance().getConfig().getString("book.title"));
		meta.setAuthor(Hub.getInstance().getConfig().getString("book.author"));
		meta.setPages(Hub.getInstance().getConfig().getStringList("book.pages"));
		book.setItemMeta(meta);
	}

	public static ItemStack getBook(){
		return book;
	}
}
