package org.foodapp.services;

import org.foodapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> users;

    public UserService(){
        users = new ArrayList<User>();
    }

    public boolean AddNewUser(User user){
        boolean exists = users.stream().anyMatch(row -> row.getPhone().trim() == user.getPhone().trim() || row.getEmail().trim() == user.getEmail().trim());

        if(exists)
            return false;

        users.add(user);
        return true;
    }

    public  boolean UpdateUser(User user){

        for(int i=0; i< this.users.size(); i++){
            if(user.getId() == this.users.get(i).getId()){
                this.users.set(i, user);
                return true;
            }
        }
        return false;
    }

}
