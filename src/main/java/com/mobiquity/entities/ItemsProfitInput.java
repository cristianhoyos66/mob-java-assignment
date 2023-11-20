package com.mobiquity.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemsProfitInput {

    private int[] profits;
    private double[] weights;
    private int capacity;

}
