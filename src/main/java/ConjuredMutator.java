
public class ConjuredMutator implements ItemMutator {
	
	@Override
	public void mutate(Item item) {
		updateSellIn(item);
		decreaseQualityOfItemAtIndex(item);
		decreaseQualityOfItemAtIndex(item);
		if(item.getSellIn() < 0) {
			decreaseQualityOfItemAtIndex(item);
			decreaseQualityOfItemAtIndex(item);
		}
	}
}
