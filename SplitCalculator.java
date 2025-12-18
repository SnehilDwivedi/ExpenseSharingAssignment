package com.expensesharing;


import java.util.*;

public class SplitCalculator {

    // EQUAL
    public static List<Split> equal(List<User> users, double total) {
        double share = total / users.size();
        List<Split> splits = new ArrayList<>();
        for (User u : users) splits.add(new Split(u, share));
        return splits;
    }

    // EXACT
    public static List<Split> exact(Map<User, Double> exactAmounts) {
        List<Split> splits = new ArrayList<>();
        for (Map.Entry<User, Double> e : exactAmounts.entrySet()) {
            splits.add(new Split(e.getKey(), e.getValue()));
        }
        return splits;
    }

    // PERCENT
    public static List<Split> percent(Map<User, Double> percents, double total) {
        double sum = 0;
        for (double p : percents.values()) sum += p;
        if (sum != 100) throw new RuntimeException("Percent must be 100");

        List<Split> splits = new ArrayList<>();
        for (Map.Entry<User, Double> e : percents.entrySet()) {
            double amount = (e.getValue() * total) / 100;
            splits.add(new Split(e.getKey(), amount));
        }
        return splits;
    }
}
