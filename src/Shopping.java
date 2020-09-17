import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This class handles the User Interface for the Grocery project 
 * @author Julian Romero, Steven Nguyen
 */
public class Shopping {
	private ShoppingBag shoppingBag;
	
	/**
	 * Constructs a shopping session with a shopping bag
	 */
	private Shopping() {
		shoppingBag = new ShoppingBag();
	}
	
	/**
	 * Handles the A command<br>
	 * Adds an item from the shopping bag given the name, price and if taxable
	 * @param name		the name of the item
	 * @param price		the price of the item
	 * @param taxable	whether the item is taxable
	 */
	private void add(String name, double price, boolean taxable) {
		GroceryItem item = new GroceryItem(name, price, taxable);
		this.shoppingBag.add(item);
		return;
	}
	
	/**
	 * Handles the R command<br>
	 * Removes an item from the shopping bag given the name, price and if taxable
	 * @param name		the name of the item
	 * @param price		the price of the item
	 * @param taxable	whether the item is taxable
	 */
	private void remove(String name, double price, boolean taxable) {
		GroceryItem item = new GroceryItem(name, price, taxable);
		this.shoppingBag.remove(item);
		return;
	}
	
	/**
	 * Handles the P command<br>
	 * Outputs the number of items and the items
	 */
	private void display() {
		int itemCount = this.shoppingBag.getItemCount();
		
		System.out.println("**You have " + itemCount + " in the bag.");
		this.shoppingBag.print();
		System.out.println("**End of the list");
		
		return;
	}
	
	/**
	 * Gets if the units of a phrase should say "item" or "items" given the number of items
	 * @param itemCount	the number of items
	 * @return		"item" if itemCount is 1 or less, "items" otherwise
	 */
	private static String getItemOrItems(int itemCount) {
		if (Math.abs(itemCount) <= 1) {
			return "item";
		} else {
			return "items";
		}
	}
	
	/**
	 * Handles the C command<br>
	 * Outputs the shopping bag, then the sales total, sales tax, and total amount
	 */
	private void checkingOut() {
		int itemCount = this.shoppingBag.getItemCount();
		String unit = getItemOrItems(itemCount);
		
		String priceFormat = "$##.##";
		DecimalFormat formattedPrice = new DecimalFormat(priceFormat);
		double salesPrice = this.shoppingBag.salesPrice();
		double salesTax = this.shoppingBag.salesTax();
		double amountPaid = salesPrice + salesTax;
		String salesTotalStr = formattedPrice.format(salesPrice);
		String salesTaxStr = formattedPrice.format(salesTax);
		String amountPaidStr = formattedPrice.format(amountPaid);
		
		System.out.println("**Checking out " + itemCount + " " + unit + ".");
		this.shoppingBag.print();
		System.out.println("*Sales total: $" + salesTotalStr);
		System.out.println("*Sales tax: $" + salesTaxStr);
		System.out.println("*Amount paid: $" + amountPaidStr);
		
		this.shoppingBag.clear();
		return;
	}
	
	/**
	 * Handles a single command input by the client<br>
	 * Checks if is a valid command, then calls the respective method
	 * @param input	the string inputted by the client
	 * @return		true if the command Q was not used to continue the loop in run(), false otherwise
	 */
	private boolean handleCommand(String input) {
		// Splits the input by whitespace
		String[] args = input.split("\\s+", 0);
		
		// Checks if a command was inputted
		if (args.length == 0) {
			System.out.println("Invalid Command!");
			return true;
		}
		String command = args[0];
		
		// Note, assume that number and type of arguments are valid
		// Given a command, calls the respective method
		switch(command) {
		case "A":
			this.add(args[1], Double.parseDouble(args[2]), Boolean.parseBoolean(args[3]));
			break;
		case "R":
			this.remove(args[1], Double.parseDouble(args[2]), Boolean.parseBoolean(args[3]));
			break;
		case "P":
			this.display();
			break;
		case "C":
			this.checkingOut();
			break;
		case "Q":
			System.out.println("Thanks for shopping with us!");
			// Return false to break the loop in run()
			return false;
		default:
			System.out.println("Invalid Command!");
		}
		
		// Return true to continue loop in run()
		return true;
	}

	/**
	 * Starts the loop to handle I/O<br>
	 * The program awaits a command, then responds with the respective function
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		// Repeat getting and responding to inputs until the Q command
		String input;
		do {
			input = sc.nextLine();
		} while (handleCommand(input));
		
		sc.close();
		return;
	}

	/**
	 * Tests the Shopping class's functions
	 * @param args	command line arguments, not used
	 */
	public static void main(String[] args) {
		new Shopping().run();
		return;
	}
}
