package org.foodapp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private int id;
    private String email;
    private String name;
    private short age;
    private String gender;
    private String phone;
    private Date dataOfBirth;

    public User(){

    }

}
