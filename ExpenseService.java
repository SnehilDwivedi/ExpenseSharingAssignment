package com.expensesharing;

public class ExpenseService {

    private BalanceSheet sheet = new BalanceSheet();

    public void addExpense(Expense e) {
        User paidBy = e.getPaidBy();
        for (Split s : e.getSplits()) {
            if (!s.getUser().getUserId().equals(paidBy.getUserId())) {
                sheet.add(s.getUser().getName(),
                          paidBy.getName(),
                          s.getAmount());
            }
        }
    }

    public BalanceSheet getSheet() {
        return sheet;
    }
}
