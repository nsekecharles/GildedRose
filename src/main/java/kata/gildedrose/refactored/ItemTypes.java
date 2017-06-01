package kata.gildedrose.refactored;

public enum ItemTypes {

	BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT("Backstage passes to a TAFKAL80ETC concert"),
	SULFURAS_HAND_OF_RAGNAROS("Sulfuras, Hand of Ragnaros"),
	ELIXIR_OF_THE_MONGOOSE("Elixir of the Mongoose"),
	AGED_BRIE("Aged Brie"),
	_5_DEXTERITY_VEST("+5 Dexterity Vest"),
	CONJURED_MANA_CAKE("Conjured Mana Cake");
	
	private String name;
	private ItemTypes(String name) {
		this.name = name;
	}
	
	public String getItemName() {
		return name;
	}
}
