import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class GildedRoseTest {
	private static ItemBuilder dextirityBuilder = ItemBuilder
	.anItem(GildedRose._5_DEXTERITY_VEST);
	private static ItemBuilder elixirBuilder = ItemBuilder
			.anItem(GildedRose.ELIXIR_OF_THE_MONGOOSE);
	private static ItemBuilder sulfurasBuilder = ItemBuilder
			.anItem(GildedRose.SULFURAS_HAND_OF_RAGNAROS);
	private static ItemBuilder agedBrieBuilder = ItemBuilder
			.anItem(GildedRose.AGED_BRIE);
	private static ItemBuilder backstageBuilder = ItemBuilder
			.anItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);
	
	@Test //TODO à mettre à jour
	public void 
	items_quality_should_decrease_every_day() throws Exception {

		// Given
		Item dextirity = dextirityBuilder.withSellIn(12).withQuality(10).build();
		Item elixir = elixirBuilder.withSellIn(12).withQuality(10).build();
		GildedRose gildedRose = GidedRoseBuilder
				.aGildedRose()
				.withItem(dextirity)
				.withItem(elixir)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(dextirity).qualityShouldBe(9);
		QualityAssert.item(elixir).qualityShouldBe(9);
	}
	
	@Test
	public void 
	sulfuras_quality_and_sellby_should_never_change() throws Exception {

		// Given
		Item sulfuras = sulfurasBuilder.withSellIn(0).withQuality(80).build();
		GildedRose gildedRose = GidedRoseBuilder
				.aGildedRose()
				.withItem(sulfuras)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(sulfuras).qualityShouldBe(80);
		QualityAssert.item(sulfuras).sellByShouldBe(0);
	}
	
	@Test //TODO à mettre à jour
	public void
	items_should_not_have_a_negative_quality() {
		
		// Given
		Item sulfuras = sulfurasBuilder.withSellIn(12).withQuality(0).build();
		Item elixir = elixirBuilder.withSellIn(12).withQuality(0).build();
		Item dextirity = dextirityBuilder.withSellIn(12).withQuality(0).build();
		GildedRose gildedRose = GidedRoseBuilder
				.aGildedRose()
				.withItem(dextirity)
				.withItem(elixir)
				.withItem(sulfuras)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(sulfuras).qualityShouldBe(0);
		QualityAssert.item(elixir).qualityShouldBe(0);
		QualityAssert.item(dextirity).qualityShouldBe(0);
	}
	
	@Test
	public void //TODO à mettre à jour
	items_quality_max_value_should_50() {

		// Given
		Item agedBrie = agedBrieBuilder.withQuality(49).withSellIn(12).build();
		Item backStage = backstageBuilder.withQuality(50).withSellIn(12).build();
		GildedRose gildedRose = GidedRoseBuilder.aGildedRose()
				.withItem(agedBrie)
				.withItem(backStage)
				.build();
		
		// When
		gildedRose.updateQuality();
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(agedBrie).qualityShouldBe(50);
		QualityAssert.item(backStage).qualityShouldBe(50);
	}
	
	@Test
	public void
	quantity_should_decrease_twice_when_sell_by_date_has_passed() {
		
		// Given
		Item dexterity = dextirityBuilder.withQuality(10).withSellIn(0).build();
		Item elixir = elixirBuilder.withQuality(10).withSellIn(0).build();
		Item conjured = ItemBuilder.anItem(ItemMutator.CONJURED_MANA_CAKE)
				.withQuality(10)
				.withSellIn(0)
				.build();
		GildedRose gildedRose = GidedRoseBuilder
				.aGildedRose()
				.withItem(dexterity)
				.withItem(elixir)
				.withItem(conjured)
				.build();
		
		// When
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(elixir).qualityShouldBe(8);
		QualityAssert.item(dexterity).qualityShouldBe(8);
		QualityAssert.item(conjured).qualityShouldBe(6);
	}

	@Test
	public void
	backstage_and_aged_brie_should_increase_in_quality_every_day() {
		
		List<String> items = Arrays.asList(GildedRose.AGED_BRIE, 
				GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);
		
		for(String itemName : items) {
			Item item = ItemBuilder
					.anItem(itemName)
					.withQuality(10)
					.withSellIn(12)
					.build();

			GildedRose gr = new GildedRose(item);
			
			gr.updateQuality();
			
			QualityAssert.item(item).qualityShouldBe(11);
		}
	}
	
	@Test
	public void
	backstage_and_aged_brie_max_quality_value_should_be_50() {
		
		List<String> items = Arrays.asList(GildedRose.AGED_BRIE, 
				GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);
		
		for(String itemName : items) {
			Item item = ItemBuilder
					.anItem(itemName)
					.withQuality(50)
					.withSellIn(1)
					.build();

			GildedRose gr = new GildedRose(item);
			
			gr.updateQuality();
			
			QualityAssert.item(item).qualityShouldBe(50);
		}
	}
	
	@Test
	public void
	backstage_quality_should_increases_by_2_when_it_remains_10_to_6_days() {
	
		for(int i = 6; i < 11; i++) {
			Item item = ItemBuilder
					.anItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
					.withQuality(10)
					.withSellIn(i)
					.build();

			GildedRose gr = new GildedRose(item);
			
			gr.updateQuality();
			
			QualityAssert.item(item).qualityShouldBe(12);
		}
	}
	
	@Test
	public void
	backstage_quality_should_increases_by_3_when_it_remains_5_to_1_days() {
	
		for(int i = 1; i < 6; i++) {
			Item item = ItemBuilder
					.anItem(ItemMutator.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
					.withQuality(10)
					.withSellIn(i)
					.build();

			GildedRose gr = new GildedRose(item);
			
			gr.updateQuality();
			
			QualityAssert.item(item).qualityShouldBe(13);
		}
	}
	
	@Test
	public void
	backstage_quality_should_drop_to_0_after_the_concert() {
	
		Item backstage = ItemBuilder
				.anItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)
				.withQuality(10)
				.withSellIn(0)
				.build();
		GildedRose gr = new GildedRose(backstage);
		
		gr.updateQuality();
		
		QualityAssert.item(backstage).qualityShouldBe(0);
		
	}
	
	@Test
	public void 
	should_increase_aged_brie_quality_by_2_when_sellIn_passed() {
		
		Item agedBrie = ItemBuilder.anItem(GildedRose.AGED_BRIE)
				.withQuality(12)
				.withSellIn(0)
				.build();
		GildedRose gr = new GildedRose(agedBrie);

		gr.updateQuality();
		
		QualityAssert.item(agedBrie)
		.qualityShouldBe(14);
	}
	
	@Test
	public void
	conjured_items_quality_should_degrade_twice_as_fast_as_normal_items() {
		
		// Given
		Item conjured = ItemBuilder.anItem(ItemMutator.CONJURED_MANA_CAKE)
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
		gildedRose.updateQuality();
		
		// Then
		QualityAssert.item(conjured).qualityIsValid();
		QualityAssert.item(conjured).qualityShouldBe(0);
	}
	
	public static class GidedRoseBuilder {
		
		private List<Item> items = new ArrayList<>();

		public static GidedRoseBuilder aGildedRose() {
			return new GidedRoseBuilder();
		}

		public GidedRoseBuilder withItem(Item item) {
			
			items.add(item);
			
			return this;
		}
		
		public GildedRose build() {
			return new GildedRose(items.toArray(new Item[items.size()]));
		}
		
	}
	
	public static class ItemBuilder {
		private String name;
		private int quality = 0;
		private int sellIn = 0;
		
		private ItemBuilder(String name) {
			this.name = name;
		}
		
		public static ItemBuilder anItem(String name) {
			return new ItemBuilder(name);
		}
		
		public ItemBuilder withQuality(int quality) {
			this.quality = quality;
			return this;
		}
		
		public ItemBuilder withSellIn(int sellIn) {
			this.sellIn = sellIn;
			return this;
		}
		
		public Item build() {
			return new Item(name, sellIn, quality);
		}
		
		
	}
	
	public static class QualityAssert {
		private Item item;
		
		private QualityAssert(Item item) {
			this.item = item;
		}

		public void sellByShouldBe(int sellIn) {
			assertEquals(sellIn, item.sellIn);
		}
		
		public void qualityIsValid() {
			assertTrue("Quality should be bettween 0 and 50", item.quality >= 0 && item.quality <= 50);
		}

		public static QualityAssert item(Item item) {
			return new QualityAssert(item);
		}
		
		public void qualityShouldBe(int value) {
			assertEquals(value, item.quality);
		}
	}
}
