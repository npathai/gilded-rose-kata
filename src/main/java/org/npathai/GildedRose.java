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
                return new Cheese(item);
            case "Sulfuras, Hand of Ragnaros":
                return new Legendary(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePass(item);
            case "Conjured":
                return new Conjured(item);
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

    static abstract class ItemCategory {

        public static final int MAX_QUALITY = 50;
        public static final int MIN_QUALITY = 0;
        private final Item item;

        public ItemCategory(Item item) {
            this.item = item;
        }

        public void tick() {
            updateSellIn();
            updateQuality();
        }

        protected void updateSellIn() {
            decrementSellIn();
        }

        protected abstract void updateQuality();

        protected final void decrementSellIn() {
            item.setSellIn(item.getSellIn() - 1);
        }

        protected final void decrementQuality() {
            if (item.getQuality() > MIN_QUALITY) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        protected final void incrementQuality() {
            if (item.getQuality() < MAX_QUALITY) {
                item.setQuality(item.getQuality() + 1);
            }
        }

        public Item getItem() {
            return item;
        }

        protected final boolean hasExpired() {
            return item.getSellIn() < 0;
        }
    }

    static class Cheese extends ItemCategory {

        public Cheese(Item item) {
            super(item);
        }

        @Override
        protected void updateQuality() {
            incrementQuality();
            if (hasExpired()) {
                incrementQuality();
            }
        }
    }

    static class Legendary extends ItemCategory {

        public Legendary(Item item) {
            super(item);
        }

        @Override
        protected void updateSellIn() {
            // Doesn't age
        }

        @Override
        protected void updateQuality() {
            // Doesn't degrade
        }
    }

    private static class BackstagePass extends ItemCategory {
        public BackstagePass(Item item) {
            super(item);
        }

        @Override
        protected void updateQuality() {
            incrementQuality();
            if (getItem().getSellIn() < 10) {
                incrementQuality();
            }
            if (getItem().getSellIn() < 5) {
                incrementQuality();
            }
            if (hasExpired()) {
                getItem().setQuality(0);
            }
        }
    }

    private static class Normal extends ItemCategory {

        public Normal(Item item) {
            super(item);
        }

        @Override
        protected void updateQuality() {
            decrementQuality();
            if (hasExpired()) {
                decrementQuality();
            }
        }
    }

    private static class Conjured extends ItemCategory {
        public Conjured(Item item) {
            super(item);
        }

        @Override
        protected void updateQuality() {
            decrementQuality();
            decrementQuality();
        }
    }
}