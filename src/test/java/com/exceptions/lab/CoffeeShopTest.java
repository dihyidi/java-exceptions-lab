package com.exceptions.lab;

import com.exceptions.lab.exceptions.CoffeeOutOfStockException;
import com.exceptions.lab.exceptions.InappropriateFeedbackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopTest {
    private CoffeeShop coffeeShop;

    @BeforeEach
    void setUp() {
        coffeeShop = new CoffeeShopImpl(1);
    }

    @Test
    void buyCoffee() throws Exception {
        // arrange
        // act
        coffeeShop.buyCoffee();
        int coffeeLeft = coffeeShop.coffeeAmount();

        // assert
        assertEquals(0, coffeeLeft);
    }

    @Test
    void buyCoffee_throwsException() {
        // arrange
        coffeeShop = new CoffeeShopImpl(0);

        // act & assert
        assertThrows(CoffeeOutOfStockException.class, () -> coffeeShop.buyCoffee(), "No coffee available!");
    }

    @Test
    void buyCoffeeIfPresent_returnsTrue() {
        // arrange
        // act
        boolean result = coffeeShop.buyCoffeeIfPresent();

        // assert
        assertTrue(result);
    }

    @Test
    void buyCoffeeIfPresent_returnsFalse() {
        // arrange
        coffeeShop = new CoffeeShopImpl(0);

        // act
        boolean result = coffeeShop.buyCoffeeIfPresent();

        // assert
        assertFalse(result);
    }

    @Test
    void giveFeedback() {
        // arrange
        String feedback = "The best coffee ever!";

        // act
        coffeeShop.giveFeedback(feedback);
        List<String> feedbacks = coffeeShop.readFeedbacks();

        // assert
        assertTrue(feedbacks.contains(feedback));
    }

    @Test
    void giveFeedback_throwsException() {
        // arrange
        String feedback = "The worst coffee ever! It is bad, not tasty and not drinkable!";

        // act & assert
        assertThrows(InappropriateFeedbackException.class, () -> coffeeShop.giveFeedback(feedback), "Feedback cannot be saved.");
    }
}