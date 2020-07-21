package org.npathai;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private List<Item> items = new ArrayList<>();

    public void tick() {
        for (Item item : items) {

            if (item.name.equals("Aged Brie")) {
                if (item.getQuality() < 50) {
                    item.setQuality(item.getQuality() + 1);
                }
                item.setSellIn(item.getSellIn() - 1);
                if (item.getSellIn() < 0) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
                continue;
            } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.getQuality() < 50) {
                    item.setQuality(item.getQuality() + 1);
                }
                if (item.getSellIn() < 11) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
                if (item.getSellIn() < 6) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
                item.setSellIn(item.getSellIn() - 1);
                if (item.getSellIn() < 0) {
                    item.setQuality(0);
                }
                continue;
            } else if (item.name.equals("Amul Milk")) {
                if (item.getQuality() > 0) {
                    item.setQuality(item.getQuality() - 1);
                }
                item.setSellIn(item.getSellIn() - 1);
                if (item.getSellIn() < 0) {
                    if (item.getQuality() > 0) {
                        item.setQuality(item.getQuality() - 1);
                    }
                }
                continue;
            }

            if (!item.getName().equals("Aged Brie") && !item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.getQuality() > 0) {
                    if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                        item.setQuality(item.getQuality() - 1);
                    }
                }
            } else {
                if (item.getQuality() < 50) {
                    item.setQuality(item.getQuality() + 1);

                    if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.getSellIn() < 11) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }

                        if (item.getSellIn() < 6) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0) {
                if (!item.getName().equals("Aged Brie")) {
                    if (!item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.getQuality() > 0) {
                            if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                                item.setQuality(item.getQuality() - 1);
                            }
                        }
                    } else {
                        item.setQuality(item.getQuality() - item.getQuality());
                    }
                } else {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }

    public void addItem(@Nonnull Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}