package kata.gildedrose.refactored;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kata.gildedrose.itemstransformers.ItemMutator;
import kata.gildedrose.itemstransformers.ItemMutatorFactory;
import kata.gildedrose.legacy.Item;


public class GildedRose {
	
	private List<Item> items = null;
	
	public GildedRose(Item... item) {
		this(Arrays.asList(item));
	}
	
	public GildedRose(List<Item> items) {
		this.items = items;
	}
	
    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
        	ItemMutator mutator = ItemMutatorFactory.getMutatorOf(items.get(i));
        	mutator.mutate(items.get(i));	
        }
    }

	public List<String> getItemsState() {
		List<String> states = new ArrayList<>();
		for(Item item : items) {
    		System.out.println(String.format("%s has %s as quality and should be sellIn %s", item.name, item.quality, item.sellIn));
    		states.add(String.format("%s has %s as quality and should be sellIn %s", item.name, item.quality, item.sellIn));
		}
		System.out.println("------------------------------------------------------------------------------------------------------");
		return states;
	}

}