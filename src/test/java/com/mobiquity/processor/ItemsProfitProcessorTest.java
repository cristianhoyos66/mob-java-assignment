package com.mobiquity.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.mobiquity.calculator.ItemsProfitCalculator;
import com.mobiquity.converters.InputConverter;
import com.mobiquity.converters.OutputConverter;
import com.mobiquity.entities.ItemsProfitInput;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemsProfitProcessorTest {

  @Mock private InputConverter inputConverter;

  @Mock private OutputConverter outputConverter;

  @Mock private ItemsProfitCalculator itemsProfitCalculator;

  private ItemsProfitProcessor itemsProfitProcessor;

  @BeforeEach
  void setup() {
    itemsProfitProcessor =
        new ItemsProfitProcessor(inputConverter, outputConverter, itemsProfitCalculator);
  }

  @Test
  void getProfitableItemsShouldReturn4AsStringWithHyphen() {
    var absolutePath = "absolutePath";
    var itemProfitInputs =
        List.of(
            ItemsProfitInput.builder()
                .capacity(81)
                .weights(new double[] {53.38, 88.62, 78.48, 72.30, 30.18, 46.34})
                .profits(new int[] {45, 98, 3, 76, 9, 48})
                .build(),
            ItemsProfitInput.builder()
                .capacity(8)
                .weights(new double[] {15.3})
                .profits(new int[] {34})
                .build());

    when(inputConverter.getItemProfitInputs(absolutePath)).thenReturn(itemProfitInputs);

    when(itemsProfitCalculator.calculateMostProfitableItems(any(ItemsProfitInput.class)))
        .thenReturn(List.of(4))
        .thenReturn(List.of());
    when(outputConverter.getProfitableItemsAsString(anyList())).thenReturn("4").thenReturn("-");

    var mostProfitableItems = itemsProfitProcessor.getProfitableItems(absolutePath);

    assertNotNull(mostProfitableItems);
    assertEquals("4\n-", mostProfitableItems);
  }
}
