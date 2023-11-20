package com.mobiquity.converters;

import java.util.List;
import java.util.stream.Collectors;

public class OutputConverter {

  public String getProfitableItemsAsString(List<Integer> profitableItems) {
    return !profitableItems.isEmpty()
        ? profitableItems.stream().map(String::valueOf).collect(Collectors.joining(", "))
        : "-";
  }
}
