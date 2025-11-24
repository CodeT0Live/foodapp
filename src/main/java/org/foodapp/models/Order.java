package org.foodapp.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Order {

    private int id;
    private int resturantId;
    private List<FoodItems> cart;
    private int grossAmount;
    private int totalAmount;
}
