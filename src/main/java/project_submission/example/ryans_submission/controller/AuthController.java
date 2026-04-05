package project_submission.example.ryans_submission.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_submission.example.ryans_submission.model.LoginRequest;
import project_submission.example.ryans_submission.model.SignupRequest;
import project_submission.example.ryans_submission.model.Users;
import project_submission.example.ryans_submission.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Users> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }

        if (!user.get().getIsActive()) {
            return ResponseEntity.status(403).body(Map.of("error", "Account is deactivated"));
        }

        if (!user.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }

        return ResponseEntity.ok(Map.of("message", "Login successful! Welcome " + user.get().getName()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        Optional<Users> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("error", "This email has already been used!"));
        }

        Users newUser = new Users();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setName(request.getName());
        newUser.setAge(request.getAge());
        newUser.setRole("USER");
        userRepository.save(newUser);
        return ResponseEntity.status(201).body(Map.of("message", "User registered: " + request.getName()));
    }
}