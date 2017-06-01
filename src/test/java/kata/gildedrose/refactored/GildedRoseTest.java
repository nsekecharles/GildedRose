package kata.gildedrose.refactored;


import static kata.gildedrose.utils.GidedRoseBuilder.aGildedRose;
import static kata.gildedrose.utils.ItemBuilder.anItem;
import static kata.gildedrose.utils.QualityAssert.item;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kata.gildedrose.legacy.Item;
import kata.gildedrose.utils.GidedRoseBuilder;
import kata.gildedrose.utils.ItemBuilder;


public class GildedRoseTest {
	
	private static ItemBuilder dextirityBuilder = anItem(ItemTypes._5_DEXTERITY_VEST);
	private static ItemBuilder elixirBuilder = anItem(ItemTypes.ELIXIR_OF_THE_MONGOOSE);
	private static ItemBuilder sulfurasBuilder = anItem(ItemTypes.SULFURAS_HAND_OF_RAGNAROS);
	private static ItemBuilder agedBrieBuilder = anItem(ItemTypes.AGED_BRIE);
	private static ItemBuilder backstageBuilder = anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);
	
	@Test
	public void 
	items_quality_should_decrease_every_day() throws Exception {

		// Given
		Item dextirity = dextirityBuilder.withSellIn(12).withQuality(10).build();
		Item elixir = elixirBuilder.withSellIn(12).withQuality(10).build();
		GildedRose gildedRose = aGildedRose()
				.withItem(dextirity)
				.withItem(elixir)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(dextirity).qualityShouldBe(9);
		item(elixir).qualityShouldBe(9);
	}
	
	@Test
	public void 
	sulfuras_quality_and_sellby_should_never_change() throws Exception {

		// Given
		Item sulfuras = sulfurasBuilder.withSellIn(0).withQuality(80).build();
		GildedRose gildedRose = aGildedRose()
				.withItem(sulfuras)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(sulfuras).qualityShouldBe(80);
		item(sulfuras).sellByShouldBe(0);
	}
	
	@Test
	public void
	items_should_not_have_a_negative_quality() {
		
		// Given
		Item sulfuras = sulfurasBuilder.withSellIn(12).withQuality(0).build();
		Item elixir = elixirBuilder.withSellIn(12).withQuality(0).build();
		Item dextirity = dextirityBuilder.withSellIn(12).withQuality(0).build();
		GildedRose gildedRose = aGildedRose()
				.withItem(dextirity)
				.withItem(elixir)
				.withItem(sulfuras)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(sulfuras).qualityShouldBe(0);
		item(elixir).qualityShouldBe(0);
		item(dextirity).qualityShouldBe(0);
	}
	
	@Test
	public void
	items_quality_max_value_should_50() {

		// Given
		Item agedBrie = agedBrieBuilder.withQuality(49).withSellIn(12).build();
		Item backStage = backstageBuilder.withQuality(50).withSellIn(12).build();
		GildedRose gildedRose = aGildedRose()
				.withItem(agedBrie)
				.withItem(backStage)
				.build();
		
		// When
		gildedRose.updateQuality();
		gildedRose.updateQuality();
		
		// Then
		item(agedBrie).qualityShouldBe(50);
		item(backStage).qualityShouldBe(50);
	}
	
	@Test
	public void
	quantity_should_decrease_twice_when_sell_by_date_has_passed() {
		
		// Given
		Item dexterity = dextirityBuilder.withQuality(10).withSellIn(0).build();
		Item elixir = elixirBuilder.withQuality(10).withSellIn(0).build();
		Item conjured = ItemBuilder.anItem(ItemTypes.CONJURED_MANA_CAKE)
				.withQuality(10)
				.withSellIn(0)
				.build();
		GildedRose gildedRose = aGildedRose()
				.withItem(dexterity)
				.withItem(elixir)
				.withItem(conjured)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(elixir).qualityShouldBe(8);
		item(dexterity).qualityShouldBe(8);
		item(conjured).qualityShouldBe(6);
	}

	@Test
	public void
	backstage_and_aged_brie_should_increase_in_quality_every_day() {
		
		// Given
		Item agedBrie = agedBrieBuilder
				.withQuality(10)
				.withSellIn(11)
				.build();
		Item backstage = backstageBuilder
				.withQuality(10)
				.withSellIn(11)
				.build();
		GildedRose gildedRose = aGildedRose()
				.withItem(agedBrie)
				.withItem(backstage)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(agedBrie).qualityShouldBe(11);
		item(backstage).qualityShouldBe(11);
	}
	
	@Test
	public void
	items_quality_max_value_should_be_50() {
		
		// Given
		GidedRoseBuilder grBuilder = aGildedRose();
		List<Item> allItems = new ArrayList<>();
		for(ItemTypes type : ItemTypes.values()) {
			Item item = anItem(type).withQuality(40).withSellIn(30).build();
			allItems.add(item);
			grBuilder.withItem(item);
		}
		
		// When
		GildedRose gildedRose = grBuilder.build();
		for(int i = 0; i < 20; i++) {
			gildedRose.updateQuality();
		}
		
		// Then
		for(int i = 0; i < allItems.size(); i++) {
			org.junit.Assert.assertTrue(allItems.get(i).getQuality() <= 50);
		}

	}
	
	@Test
	public void
	backstage_quality_should_increases_by_2_when_it_remains_10_to_6_days() {
		
		// Given
		Item backstageSellIn10Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(10)
				.build();
		Item backstageSellIn9Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(9)
				.build();
		Item backstageSellIn8Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(8)
				.build();
		Item backstageSellIn7Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(6)
				.build();
		Item backstageSellIn6Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(6)
				.build();
		
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(backstageSellIn10Day)
				.withItem(backstageSellIn9Day)
				.withItem(backstageSellIn8Day)
				.withItem(backstageSellIn7Day)
				.withItem(backstageSellIn6Day)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(backstageSellIn10Day).qualityShouldBe(12);
		item(backstageSellIn9Day).qualityShouldBe(12);
		item(backstageSellIn8Day).qualityShouldBe(12);
		item(backstageSellIn7Day).qualityShouldBe(12);
		item(backstageSellIn6Day).qualityShouldBe(12);
	}
	
	@Test
	public void
	backstage_quality_should_increases_by_3_when_it_remains_5_to_1_days() {
	
		// Given
		Item backstageSellIn1Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(1)
				.build();
		Item backstageSellIn2Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(2)
				.build();
		Item backstageSellIn3Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(3)
				.build();
		Item backstageSellIn4Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(4)
				.build();
		Item backstageSellIn5Day = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(5)
				.build();
		
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(backstageSellIn1Day)
				.withItem(backstageSellIn2Day)
				.withItem(backstageSellIn3Day)
				.withItem(backstageSellIn4Day)
				.withItem(backstageSellIn5Day)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(backstageSellIn1Day).qualityShouldBe(13);
		item(backstageSellIn2Day).qualityShouldBe(13);
		item(backstageSellIn3Day).qualityShouldBe(13);
		item(backstageSellIn4Day).qualityShouldBe(13);
		item(backstageSellIn5Day).qualityShouldBe(13);
	}
	
	@Test
	public void
	backstage_quality_should_drop_to_0_after_the_concert() {
	
		// Given
		Item backstage = ItemBuilder
				.anItem(ItemTypes.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(0)
				.build();
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(backstage)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		item(backstage).qualityShouldBe(0);
		
	}
	
	@Test
	public void 
	should_increase_aged_brie_quality_by_2_when_sellIn_passed() {
		
		// Given
		Item agedBrie = ItemBuilder.anItem(ItemTypes.AGED_BRIE)
				.withQuality(12)
				.withSellIn(0)
				.build();
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(agedBrie)
				.build();

		// When
		gildedRose.updateQuality();
		
		// Then
		item(agedBrie).qualityShouldBe(14);
	}
	
	@Test
	public void
	conjured_items_quality_should_degrade_twice_as_fast_as_normal_items() {
		
		// Given
		Item conjured = ItemBuilder.anItem(ItemTypes.CONJURED_MANA_CAKE)
				.withQuality(6)
				.withSellIn(3)
				.build();
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(conjured)
				.build();
		
		// When
		gildedRose.updateQuality();
		gildedRose.updateQuality();
		gildedRose.updateQuality();
		
		// Then
		item(conjured).qualityIsValid();
		item(conjured).qualityShouldBe(0);
	}
}
