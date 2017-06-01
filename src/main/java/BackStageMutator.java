
public class BackStageMutator implements ItemMutator {

	@Override
	public void mutate(Item item) {
		updateSellIn(item);
		if (item.getSellIn() < 0) {
        	setQualityToZero(item);
        } else {
        	increaseQualityOfItemAtIndex(item);
            if (item.getSellIn() + 1 < 11) {
            	increaseQualityOfItemAtIndex(item);
            }
            if (item.getSellIn() + 1 < 6) {
            	increaseQualityOfItemAtIndex(item);
            }
        }
	}
}
