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

        List<FoodItems> cartItems = orderCart.get(orderId);

        if(cartItems.isEmpty()){
            this.orderCart.put(orderId, updateItems);
            return  true;
        }

        List<FoodItems> mergedResult = mergeCartItems(cartItems, updateItems);

        this.orderCart.put(orderId, mergedResult);
        return  true;
    }

    private List<FoodItems> mergeCartItems(List<FoodItems> existing, List<FoodItems>  updated){
        Map<Integer, FoodItems> map = new HashMap<>();

        for(FoodItems item : existing){
            map.put(item.getId(), item);
        }

        for(FoodItems item : updated){
            map.put(item.getId(), item);
        }
        return  new ArrayList<>(map.values());
    }



}
