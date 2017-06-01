package kata.gildedrose.itemstransformers;

import kata.gildedrose.legacy.Item;

public class BackStageMutator implements ItemMutator {

	@Override
	public void mutate(Item item) {
		updateSellInOf(item);
		if (item.getSellIn() < 0) {
        	setQualityToZeroFor(item);
        } else {
        	increaseQualityOf(item);
            if (item.getSellIn() + 1 < 11) {
            	increaseQualityOf(item);
            }
            if (item.getSellIn() + 1 < 6) {
            	increaseQualityOf(item);
            }
        }
	}
}
