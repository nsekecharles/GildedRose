
public interface ItemMutator {

	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
	public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	
	default void mutate(Item item) {
    	updateSellIn(item);
    	decreaseQualityOfItemAtIndex(item);
    	if (item.getSellIn() < 0) {
    		decreaseQualityOfItemAtIndex(item);
    	}
	}
	
	default void decreaseQualityOfItemAtIndex(Item item) {
		if (item.getQuality() > 0) {
			item.setQuality(item.getQuality() - 1);
		}
	}

	default void increaseQualityOfItemAtIndex(Item item) {
		if(item.getQuality() < 50) {
		    item.setQuality(item.getQuality() + 1);
		}
	}
	
	default void setQualityToZero(Item item) {
		item.setQuality(0);
	}
	
	default void updateSellIn(Item item) {
		item.setSellIn(item.getSellIn() - 1);
	}

}
