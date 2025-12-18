package com.expensesharing;

import java.util.List;

public class Expense {
    private User paidBy;
    private double totalAmount;
    private SplitType splitType;
    private List<Split> splits;

    public Expense(User paidBy, double totalAmount,
                   SplitType splitType, List<Split> splits) {
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.splitType = splitType;
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }
}
