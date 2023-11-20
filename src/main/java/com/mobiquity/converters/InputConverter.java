package com.mobiquity.converters;

import com.mobiquity.entities.ItemsProfitInput;
import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {

  public List<ItemsProfitInput> getItemProfitInputs(String absolutePath) {
      try (var linesStream = Files.lines(Paths.get(absolutePath))) {
          var weightItemsInputs = new ArrayList<ItemsProfitInput>();
          linesStream.forEach(line -> createWeightItemsInputsFromLine(line, weightItemsInputs));
          return weightItemsInputs;
      } catch (IOException exception) {
          throw new APIException("There is a problem reading the file", exception);
      }
  }

    private void createWeightItemsInputsFromLine(String line, ArrayList<ItemsProfitInput> itemsProfitInputs) {
        var capacityVsItemInfo = line.split(" : ");
        var itemsInfo = capacityVsItemInfo[1].split("\\) ");
        var weights = new double[itemsInfo.length];
        var profits = new int[itemsInfo.length];
        convertStrItemInfoToRespectiveTypes(itemsInfo, weights, profits);
        itemsProfitInputs.add(ItemsProfitInput.builder()
                .weights(weights)
                .profits(profits)
                .capacity(Integer.parseInt(capacityVsItemInfo[0]))
                .build());
    }

    private void convertStrItemInfoToRespectiveTypes(String[] itemsInfo, double[] weights, int[] profits) {
        for (String itemInfo : itemsInfo) {
            var separatedItemInfo = itemInfo.split(",");
            var element = Integer.parseInt(separatedItemInfo[0].substring(1)) - 1;
            var weight = Double.parseDouble(separatedItemInfo[1]);
            var profit = Integer.parseInt(separatedItemInfo[2].substring(1).replace(")", ""));
            weights[element] = weight;
            profits[element] = profit;
        }
    }
}
