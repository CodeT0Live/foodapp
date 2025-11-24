package org.foodapp.services;

import org.foodapp.models.FoodItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {

    private final AtomicLong orderCounter = new AtomicLong(0);
    private Map<Long, List<FoodItems>> orderCart = new HashMap<>();

    private long generateOrderId(){
        return orderCounter.incrementAndGet();
    }


    public long AddItemToCart(List<FoodItems> cartItems){
        final long orderId = generateOrderId();
        this.orderCart.put(orderId, cartItems);
        return  orderId;
    }

    public boolean UpdateCartItem(long orderId , List<FoodItems> updateItems){
        return  false;
    }



}
