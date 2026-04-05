package project_submission.example.ryans_submission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import project_submission.example.ryans_submission.model.TransactionRequest;
import project_submission.example.ryans_submission.model.Transaction;
import project_submission.example.ryans_submission.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Transaction successful"),
    @ApiResponse(responseCode = "400", description = "Insufficient balance or invalid type"),
    @ApiResponse(responseCode = "404", description = "User not found")
    })

    // POST /api/transactions — add a CREDITED or WITHDRAWN transaction
    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest req) {
        try {
            Transaction tx = transactionService.addTransaction(req);
            return ResponseEntity.ok(tx);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/transactions/user/{userId} — full history
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTransactions(@PathVariable Long userId) {
        try {
            List<Transaction> list = transactionService.getTransactionsByUser(userId);
            return ResponseEntity.ok(list);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/transactions/user/{userId}?type=CREDITED or ?type=WITHDRAWN
    @GetMapping("/user/{userId}/filter")
    public ResponseEntity<?> getTransactionsByType(
            @PathVariable Long userId,
            @RequestParam String type) {
        try {
            List<Transaction> list = transactionService.getTransactionsByType(userId, type);
            return ResponseEntity.ok(list);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/transactions/user/1/date?from=2026-01-01T00:00:00&to=2026-04-04T23:59:59
    
    @GetMapping("/user/{userId}/date")
    public ResponseEntity<?> getTransactionsByDate(
            @PathVariable Long userId,
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to,
            @RequestParam(required = false) String type) {
        try {
            List<Transaction> list;
            if (type != null) {
                list = transactionService.getTransactionsByTypeAndDateRange(userId, type, from, to);
            } else {
                list = transactionService.getTransactionsByDateRange(userId, from, to);
            }
            return ResponseEntity.ok(list);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
} 
