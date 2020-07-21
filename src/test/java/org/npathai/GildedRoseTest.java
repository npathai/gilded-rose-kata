package org.npathai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.npathai.ItemAssertions.assertThatOnlyItem;

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

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }


        @ParameterizedTest
        @CsvSource({
                "0, 10, -1, 12",
                "-1, 11, -2, 13",
                "0, 48, -1, 50"
        })
        public void afterSellDateDateQualityIncreasesTwiceAsFast(int sellIn, int quality, int expectedSellIn,
                                                                 int expectedQuality) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "5, 4",
                "1, 0"
        })
        public void beforeSellDateWithMaxQualityItRemainsUnchanged(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, 50));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(50);
        }

        @ParameterizedTest
        @CsvSource({
                "0, -1",
                "-1, -2"
        })
        public void afterSellDateWithMaxQualityItRemainsUnchanged(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Aged Brie", sellIn, 50));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(50);
        }
    }

    @Nested
    public class Sulfuras {

        @ParameterizedTest
        @CsvSource({
                "5, 5",
                "1, 1",
        })
        public void beforeSellDateDoesntAge(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Sulfuras, Hand of Ragnaros", sellIn, 10));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(10);
        }

        @ParameterizedTest
        @CsvSource({
                "0, 0",
                "-1, -1",
        })
        public void afterSellDateDoesntAge(int sellIn, int expectedSellIn) {
            gildedRose.addItem(new Item("Sulfuras, Hand of Ragnaros", sellIn, 10));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(10);
        }

        @ParameterizedTest
        @CsvSource({
                "10, 10",
                "11, 11",
                "0, 0",
                "50, 50"
        })
        public void beforeSellDateQualityDoesntChange(int quality, int expectedQuality) {
            gildedRose.addItem(new Item("Sulfuras, Hand of Ragnaros", 1, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(1)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "10, 10",
                "1, 1",
                "0, 0",
                "50, 50"
        })
        public void afterSellDateQualityDoesntChange(int quality, int expectedQuality) {
            gildedRose.addItem(new Item("Sulfuras, Hand of Ragnaros", -1, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(-1)
                    .hasQuality(expectedQuality);
        }
    }

    @Nested
    class BackstagePasses {

        @ParameterizedTest
        @CsvSource({
                "20, 10, 19, 11",
                "11, 19, 10, 20",
        })
        public void longBeforeSellDateQualityIncreasesAtSlowerRate(int sellIn, int quality, int expectedSellIn,
                                                                   int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "20, 49, 19, 50",
                "11, 50, 10, 50",
        })
        public void longBeforeSellDateQualityDoesNotIncreaseMoreThanMaximum(int sellIn, int quality, int expectedSellIn,
                                                                            int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "10, 10, 9, 12",
                "9, 19, 8, 21",
                "6, 20, 5, 22",
                "6, 48, 5, 50"
        })
        public void mediumCloseToSellDateQualityIncreasesAtTwiceRate(int sellIn, int quality, int expectedSellIn,
                                                                     int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "10, 49, 9, 50",
                "10, 50, 9, 50",
        })
        public void mediumCloseToSellDateQualityDoesNotIncreaseMoreThanMaximum(int sellIn, int quality, int expectedSellIn,
                                                                               int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "5, 10, 4, 13",
                "4, 19, 3, 22",
                "1, 20, 0, 23",
                "1, 47, 0, 50"
        })
        public void veryCloseToSellDateQualityIncreasesAtThriceRate(int sellIn, int quality, int expectedSellIn,
                                                                    int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "5, 48, 4, 50",
                "4, 49, 3, 50",
                "1, 50, 0, 50",
        })
        public void veryCloseToSellDateQualityDoesNotIncreaseMoreThanMaximum(int sellIn, int quality, int expectedSellIn,
                                                                             int expectedQuality) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "0, 48, -1, 0",
                "-1, 50, -2, 0",
        })
        public void afterSellDateQualityDropsToZero(int sellIn, int quality, int expectedSellIn) {
            gildedRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(0);
        }
    }

    @Nested
    class Normal {

        @ParameterizedTest
        @CsvSource({
                "5, 10, 4, 9",
                "1, 19, 0, 18",
                "1, 1, 0, 0"
        })
        public void beforeSellDateQualityDecreases(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Amul Milk", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "5, 0, 4, 0",
                "1, 0, 0, 0"
        })
        public void beforeSellDateQualityDoesNotDropBelowZero(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Amul Milk", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "0, 10, -1, 8",
                "-1, 19, -2, 17",
                "-1, 2, -2, 0"
        })
        public void afterSellDateQualityDecreasesAtTwiceRate(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Amul Milk", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }

        @ParameterizedTest
        @CsvSource({
                "0, 1, -1, 0",
                "-1, 0, -2, 0",
        })
        public void afterSellDateQualityDoesNotDropBelowZero(int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            gildedRose.addItem(new Item("Amul Milk", sellIn, quality));
            gildedRose.tick();

            assertThatOnlyItem(gildedRose.getItems())
                    .hasSellIn(expectedSellIn)
                    .hasQuality(expectedQuality);
        }
    }
}