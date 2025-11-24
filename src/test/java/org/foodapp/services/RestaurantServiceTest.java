package org.foodapp.services;

import org.foodapp.models.Address;
import org.foodapp.models.Resturant;
import org.foodapp.services.RestaurantService;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.github.javafaker.Faker;

import static org.junit.jupiter.api.Assertions.*;


public class RestaurantServiceTest {

    @Test
    public void testAddNewRestaurant(){
        RestaurantService service = new RestaurantService();

        Address address = new Address(10, "MG Road", "560001", "Bangalore", "KA");
        Resturant resturant = new Resturant(1, "KFC", address, 4, Collections.emptyList());

        // Act (call the method being tested)
        boolean result = service.AddNewRestaurant(resturant);

        assertTrue(result, "Resturants should be added successfully");
    }

    @Test
    public void testmultipleaddnewresturantconcurrently() throws InterruptedException {
        RestaurantService service = new RestaurantService();
        try {
            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

            for (int i = 0; i < 100; i++) {
                final int id = i;
                Address address = new Address(10, "MG Road", "560001", "Bangalore", "KA");
                executor.submit(() -> {
                    Resturant resturant = new Resturant(id, "Resturant", address, 4, Collections.emptyList());
                    boolean added = service.AddNewRestaurant(resturant);
                });
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
            assertEquals(100, service.Count());
            System.out.println("Total Resturants" + service.Count());
        }
        catch (Exception ignored){

        }
    }


    @Test
    public void testRestaurantSearchByName(){
        RestaurantService service = new RestaurantService();
        Faker faker = new Faker();
        Random random = new Random();
        
        List<String> cities = List.of("Bangalore", "Hyderabad", "Mumbai", "Delhi");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<100; i++){
            final  String city = cities.get(random.nextInt(cities.size()));
            Integer merge = map.merge(city, 1, Integer::sum);
            Address address = new Address(10, "MG Road", city, "Karnataka", "5600"+i);
            Resturant resturant = new Resturant(
                    i,
                    "New Restuarant"+i,
                    address,
                    5,
                    Collections.emptyList()
            );
            service.AddNewRestaurant(resturant);
        }
        
        List<Resturant> result =  service.searchByCity("bangalore");
        int count = map.getOrDefault("Bangalore",0);
        assertEquals(count, result.size());
        System.out.println("Actual Count:"+ count+", Search Count"+ result.size());
    }
}
