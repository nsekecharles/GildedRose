package kata.gildedrose;
import static org.junit.Assert.*;

import org.junit.Test;

import kata.gildedrose.GildedRoseItemsState;

public class GildedRoseItemsStateTest {

	@Test
	public void 
	legacy_and_refactored_gildedrose_should_have_same_degradation() throws Exception {
		
		GildedRoseItemsState dh = new GildedRoseItemsState();
		
		assertEquals(dh.getRefactoredGildedRoseItemsStateOn(1), dh.getLegacyGildedRoseItemsStateOn(1));
	}
}
