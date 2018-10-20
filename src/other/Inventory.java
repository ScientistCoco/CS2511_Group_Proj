package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import items.Item;
import items.Key;
import items.Sword;
import javafx.scene.image.ImageView;
import player.Player;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public boolean addItem(Item it) {
		//check sword
		if (it instanceof Sword && this.findItem("sword") != null) return false;
		//assuming player can't add another key once player has it
		if (it instanceof Key && this.findItem("Key") != null) return false;
		return items.add(it);
	}
	
	
	/**
	 * @pre items contain it
	 * @param it
	 */
	public boolean removeItem(Item it) {
		return items.remove(it);
	}
	
	public Item findItem(String name) {
		for (Item it : items) {
			if (it.getItemName().equals(name)) {
				return it;
			}
		}
		return null;
	}
	
	/**
	 * Finds the item associated with the given image
	 * @param image
	 * @return
	 */
	public Item findItemByImage(ImageView image) {
		for (Item it : items) {
			if (it.getEntityIcon().equals(image)) {
				return it;
			}
		}
		return null;
	}
	
	public int findItemAmount(String name) {
		int counter = 0;
		for (Item it : items) {
			if (it.getItemName().equals(name)) {
				counter++ ;
			}
		}
		return counter;
	}
	
	public void displayItems() {
		for (Item it : items) {
			System.out.println("Item name: " + it.getItemName() + "\t" + "Description: " + it.getDescription());
		}
	}
	
	public ArrayList<ImageView> getInventoryItems() {		
		ArrayList<ImageView> invItems = new ArrayList<ImageView>();
		this.sortInventory();
		for (Item item : items) {
			invItems.add(item.getEntityIcon());
		}
		return invItems;
		//return this.items;
	}
	
	/**
	 * This sorts the inventory by the item name - alphabetically
	 */
	public void sortInventory() {
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item i1, Item i2) {
				return i1.getItemName().compareTo(i2.getItemName());
			}			
		});
	}
	
	/**
	 * This method is called when the player wants to use an item in their inventory
	 * @param player
	 * @param itemName
	 */
	public String useItem(Player player, String itemName) {
		Item item = findItem(itemName);
		
		if (item != null) {		// Player is able to use item. Remove item from players inventory
			removeItem(item);
			return item.useItem(player);
		}
		else {
			System.out.println("Item does not exist in your inventory");
		}
		return null;
	}
}
