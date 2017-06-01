package kata.gildedrose.itemstransformers;

import kata.gildedrose.legacy.Item;

public class AgedBrieMutator implements ItemMutator {

	@Override
	public void mutate(Item item) {
		updateSellInOf(item);
		increaseQualityOf(item);
    	if (item.getSellIn() < 0) {
    		increaseQualityOf(item);
    	}
	}
}
