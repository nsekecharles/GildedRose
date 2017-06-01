import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GildedRose {

	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
	public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	
	private List<Item> items = null;

	public GildedRose(Item... item) {
		this(Arrays.asList(item));
	}
	
	public GildedRose(List<Item> items) {
		this.items = items;
	}
	
	public static void main(String[] args) {
        System.out.println("OMGHAI!");
	}
	
    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
        	ItemMutator mutator = getMutator(items.get(i));
        	mutator.mutate(items.get(i));	
        }
    }

	private ItemMutator getMutator(Item item) {
		if(AGED_BRIE.equals(item.getName())) {
			return new AgedBrieMutator();
		} else if(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(item.getName())) {
			return new BackStageMutator();
		} else if(SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())) {
			return new SulfurasMutator();
		} else if(CONJURED_MANA_CAKE.equals(item.getName())) {
			return new ConjuredMutator();
		} else {
			return new DefaultMutator();
		}
	}

	public List<String> getItemsState() {
		List<String> states = new ArrayList<>();
		for(Item item : items) {
    		System.out.println(String.format("%s has %s as quality and should be sellIn %s", item.name, item.quality, item.sellIn));
    		states.add(String.format("%s has %s as quality and should be sellIn %s", item.name, item.quality, item.sellIn));
		}
		System.out.println("------------------------------------------------------------------------------------------------------");
		return states;
	}

}