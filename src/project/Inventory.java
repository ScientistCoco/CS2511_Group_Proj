package project;

import java.util.HashMap;

public class Inventory {
	private HashMap<Item, Long> items;
	
	public Inventory() {
		items = new HashMap<Item, Long>();
	}
	
	public boolean addItem(Item it) {
		//check sword
		if (it instanceof Sword) {
			if (items.containsKey(it)) {
				return false;
			}
		}
		
		if(!items.containsKey(it)) {
			items.put(it, new Long(1));
		} else {
			items.put(it, new Long(items.get(it).longValue() + 1));
		}
		return true;
	}
	
	
	/**
	 * @pre items contain it
	 * @param it
	 */
	public boolean removeItem(Item it) {
		if (items.get(it).longValue() > 1) {
			items.put(it, new Long(items.get(it).longValue() - 1));
			return true;
		}
		if (items.get(it).longValue() == 1) {
			items.remove(it);
			return true;
		}
		return false;
	}
	
	public Item findItem(String name) {
		for (Item it : items.keySet()) {
			if (it.getItemName().equals(name)) {
				return it;
			}
		}
		return null;
	}
	
	public void displayItems() {
		for (Item it : items.keySet()) {
			System.out.println("Item name: " + it.getItemName() + " Item number: " + items.get(it));
		}
	}
	
	/**
	 * This method is called when the player wants to use an item in their inventory
	 * @param player
	 * @param itemName
	 */
	public void useItem(Player player, String itemName) {
		Item item = findItem(itemName);
		if (item != null) {item.useItem(player);}
		else {
			System.out.println("Item does not exist in your inventory");
		}
	}
}
