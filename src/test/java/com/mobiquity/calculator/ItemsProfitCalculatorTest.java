package com.mobiquity.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mobiquity.entities.ItemsProfitInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemsProfitCalculatorTest {

  private ItemsProfitCalculator itemsProfitCalculator;

  @BeforeEach
  void setup() {
    itemsProfitCalculator = new ItemsProfitCalculator();
  }

  @Test
  void calculateMostProfitableItemsShouldReturnEmptyList() {
    var itemsProfitInput =
        ItemsProfitInput.builder()
            .capacity(8)
            .weights(new double[] {15.3})
            .profits(new int[] {34})
            .build();
    var profitableItems = itemsProfitCalculator.calculateMostProfitableItems(itemsProfitInput);

    assertNotNull(profitableItems);
    assertTrue(profitableItems.isEmpty());
  }

  @Test
  void calculateMostProfitableItemsShouldReturn4() {
    var itemsProfitInput =
        ItemsProfitInput.builder()
            .capacity(81)
            .weights(new double[] {53.38, 88.62, 78.48, 72.30, 30.18, 46.34})
            .profits(new int[] {45, 98, 3, 76, 9, 48})
            .build();
    var profitableItems = itemsProfitCalculator.calculateMostProfitableItems(itemsProfitInput);

    assertNotNull(profitableItems);
    assertFalse(profitableItems.isEmpty());
    assertEquals(1, profitableItems.size());
    assertEquals(4, profitableItems.get(0));
  }

  @Test
  void calculateMostProfitableItemsShouldReturn7And2() {
    var itemsProfitInput =
        ItemsProfitInput.builder()
            .capacity(75)
            .weights(new double[] {85.31, 14.55, 3.98, 26.24, 63.69, 76.25, 60.02, 93.18, 89.95})
            .profits(new int[] {29, 74, 16, 55, 52, 75, 74, 35, 78})
            .build();
    var profitableItems = itemsProfitCalculator.calculateMostProfitableItems(itemsProfitInput);

    assertNotNull(profitableItems);
    assertFalse(profitableItems.isEmpty());
    assertEquals(2, profitableItems.size());
    assertEquals(7, profitableItems.get(0));
    assertEquals(2, profitableItems.get(1));
  }

  @Test
  void calculateMostProfitableItemsShouldReturn9And6() {
    var itemsProfitInput =
        ItemsProfitInput.builder()
            .capacity(55)
            .weights(new double[] {90.72, 33.80, 43.15, 37.97, 46.81, 48.77, 81.80, 19.36, 6.76})
            .profits(new int[] {13, 40, 10, 16, 36, 79, 45, 79, 64})
            .build();
    var profitableItems = itemsProfitCalculator.calculateMostProfitableItems(itemsProfitInput);

    assertNotNull(profitableItems);
    assertFalse(profitableItems.isEmpty());
    assertEquals(2, profitableItems.size());
    assertEquals(9, profitableItems.get(0));
    assertEquals(6, profitableItems.get(1));
  }
}
