package org.npathai;

import org.assertj.core.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemAssertions {


    private final Item item;

    public ItemAssertions(Item item) {
        this.item = item;
    }

    public static ItemAssertions assertThatOnlyItem(List<Item> items) {
        assertThat(items).hasSize(1);

        return new ItemAssertions(items.get(0));
    }


    public ItemAssertions hasSellIn(int expectedSellIn) {
        assertThat(item.sellIn).isEqualTo(expectedSellIn);
        return this;
    }


    public ItemAssertions hasQuality(int expectedQuality) {
        assertThat(item.quality).isEqualTo(expectedQuality);
        return this;
    }
}
