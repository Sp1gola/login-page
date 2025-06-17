package main;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersList {
    private List<User> list = new ArrayList<>();

    public boolean searchUser(User user){
        for(User u : list){
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
    public void addUser(User user){
        list.add(user);
    }

    public boolean userExists(String username) {
        return list.stream().anyMatch(u -> u.getUsername().equals(username));
    }
}