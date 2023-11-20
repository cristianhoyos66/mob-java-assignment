package com.mobiquity.calculator;

import com.mobiquity.entities.ItemsProfitInput;
import java.util.ArrayList;
import java.util.List;

public class ItemsProfitCalculator {

  public List<Integer> calculateMostProfitableItems(ItemsProfitInput itemsProfitInput) {
    int[] profits = itemsProfitInput.getProfits();
    double[] weights = itemsProfitInput.getWeights();
    int capacity = itemsProfitInput.getCapacity();
    int amountOfElements = profits.length;
    int[][] runningCalculationTable = new int[amountOfElements + 1][capacity + 1];

    updateRunningCalculationTable(
        amountOfElements, capacity, runningCalculationTable, weights, profits);
    //System.out.println("Maximum Profit: " + runningCalculationTable[amountOfElements][capacity]);

    return getMostProfitableElements(amountOfElements, capacity, runningCalculationTable, weights);
  }

  private void updateRunningCalculationTable(
          int amountOfElements,
          int capacity,
          int[][] runningCalculationTable,
          double[] weights,
          int[] profits) {
    for (int itemIdx = 0; itemIdx <= amountOfElements; itemIdx++) {
      for (int columnWeight = 0; columnWeight <= capacity; columnWeight++) {
        if (itemIdx == 0 || columnWeight == 0) {
          runningCalculationTable[itemIdx][columnWeight] = 0;
        } else if (weights[itemIdx - 1] <= columnWeight) {
          int includeItem =
                  profits[itemIdx - 1]
                          + runningCalculationTable[itemIdx - 1][columnWeight - (int) weights[itemIdx - 1]];
          int excludeItem = runningCalculationTable[itemIdx - 1][columnWeight];
          runningCalculationTable[itemIdx][columnWeight] = Math.max(includeItem, excludeItem);
        } else {
          runningCalculationTable[itemIdx][columnWeight] =
                  runningCalculationTable[itemIdx - 1][columnWeight];
        }
      }
    }
  }

  private List<Integer> getMostProfitableElements(int amountOfElements, int capacity, int[][] runningCalculationTable, double[] weights) {
    List<Integer> selectedItems = new ArrayList<>();
    while (amountOfElements > 0 && capacity > 0) {
      if (runningCalculationTable[amountOfElements][capacity] != runningCalculationTable[amountOfElements - 1][capacity]) {
        selectedItems.add(amountOfElements);
        capacity -= (int) weights[amountOfElements - 1];
      }
      amountOfElements--;
    }
    return selectedItems;
  }
}
