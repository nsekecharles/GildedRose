import java.util.ArrayList;
import java.util.List;

public class DegradationHistory {

	public List<String> getHistory(int duration) {
		
		List<String> lines = new ArrayList<String>();
		List<Item>  items = new ArrayList<Item>();
        items.add(new Item(GildedRose._5_DEXTERITY_VEST, 10, 20));
        items.add(new Item(GildedRose.AGED_BRIE, 2, 0));
        items.add(new Item(GildedRose.ELIXIR_OF_THE_MONGOOSE, 5, 7));
        items.add(new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 0, 80));
        items.add(new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
	    items.add(new Item("Conjured Mana Cake", 3, 6));
	        
        GildedRose gildedRose = new GildedRose(items);
        lines.addAll(gildedRose.getItemsState());
		for(int i = 0; i < duration; i++) {
			gildedRose.updateQuality();
			lines.addAll(gildedRose.getItemsState());
		}
		return lines;
	}
	
	public List<String> getHistoryLegacy(int duration) {
		
		List<Item> items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
        
        LegacyGildedRose legacyGildedRose = new LegacyGildedRose(items);
        List<String> lines = new ArrayList<String>();
        lines.addAll(legacyGildedRose.getItemsState());
        for(int i = 0; i < duration; i++) {
        	legacyGildedRose.updateQuality();
			lines.addAll(legacyGildedRose.getItemsState());
        }
        
        return lines;
	}
}
