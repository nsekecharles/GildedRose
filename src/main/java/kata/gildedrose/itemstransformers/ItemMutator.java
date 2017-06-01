package kata.gildedrose.itemstransformers;

import kata.gildedrose.legacy.Item;

public interface ItemMutator {
	
	default void mutate(Item item) {
    	updateSellInOf(item);
    	decreaseQualityOf(item);
    	if (item.getSellIn() < 0) {
    		decreaseQualityOf(item);
    	}
	}
	
	default void decreaseQualityOf(Item item) {
		if (item.getQuality() > 0) {
			item.setQuality(item.getQuality() - 1);
		}
	}

	default void increaseQualityOf(Item item) {
		if(item.getQuality() < 50) {
		    item.setQuality(item.getQuality() + 1);
		}
	}
	
	default void setQualityToZeroFor(Item item) {
		item.setQuality(0);
	}
	
	default void updateSellInOf(Item item) {
		item.setSellIn(item.getSellIn() - 1);
	}

}
