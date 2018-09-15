package other;

import java.util.ArrayList;

import items.Item;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public boolean addItem(Item it) {
		//check sword
		if (this.findItem("sword") != null) return false;
		//assuming player can't add another key once player has it
		if (this.findItem("Key") != null) return false;
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
	
	/**
	 * This method is called when the player wants to use an item in their inventory
	 * @param player
	 * @param itemName
	 */
	public void useItem(Player player, String itemName) {
		Item item = findItem(itemName);
		
		if (item != null) {		// Player is able to use item. Remove item from players inventory
			removeItem(item);
			item.useItem(player);
		}
		else {
			System.out.println("Item does not exist in your inventory");
		}
	}
}
