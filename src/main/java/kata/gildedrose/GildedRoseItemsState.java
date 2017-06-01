package kata.gildedrose;
import java.util.ArrayList;
import java.util.List;

import kata.gildedrose.legacy.Item;
import kata.gildedrose.refactored.ItemTypes;
import kata.gildedrose.legacy.GildedRose;

public class GildedRoseItemsState {

	public List<String> getRefactoredGildedRoseItemsStateOn(int days) {
		
		List<String> lines = new ArrayList<String>();
		List<Item>  items = new ArrayList<Item>();
        items.add(new Item(ItemTypes._5_DEXTERITY_VEST.getItemName(), 10, 20));
        items.add(new Item(ItemTypes.AGED_BRIE.getItemName(), 2, 0));
        items.add(new Item(ItemTypes.ELIXIR_OF_THE_MONGOOSE.getItemName(), 5, 7));
        items.add(new Item(ItemTypes.SULFURAS_HAND_OF_RAGNAROS.getItemName(), 0, 80));
        items.add(new Item(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.getItemName(), 15, 20));
	    //items.add(new Item(ItemTypes.CONJURED_MANA_CAKE.getItemName(), 3, 6));
	        
        kata.gildedrose.refactored.GildedRose gildedRose = new kata.gildedrose.refactored.GildedRose(items);
        lines.addAll(gildedRose.getItemsState());
		for(int i = 0; i < days; i++) {
			gildedRose.updateQuality();
			lines.addAll(gildedRose.getItemsState());
		}
		return lines;
	}
	
	public List<String> getLegacyGildedRoseItemsStateOn(int days) {
		
		List<Item> items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        //items.add(new Item("Conjured Mana Cake", 3, 6));
        
        GildedRose legacyGildedRose = new GildedRose(items);
        List<String> lines = new ArrayList<String>();
        lines.addAll(legacyGildedRose.getItemsState());
        for(int i = 0; i < days; i++) {
        	legacyGildedRose.updateQuality();
			lines.addAll(legacyGildedRose.getItemsState());
        }
        
        return lines;
	}
}
