/*Task 2
 * Task 2 - Build A Receipt Calculator In Java
 * Core Functionalities:

Accept input for items (name, price, quantity).
Calculate subtotal, tax, discount, and final total.
Generate and display the receipt with a list of items, prices, total cost, tax, and discount.
Save the receipt as a text file or PDF (bonus).
Technical Specifications:

Use Java (Java SE 8 or above).
Implement object-oriented programming concepts.
Use collections like ArrayList or HashMap for managing products.
File handling for saving and retrieving receipt data.*/
package task2_calculator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Class representing an item in the receipt
class Item {
 private String name;
 private double price;
 private int quantity;

 public Item(String name, double price, int quantity) {
     this.name = name;
     this.price = price;
     this.quantity = quantity;
 }

 public double getTotalPrice() {
     return price * quantity;
 }

 public String getName() {
     return name;
 }

 public double getPrice() {
     return price;
 }

 public int getQuantity() {
     return quantity;
 }
}

//Main class for the receipt calculator
	public class ReceiptCalculator {
		private List<Item> items;
		
		public ReceiptCalculator() {
	        items = new ArrayList<>();
	    }

	    // Method to add an item
	    public void addItem(String name, double price, int quantity) {
	        items.add(new Item(name, price, quantity));
	    }

	    // Method to calculate subtotal
	    public double calculateSubtotal() {
	        double subtotal = 0.0;
	        for (Item item : items) {
	            subtotal += item.getTotalPrice();
	        }
	        return subtotal;
	    }
	    
	 // Method to calculate total with tax and discount
	    public double calculateTotal(double taxRate, double discountRate) {
	        double subtotal = calculateSubtotal();
	        double tax = subtotal * (taxRate / 100);
	        double discount = subtotal * (discountRate / 100);
	        return subtotal + tax - discount;
	    }
	    
	    // Method to display and save the receipt
	    public void displayReceipt(double taxRate, double discountRate) {
	        double subtotal = calculateSubtotal();
	        double tax = subtotal * (taxRate / 100);
	        double discount = subtotal * (discountRate / 100);
	        double total = subtotal + tax - discount;
	        
	        StringBuilder receipt = new StringBuilder();
	        receipt.append("----- Receipt -----\n");
	        for (Item item : items) {
	            receipt.append(String.format("%s: $%.2f x %d = $%.2f%n", item.getName(), item.getPrice(), item.getQuantity(), item.getTotalPrice()));
	        }
	        receipt.append(String.format("Subtotal: $%.2f%n", subtotal));
	        receipt.append(String.format("Tax (%.2f%%): $%.2f%n", taxRate, tax));
	        receipt.append(String.format("Discount (%.2f%%): -$%.2f%n", discountRate, discount));
	        receipt.append(String.format("Total: $%.2f%n", total));
	        receipt.append("-------------------\n");

	        System.out.println(receipt);
	        
	     // Save to file
	        saveReceiptToFile(receipt.toString());
	    }

	    // Method to save the receipt to a text file
	    private void saveReceiptToFile(String receipt) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
	            writer.write(receipt);
	            System.out.println("Receipt saved as receipt.txt");
	        } catch (IOException e) {
	            System.err.println("Error saving receipt: " + e.getMessage());
	        }
	    }
	    
	 // Main method to run the program
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        ReceiptCalculator receiptCalculator = new ReceiptCalculator();

	        System.out.print("Enter the number of items: ");
	        int numberOfItems = scanner.nextInt();
	        scanner.nextLine();  // Consume newline
	        
	     // Input item details
	        for (int i = 0; i < numberOfItems; i++) {
	            System.out.printf("Enter name for item %d: ", i + 1);
	            String name = scanner.nextLine();
	            System.out.printf("Enter price for item %d: $", i + 1);
	            double price = scanner.nextDouble();
	            System.out.printf("Enter quantity for item %d: ", i + 1);
	            int quantity = scanner.nextInt();
	            scanner.nextLine();  // Consume newline
	            receiptCalculator.addItem(name, price, quantity);
	        }
	        
	        // Input tax rate
	        System.out.print("Enter the tax rate (in percentage): ");
	        double taxRate = scanner.nextDouble();

	        // Input discount percentage
	        System.out.print("Enter the discount percentage: ");
	        double discountRate = scanner.nextDouble();

	        // Display the receipt
	        receiptCalculator.displayReceipt(taxRate, discountRate);

	        scanner.close();
	    }
}
