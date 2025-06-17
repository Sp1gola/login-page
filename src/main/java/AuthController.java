import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsersList userService;

    @Autowired
    public AuthController(UsersList userService) {
        this.userService = userService;
    }

    // Login: risponde solo se utente esiste e password corretta,
    // altrimenti ritorna errore 401 Unauthorized
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("login");  // attenzione: nel JS il campo è "login"
        String password = payload.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User(username, password);
        if (userService.searchUser(user)) {
            // Login riuscito, rispondi 200 OK senza body
            return ResponseEntity.ok().build();
        } else {
            // Credenziali errate o utente non esistente
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Registrazione: registra se username non esiste, altrimenti 409 Conflict
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        if (userService.userExists(user.getUsername())) {
            response.put("success", false);
            response.put("message", "Username già registrato");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        userService.addUser(user);
        response.put("success", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
