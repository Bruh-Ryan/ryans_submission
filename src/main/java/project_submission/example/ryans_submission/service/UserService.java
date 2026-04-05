package project_submission.example.ryans_submission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import project_submission.example.ryans_submission.model.Users;
import project_submission.example.ryans_submission.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Returns 10 users at a time, sorted by name, page 0 = first 10, page 1 = next 10
    public Page <Users> getUsersPaginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userRepository.findAll(pageable);
    }

    public Long getUserId(String email, String name) {
    return userRepository.findByEmailAndName(email, name)
            .map(Users::getId)           // extract ONLY the id
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
} 
