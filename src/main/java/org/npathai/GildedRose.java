package org.npathai;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private final List<Item> items = new ArrayList<>();

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

    public void addItem(@Nonnull Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    static class ItemCategory {

        private final Item item;

        public ItemCategory(Item item) {
            this.item = item;
        }

        public void tick() {

        }

        protected void decrementSellIn() {
            item.setSellIn(item.getSellIn() - 1);
        }

        protected void decrementQuality() {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        protected void incrementQuality() {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }

        public Item getItem() {
            return item;
        }
    }

    static class AgedBrie extends ItemCategory {

        public AgedBrie(Item item) {
            super(item);
        }

        public void tick() {
            agedBrieTick();
        }

        private void agedBrieTick() {
            decrementSellIn();
            incrementQuality();
            if (getItem().getSellIn() < 0) {
                incrementQuality();
            }
        }
    }

    static class Sulfuras extends ItemCategory {

        public Sulfuras(Item item) {
            super(item);
        }

        public void tick() {

        }
    }

    private static class BackstagePass extends ItemCategory {
        public BackstagePass(Item item) {
            super(item);
        }

        public void tick() {
            backstagePassTick();
        }

        private void backstagePassTick() {
            decrementSellIn();
            incrementQuality();
            if (getItem().getSellIn() < 10) {
                incrementQuality();
            }
            if (getItem().getSellIn() < 5) {
                incrementQuality();
            }
            if (getItem().getSellIn() < 0) {
                getItem().setQuality(0);
            }
        }
    }

    private static class Normal extends ItemCategory {

        public Normal(Item item) {
            super(item);
        }

        public void tick() {
            normalTick();
        }

        private void normalTick() {
            decrementSellIn();
            decrementQuality();
            if (getItem().getSellIn() < 0) {
                decrementQuality();
            }
        }
    }
}