package com.mobiquity.processor;

import com.mobiquity.calculator.ItemsProfitCalculator;
import com.mobiquity.converters.InputConverter;
import com.mobiquity.converters.OutputConverter;

import java.util.stream.Collectors;

public class ItemsProfitProcessor {

  private final InputConverter inputConverter;
  private final OutputConverter outputConverter;
  private final ItemsProfitCalculator itemsProfitCalculator;

  public ItemsProfitProcessor() {
    inputConverter = new InputConverter();
    outputConverter = new OutputConverter();
    itemsProfitCalculator = new ItemsProfitCalculator();
  }

  public ItemsProfitProcessor(InputConverter inputConverter, OutputConverter outputConverter, ItemsProfitCalculator itemsProfitCalculator) {
    this.inputConverter = inputConverter;
    this.outputConverter = outputConverter;
    this.itemsProfitCalculator = itemsProfitCalculator;
  }

  public String getProfitableItems(String absolutePath) {
    return inputConverter.getItemProfitInputs(absolutePath).stream()
        .map(
            weightItemsInput ->
                outputConverter.getProfitableItemsAsString(
                    itemsProfitCalculator.calculateMostProfitableItems(weightItemsInput)))
        .collect(Collectors.joining("\n"));
  }
}
