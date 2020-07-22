package org.npathai;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private List<Item> items = new ArrayList<>();

    public void tick() {
        for (Item item : items) {
            ItemCategory itemCategory = createCategory(item);
            itemCategory.tick();
        }
    }

    private ItemCategory createCategory(Item item) {
        switch (item.getName()) {
            case "Aged Brie":
                return new AgedBrie(item);
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePass(item);
            default:
                return new Normal(item);
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

    class ItemCategory {

        public void tick() {

        }
    }

    class AgedBrie extends ItemCategory {
        private final Item item;

        public AgedBrie(Item item) {
            this.item = item;
        }

        public void tick() {
            agedBrieTick(item);
        }
    }

    class Sulfuras extends ItemCategory {

        private final Item item;

        public Sulfuras(Item item) {
            this.item = item;
        }

        public void tick() {
            sulfurasTick();
        }
    }

    private class BackstagePass extends ItemCategory {
        private final Item item;

        public BackstagePass(Item item) {
            this.item = item;
        }

        public void tick() {
            backstagePassTick(item);
        }
    }

    private class Normal extends ItemCategory {
        private final Item item;

        public Normal(Item item) {
            this.item = item;
        }

        public void tick() {
            normalTick(item);
        }
    }
}