package org.foodapp.services;

import org.foodapp.models.Resturant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RestaurantService {
    private final List<Resturant> resturantList;
    private final Lock lock;
    public RestaurantService(){
        this.resturantList = new ArrayList<Resturant>();
        this.lock = new ReentrantLock();
    }

    public boolean AddNewRestaurant(Resturant resturant){
        lock.lock();
        try {
            boolean exits = this.resturantList.stream().anyMatch(row -> row.getId() == resturant.getId());

            if (exits) return false;

            this.resturantList.add(resturant);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean RemoveResturant(Resturant resturant){
        lock.lock();
        try {
            return this.resturantList.removeIf(row -> row.getId() == resturant.getId());
        }finally {
            lock.unlock();
        }
    }

    public boolean UpdateResturant(Resturant resturant){
        lock.lock();
        try {
            for(int i=0; i< this.resturantList.size(); i++){
                if(this.resturantList.get(i).getId() == resturant.getId()){
                    this.resturantList.set(i, resturant);
                    return  true;
                }
            }
            return  false;
        }finally {
            lock.unlock();
        }

    }

    public int Count(){
        return this.resturantList.size();
    }

    public List<Resturant> searchByCity(String city){

        return this.resturantList.stream().filter(row -> row.getAddress().city().equalsIgnoreCase(city)).toList();
    }

    public List<Resturant> searchByName(String name){
        return this.resturantList.stream().filter(row -> row.getName().equalsIgnoreCase(name)).toList();
    }

}
