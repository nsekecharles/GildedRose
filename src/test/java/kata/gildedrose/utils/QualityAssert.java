package kata.gildedrose.utils;
import static org.junit.Assert.*;

import kata.gildedrose.legacy.Item;

public class QualityAssert {
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