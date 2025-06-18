package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //cerca utente con username e password (login)
    public boolean searchUser(User user) {
        User found = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return found != null;
    }

    //controlla se username esiste
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    //aggiunge user
    public void addUser(User user) {
        userRepository.save(user);
    }

}