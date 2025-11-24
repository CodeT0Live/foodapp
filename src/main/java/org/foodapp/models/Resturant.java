package org.foodapp.models;

import lombok.Getter;

import java.util.List;

public class Resturant {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private Address address;
    private int rating;
    private List<FoodItems> menu;

    public Resturant(int id, String name, Address address, int rating, List<FoodItems> menu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.menu = menu;
    }
}
