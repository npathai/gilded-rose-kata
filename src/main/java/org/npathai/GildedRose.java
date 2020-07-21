package org.npathai;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private List<Item> items = new ArrayList<>();

    public void tick() {
        for (Item item : items) {

            if (item.name.equals("Aged Brie")) {
                incrementQuality(item);
                decrementSellIn(item);
                if (item.getSellIn() < 0) {
                    incrementQuality(item);
                }
                continue;
            } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                incrementQuality(item);
                if (item.getSellIn() < 11) {
                    incrementQuality(item);
                }
                if (item.getSellIn() < 6) {
                    incrementQuality(item);
                }
                decrementSellIn(item);
                if (item.getSellIn() < 0) {
                    item.setQuality(0);
                }
                continue;
            } else {
                decrementQuality(item);
                decrementSellIn(item);
                if (item.getSellIn() < 0) {
                    decrementQuality(item);
                }
                continue;
            }
        }
    }

    private void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void decrementQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    public void addItem(@Nonnull Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}