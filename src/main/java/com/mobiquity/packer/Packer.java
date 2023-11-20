package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.processor.ItemsProfitProcessor;

public class Packer {

  private static final ItemsProfitProcessor itemsProfitProcessor = new ItemsProfitProcessor();

  private Packer() {}

  public static String pack(String filePath) throws APIException {
    return itemsProfitProcessor.getProfitableItems(filePath);
  }
}
