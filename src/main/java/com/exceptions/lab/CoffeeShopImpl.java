package com.exceptions.lab;

import com.exceptions.lab.exceptions.CoffeeOutOfStockException;
import com.exceptions.lab.exceptions.InappropriateFeedbackException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoffeeShopImpl implements CoffeeShop {
    private int coffeeStock;
    private final List<String> feedbacks = new ArrayList<>();
    private static final List<String> BAD_WORDS = Arrays.asList("bad", "worst", "not tasty", "not drinkable");

    public CoffeeShopImpl(int initialStock) {
        this.coffeeStock = initialStock;
    }

    @Override
    public void buyCoffee() throws CoffeeOutOfStockException {
        if (coffeeStock <= 0) {
            throw new CoffeeOutOfStockException("No coffee available!");
        }
        coffeeStock--;
    }

    @Override
    public boolean buyCoffeeIfPresent() {
        if (coffeeStock > 0) {
            coffeeStock--;
            return true;
        }
        return false;
    }

    @Override
    public void giveFeedback(String feedback) throws InappropriateFeedbackException {
        for (String badWord : BAD_WORDS) {
            if (feedback.toLowerCase().contains(badWord)) {
                throw new InappropriateFeedbackException("Feedback cannot be saved.");
            }
        }
        feedbacks.add(feedback);
    }

    @Override
    public List<String> readFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public int coffeeAmount() {
        return coffeeStock;
    }
}
