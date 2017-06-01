package kata.gildedrose.itemstransformers;

import kata.gildedrose.legacy.Item;
import kata.gildedrose.refactored.ItemTypes;

public class ItemMutatorFactory {

	public static ItemMutator getMutatorOf(Item item) {
		if(ItemTypes.AGED_BRIE.getItemName().equals(item.getName())) {
			return new AgedBrieMutator();
		} else if(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.getItemName().equals(item.getName())) {
			return new BackStageMutator();
		} else if(ItemTypes.SULFURAS_HAND_OF_RAGNAROS.getItemName().equals(item.getName())) {
			return new SulfurasMutator();
		} else if(ItemTypes.CONJURED_MANA_CAKE.getItemName().equals(item.getName())) {
			return new ConjuredMutator();
		} else {
			return new DefaultMutator();
		}
	}
}
