package com.exceptions.lab;

import com.exceptions.lab.exceptions.CoffeeOutOfStockException;
import com.exceptions.lab.exceptions.InappropriateFeedbackException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeShopImpl coffeeShop = new CoffeeShopImpl(5);

        while (true) {
            System.out.println("\nCoffee Shop Menu:");
            System.out.println("1. Buy Coffee");
            System.out.println("2. Buy Coffee (if available)");
            System.out.println("3. Give Feedback");
            System.out.println("4. Read Feedbacks");
            System.out.println("5. Check Coffee Stock");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        coffeeShop.buyCoffee();
                        System.out.println("You bought a coffee!");
                    } catch (CoffeeOutOfStockException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (coffeeShop.buyCoffeeIfPresent()) {
                        System.out.println("You bought a coffee!");
                    } else {
                        System.out.println("No coffee available!");
                    }
                    break;
                case 3:
                    System.out.print("Enter your feedback: ");
                    String feedback = scanner.nextLine();
                    try {
                        coffeeShop.giveFeedback(feedback);
                        System.out.println("Feedback saved!");
                    } catch (InappropriateFeedbackException e) {
                        System.out.println("Feedback cannot be saved.");
                    }
                    break;
                case 4:
                    List<String> feedbacks = coffeeShop.readFeedbacks();
                    if (feedbacks.isEmpty()) {
                        System.out.println("No feedbacks yet.");
                    } else {
                        System.out.println("Feedbacks:");
                        feedbacks.forEach(System.out::println);
                    }
                    break;
                case 5:
                    System.out.println("Coffee stock: " + coffeeShop.coffeeAmount());
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}