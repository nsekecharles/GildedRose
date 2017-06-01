package kata.gildedrose.itemstransformers;

import kata.gildedrose.legacy.Item;

public class ConjuredMutator implements ItemMutator {
	
	/**
	 * Conjured items quality decrease twice faster than other items
	 */
	@Override
	public void decreaseQualityOf(Item item) {
		ItemMutator.super.decreaseQualityOf(item);
		ItemMutator.super.decreaseQualityOf(item);
	}
}
