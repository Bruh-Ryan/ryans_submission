package project_submission.example.ryans_submission.model;

public class TransactionRequest {

    private Long userId;

    // "CREDITED" or "WITHDRAWN"
    private String type;

    private java.math.BigDecimal amount;

    // ── Getters & Setters ──────────────────────────

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public java.math.BigDecimal getAmount() { return amount; }
    public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
} 
