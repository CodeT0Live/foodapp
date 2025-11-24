package org.foodapp.models;

import lombok.Getter;

public class FoodItems {
    @Getter
    private int id;
    private String name;
    private String descriptions;
    private int rating;
}
