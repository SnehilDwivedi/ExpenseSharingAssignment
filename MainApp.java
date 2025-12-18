package com.expensesharing;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        // Users
        User A = new User("A");
        User B = new User("B");
        User C = new User("C");

        // Expense Service
        ExpenseService service = new ExpenseService();

        // ----------------------
        // 1️⃣ EQUAL SPLIT
        // ----------------------
        service.addExpense(new Expense(
                A, 900, SplitType.EQUAL,
                SplitCalculator.equal(Arrays.asList(A,B,C), 900)
        ));

        // ----------------------
        // 2️⃣ EXACT SPLIT
        // ----------------------
        Map<User, Double> exact = new HashMap<>();
        exact.put(B, 300.0);
        exact.put(C, 200.0);
        exact.put(A, 500.0);
        service.addExpense(new Expense(
                A, 1000, SplitType.EXACT,
                SplitCalculator.exact(exact)
        ));

        // ----------------------
        // 3️⃣ PERCENT SPLIT
        // ----------------------
        Map<User, Double> percent = new HashMap<>();
        percent.put(A, 50.0);
        percent.put(B, 25.0);
        percent.put(C, 25.0);
        service.addExpense(new Expense(
                A, 2000, SplitType.PERCENT,
                SplitCalculator.percent(percent, 2000)
        ));

        // ----------------------
        // 4️⃣ Simplify & Show all balances
        // ----------------------
        System.out.println("\n--- Simplified Balances ---");
        service.getSheet().simplify();
        service.getSheet().show();

        // ----------------------
        // 5️⃣ Show User-wise balances
        // ----------------------
        System.out.println("\n--- User-wise Balances ---");
        service.getSheet().showUserBalance("A");
        service.getSheet().showUserBalance("B");
        service.getSheet().showUserBalance("C");

        // ----------------------
        // 6️⃣ Settlement Example
        // ----------------------
        System.out.println("\n--- Settlement: B pays A ₹300 ---");
        service.getSheet().settle("B", "A", 300);

        // Show updated balances
        System.out.println("\n--- Updated Simplified Balances ---");
        service.getSheet().show();

        System.out.println("\n--- Updated User-wise Balances ---");
        service.getSheet().showUserBalance("A");
        service.getSheet().showUserBalance("B");
        service.getSheet().showUserBalance("C");
    }
}
