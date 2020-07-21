package org.npathai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    private GildedRose gildedRose;

    @BeforeEach
    void createGildedRose() {
        gildedRose = new GildedRose();
    }

    @Nested
    public class AgedBrie {

        @ParameterizedTest
        @CsvSource({
                "5, 10, 4, 11",
                "1, 19, 0, 20",
                "1, 49, 0, 50"
        })
        public void beforeSellDateQualityIncreases(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, quality));
            gildedRose.tick();
            assertThat(gildedRose.getItems().get(0).sellIn).isEqualTo(expectedSellIn);
            assertThat(gildedRose.getItems().get(0).quality).isEqualTo(expectedQuality);
        }


        @ParameterizedTest
        @CsvSource({
                "0, 10, -1, 12",
                "-1, 11, -2, 13",
                "0, 48, -1, 50"
        })
        public void afterSellDateDateQualityIncreasesTwiceAsFast(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, quality));
            gildedRose.tick();
            assertThat(gildedRose.getItems().get(0).sellIn).isEqualTo(expectedSellIn);
            assertThat(gildedRose.getItems().get(0).quality).isEqualTo(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "5, 4",
                "1, 0"
        })
        public void beforeSellDateWithMaxQualityItRemainsUnchanged(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, 50));
            gildedRose.tick();
            assertThat(gildedRose.getItems().get(0).sellIn).isEqualTo(expectedSellIn);
            assertThat(gildedRose.getItems().get(0).quality).isEqualTo(50);
        }

        @ParameterizedTest
        @CsvSource({
                "0, -1",
                "-1, -2"
        })
        public void afterSellDateWithMaxQualityItRemainsUnchanged(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, 50));
            gildedRose.tick();
            assertThat(gildedRose.getItems().get(0).sellIn).isEqualTo(expectedSellIn);
            assertThat(gildedRose.getItems().get(0).quality).isEqualTo(50);
        }
    }
}