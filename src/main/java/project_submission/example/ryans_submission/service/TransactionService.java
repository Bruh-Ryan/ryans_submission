package project_submission.example.ryans_submission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_submission.example.ryans_submission.model.TransactionRequest;
import project_submission.example.ryans_submission.model.Transaction;
import project_submission.example.ryans_submission.model.Users;
import project_submission.example.ryans_submission.repository.TransactionRepository;
import project_submission.example.ryans_submission.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Transaction addTransaction(TransactionRequest req) {

        //  Find the user
        Users user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Check sufficient balance before withdrawing
        if (req.getType().equals("WITHDRAWN")) {
            if (user.getBalance().compareTo(req.getAmount()) < 0) {
                throw new RuntimeException("Insufficient balance");
            }
            user.setBalance(user.getBalance().subtract(req.getAmount()));
        } else if (req.getType().equals("CREDITED")) {
            user.setBalance(user.getBalance().add(req.getAmount()));
        } else {
            throw new RuntimeException("Invalid type. Use CREDITED or WITHDRAWN");
        }

        // Save updated balance
        userRepository.save(user);

        // Record the transaction
        Transaction tx = new Transaction();
        tx.setUser(user);
        tx.setType(req.getType());
        tx.setAmount(req.getAmount());

        return transactionRepository.save(tx);
    }

    // Get full transaction history for a user
    public List<Transaction> getTransactionsByUser(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }

    // get only CREDITED or only WITHDRAWN
    public List<Transaction> getTransactionsByType(Long userId, String type) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUserAndType(user, type);
    }

    // transaction date range
    public List<Transaction> getTransactionsByDateRange(
            Long userId, LocalDateTime from, LocalDateTime to) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUserAndCreatedAtBetween(user, from, to);
    }
    // transaction type with date range
    public List<Transaction> getTransactionsByTypeAndDateRange(
            Long userId, String type, LocalDateTime from, LocalDateTime to) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUserAndTypeAndCreatedAtBetween(user, type, from, to);
    }
}
