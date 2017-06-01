package kata.gildedrose.utils;

import kata.gildedrose.legacy.Item;
import kata.gildedrose.refactored.ItemTypes;

public  class ItemBuilder {
	private String name;
	private int quality = 0;
	private int sellIn = 0;
	
	private ItemBuilder(String name) {
		this.name = name;
	}
	
	public static ItemBuilder anItem(ItemTypes itemType) {
		return new ItemBuilder(itemType.getItemName());
	}
	
	public ItemBuilder withQuality(int quality) {
		this.quality = quality;
		return this;
	}
	
	public ItemBuilder withSellIn(int sellIn) {
		this.sellIn = sellIn;
		return this;
	}
	
	public Item build() {
		return new Item(name, sellIn, quality);
	}
}