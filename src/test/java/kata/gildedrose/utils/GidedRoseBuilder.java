package kata.gildedrose.utils;

import java.util.ArrayList;
import java.util.List;

import kata.gildedrose.legacy.Item;
import kata.gildedrose.refactored.GildedRose;

public class GidedRoseBuilder {
	
	private List<Item> items = new ArrayList<>();

	public static GidedRoseBuilder aGildedRose() {
		return new GidedRoseBuilder();
	}

	public GidedRoseBuilder withItem(Item item) {
		
		items.add(item);
		
		return this;
	}
	
	public GildedRose build() {
		return new GildedRose(items.toArray(new Item[items.size()]));
	}
	
}