package com.expensesharing;

import java.util.*;

public class BalanceSheet {

    // balance[A][B] = A owes B
    private Map<String, Map<String, Double>> balance = new HashMap<>();

    public void add(String from, String to, double amount) {
        if (from.equals(to)) return;
        balance.putIfAbsent(from, new HashMap<>());
        balance.get(from).put(to,
                balance.get(from).getOrDefault(to, 0.0) + amount);
    }

    public void settle(String from, String to, double amount) {
        if (!balance.containsKey(from)) return;
        double cur = balance.get(from).getOrDefault(to, 0.0);
        double rem = cur - amount;
        if (rem <= 0) balance.get(from).remove(to);
        else balance.get(from).put(to, rem);
    }

    // SIMPLIFY BALANCES
    public void simplify() {
        Map<String, Double> net = new HashMap<>();

        for (String from : balance.keySet()) {
            for (String to : balance.get(from).keySet()) {
                double amt = balance.get(from).get(to);
                net.put(from, net.getOrDefault(from, 0.0) - amt);
                net.put(to, net.getOrDefault(to, 0.0) + amt);
            }
        }

        balance.clear();

        List<String> debtors = new ArrayList<>();
        List<String> creditors = new ArrayList<>();

        for (String u : net.keySet()) {
            if (net.get(u) < 0) debtors.add(u);
            else if (net.get(u) > 0) creditors.add(u);
        }

        int i = 0, j = 0;
        while (i < debtors.size() && j < creditors.size()) {
            String d = debtors.get(i);
            String c = creditors.get(j);

            double owe = -net.get(d);
            double get = net.get(c);
            double min = Math.min(owe, get);

            add(d, c, min);

            net.put(d, net.get(d) + min);
            net.put(c, net.get(c) - min);

            if (net.get(d) == 0) i++;
            if (net.get(c) == 0) j++;
        }
    }
    // USER WISE BALANCE VIEW
    public void showUserBalance(String user) {
        double youOwe = 0.0;
        double youGet = 0.0;

        // How much user owes others
        if (balance.containsKey(user)) {
            for (double amt : balance.get(user).values()) {
                youOwe += amt;
            }
        }

        // How much others owe user
        for (String from : balance.keySet()) {
            if (balance.get(from).containsKey(user)) {
                youGet += balance.get(from).get(user);
            }
        }

        System.out.println("---- Balance for " + user + " ----");
        System.out.println("You owe : ₹" + youOwe);
        System.out.println("You will get : ₹" + youGet);
    }


    public void show() {
        for (String f : balance.keySet()) {
            for (String t : balance.get(f).keySet()) {
                System.out.println(f + " owes " + t + " : ₹" + balance.get(f).get(t));
            }
        }
    }
}
