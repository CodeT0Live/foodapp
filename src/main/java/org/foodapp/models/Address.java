package org.foodapp.models;

public record Address(
     int plotNumber,
     String street,
     String city,
     String state,
     String pincode
){
}
