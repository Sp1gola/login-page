package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Cerca utente con username e password (login)
    public boolean searchUser(User user) {
        User found = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return found != null;
    }

    // Controlla se username esiste (registrazione)
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    // Aggiungi nuovo utente
    public void addUser(User user) {
        userRepository.save(user);
    }
}