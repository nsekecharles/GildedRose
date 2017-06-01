
public class AgedBrieMutator implements ItemMutator {

	@Override
	public void mutate(Item item) {
		updateSellIn(item);
		increaseQualityOfItemAtIndex(item);
    	if (item.getSellIn() < 0) {
    		increaseQualityOfItemAtIndex(item);
    	}
	}
}
