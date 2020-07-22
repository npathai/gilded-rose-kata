package org.npathai;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private List<Item> items = new ArrayList<>();

    public void tick() {
        for (Item item : items) {
            if (item.name.equals("Aged Brie")) {
                agedBrieTick(item);
                continue;
            } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                sulfurasTick();
                continue;
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                backstagePassTick(item);
                continue;
            } else {
                normalTick(item);
                continue;
            }
        }
    }

    private void backstagePassTick(Item item) {
        decrementSellIn(item);
        incrementQuality(item);
        if (item.getSellIn() < 10) {
            incrementQuality(item);
        }
        if (item.getSellIn() < 5) {
            incrementQuality(item);
        }
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        }
    }

    private void sulfurasTick() {

    }

    private void agedBrieTick(Item item) {
        decrementSellIn(item);
        incrementQuality(item);
        if (item.getSellIn() < 0) {
            incrementQuality(item);
        }
    }

    private void normalTick(Item item) {
        decrementSellIn(item);
        decrementQuality(item);
        if (item.getSellIn() < 0) {
            decrementQuality(item);
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