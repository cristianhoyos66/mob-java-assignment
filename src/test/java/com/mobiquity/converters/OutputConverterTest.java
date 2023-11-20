package com.mobiquity.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputConverterTest {

  private OutputConverter outputConverter;

  @BeforeEach
  void setup() {
    outputConverter = new OutputConverter();
  }

  @Test
  void getProfitableItemsAsStringShouldReturnItemsAsString() {
    var profitableItems = List.of(1, 2, 3);
    var result = outputConverter.getProfitableItemsAsString(profitableItems);

    assertNotNull(result);
    assertEquals("1, 2, 3", result);
  }

  @Test
  void getProfitableItemsAsStringShouldReturnHyphenString() {
    List<Integer> profitableItems = List.of();
    var result = outputConverter.getProfitableItemsAsString(profitableItems);

    assertNotNull(result);
    assertEquals("-", result);
  }
}
