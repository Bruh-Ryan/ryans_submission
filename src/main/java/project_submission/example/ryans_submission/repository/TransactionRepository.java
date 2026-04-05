package project_submission.example.ryans_submission.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project_submission.example.ryans_submission.model.Transaction;
import project_submission.example.ryans_submission.model.Users;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Get all transactions for a specific user
    List<Transaction> findByUser(Users user);

    // Get all transactions for a user filtered by type
    List<Transaction> findByUserAndType(Users user, String type);

    List<Transaction> findByUserAndCreatedAtBetween(
        Users user,
        LocalDateTime from,
        LocalDateTime to
    );

    // Date range + type filter combined
    List<Transaction> findByUserAndTypeAndCreatedAtBetween(
        Users user,
        String type,
        LocalDateTime from,
        LocalDateTime to
    );
} 
