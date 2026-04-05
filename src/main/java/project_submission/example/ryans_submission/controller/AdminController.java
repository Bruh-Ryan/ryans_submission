package project_submission.example.ryans_submission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project_submission.example.ryans_submission.model.Users;
import project_submission.example.ryans_submission.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import project_submission.example.ryans_submission.service.UserService;

import java.util.List;

//1.temporay solution no DB is used till now 3rd april 1.37am

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    // public  DashboardSummary dashboard() {
    public String dashboard(){ 
        //Later: when we implement DB will not return string
        return "Welcome Admin! Total Users: 0 | Total Transactions: 0 | Revenue: $0.00";
    }


    @GetMapping("/users")
    public Page <Users> getAllUsers(
        @RequestParam(defaultValue = "0") int page,    // which page (0 = first)
        @RequestParam(defaultValue = "10") int size     // how many per page
    ) {
        return userService.getUsersPaginated(page, size);
    }

    @GetMapping("/id_from_user")
    public ResponseEntity<?> getIdFromUser(
            @RequestParam String email,
            @RequestParam String name) {
        try {
            Long id = userService.getUserId(email, name);
            // Returns ONLY the id — no name, email, role, password exposed
            return ResponseEntity.ok(Map.of("id", id));
        } catch (RuntimeException e) {
            // Vague on purpose — don't confirm whether email or name was wrong
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }
    // @GetMapping("/users_from_region")
    // public ResponseEntity<?> getUsersFromCity(@RequestParam String city){
    //     try{
            
    //     } catch(RuntimeException e){
    //         return ResponseEntity.status(404).body(Map.of("error", "Not found in this region"));
    //     }

    // }
   @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Later: repo.deleteById(id);
        return "Deleted user with id: " + id;
    }
}
