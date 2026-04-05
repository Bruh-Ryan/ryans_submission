package project_submission.example.ryans_submission.model;

public class DashboardSummary {
    private int totalUsers;
    private int totalTransactions;
    private double totalRevenue;
    private double totalExpenses;

    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(int t) { this.totalTransactions = t; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    public double getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(double totalExpenses) { this.totalExpenses = totalExpenses; }
}
