package edu.ICS372.gps1.Mohamed.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import edu.ICS372.gps1.Mohamed.Store.Business.Exceptions.ProductCustomExceptions;
import edu.ICS372.gps1.Mohamed.Store.Business.facade.Request;
import edu.ICS372.gps1.Mohamed.Store.Business.facade.Result;
import edu.ICS372.gps1.Mohamed.Store.Business.facade.Store;

public class UserInterface {
	private static UserInterface singletonUserInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Store groceryStore;
	private static final int EXIT = 0;
	private static final int ENROLL_MEMBER = 1;
	private static final int RETREIVE_MEMBER = 2;
	private static final int LIST_ALL_MEMBERS = 3;
	private static final int REMOVE_MEMBER = 4;
	private static final int ADD_PRODUCT = 5;
	private static final int CHANGE_PRODUCT_PRICE = 6;
	private static final int RETREIVE_PRODUCT = 7;
	private static final int LIST_ALL_PRODUCTS = 8;
	private static final int ADD_TO_SHOPPING_CART = 9;
	private static final int LIST_OUTSTANDING_ORDERS = 10;
	private static final int SHOPPINGCART_MENU = 11;
	private static final int CHECK_OUT = 12;
	private static final int PROCESS_SHIPMENT = 13;
	private static final int REMOVE_PRODUCT = 14;
	private static final int PRINT_TRANSACTIONS = 15;
	private static final int SAVE = 16;
	private static final int HELP = 17;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Library object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			groceryStore = Store.instance();
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (singletonUserInterface == null) {
			return singletonUserInterface = new UserInterface();
		} else {
			return singletonUserInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Gets a name after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				System.out.println(line);
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	public double getPrice(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {

		System.out.println("Enter a number between 0 and 14 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ENROLL_MEMBER + " to enroll a new member");
		System.out.println(RETREIVE_MEMBER + " to retreive a member");
		System.out.println(LIST_ALL_MEMBERS + " to list all members");
		System.out.println(REMOVE_MEMBER + " to remove a member");
		System.out.println(ADD_PRODUCT + " to add a product");
		System.out.println(CHANGE_PRODUCT_PRICE + " to change a product price");
		System.out.println(RETREIVE_PRODUCT + " to retreive a product");
		System.out.println(LIST_ALL_PRODUCTS + " to list all products");
		System.out.println(LIST_OUTSTANDING_ORDERS + " to list all outstanding orders");
		System.out.println(ADD_TO_SHOPPING_CART + " to add to shopping shopping cart");
		System.out.println(SHOPPINGCART_MENU + " to add to shopping shopping cart");
		System.out.println(CHECK_OUT + " to check out shopping cart");
		System.out.println(PROCESS_SHIPMENT + " to process shipment");
		System.out.println(REMOVE_PRODUCT + " to remove a product");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding a member. Prompts the user for the appropriate
	 * values and uses the appropriate Library method for adding the member.
	 * 
	 */
//	public void addMember() {
//		Request.instance().setMemberName(getName("Enter member name"));
//		Request.instance().setMemberAddress(getName("Enter address"));
//		Request.instance().setMemberPhone(getName("Enter phone"));
//		Result result = groceryStore.addMember(Request.instance());
//		if (result.getResultCode() == Result.MEMBER_ADDED) {
//			System.out.println("Member: " + result.getMemberName() + " added");
//		} else {
//			System.out.println(result.getMemberName() + " was not added ");
//		}
//	}

//	private void removeMember() {
//		Request.instance().setMemberId(Integer.parseInt("Enter member id"));
//		Result result = groceryStore.removeMember(Request.instance());
//		if (result.getResultCode() == Result.SUCCESS) {
//			System.out.println("Member: " + result.getMemberName() + " added");
//		} else {
//			System.out.println("Member could not be added");
//		}
//
//	}

//	private void retreiveMember() {
//		System.out.println("Line 263, UI.retreiveMember()");
//		Request.instance().setMemberId(Integer.parseInt(getToken("Enter member id")));
//		System.out.println("Line 265, memberId = " + Request.instance().getMemberId());
//		Result result = groceryStore.retreiveMember(Request.instance());
//		if (result.getResultCode() == Result.MEMBER_FOUND) {
//			System.out.println("Member was retreived");
//			System.out.println("[Member: " + result.getMemberName() + ", MemberId: " + result.getMemberId()
//					+ ", Address: " + result.getMemberAddress() + ", Phone Number: " + result.getMemberPhone() + "]");
//		} else {
//			System.out.println("Line 273, Fail, resultCode = " + result.getResultCode());
//		}
//	}

	/**
	 * Method to be called for adding a product. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * product.
	 * 
	 * @throws ProductCustomExceptions
	 * 
	 * @modified by Abshir
	 */
	public void addProduct() throws ProductCustomExceptions {
		do {
			Request.instance().setProductName(getName("Enter  name"));
			Request.instance().setProductId((getNumber("Enter product id")));
			Request.instance().setProductPrice((getPrice("Enter product price")));
			Request.instance().setProductMinimumReorderLevel(getNumber("Enter the product quantity"));
			Result result = groceryStore.addProduct(Request.instance());
			if (result.getResultCode() != Result.SUCCESS) {
				System.out.println("Product could not be added");
			} else {
				System.out.println("Product " + result.getProductName() + " added");
			}
		} while (yesOrNo("Add more books?"));
	}

	/**
	 * Method to be called for displaying transactions. Prompts the user for the
	 * appropriate values and uses the appropriate Library method for displaying
	 * transactions.
	 * 
	 */
//	public void getTransactions() {
//		Request.instance().setMemberId(Integer.parseInt("Enter member id"));
//		Request.instance().setDate(getDate("Please enter the date for which you want records as mm/dd/yy"));
//		Iterator<Result> result = groceryStore.getTransactions(Request.instance());
//		while (result.hasNext()) {
//			Result transaction = result.next();
//			System.out.println(transaction.getTransactionType() + "   " + transaction.getProductName() + "\n");
//		}
//		System.out.println("\n End of transactions \n");
//	}

	/**
	 * Displays all members
	 */
//	public void listMembers() {
//		Iterator<Result> iterator = groceryStore.getMembers();
//		System.out.println("List of members (name, address, phone, id)");
//		while (iterator.hasNext()) {
//			Result result = iterator.next();
//			System.out.println(result.getMemberName() + " " + result.getMemberAddress() + " " + result.getMemberPhone()
//					+ " " + result.getMemberId());
//		}
//		System.out.println("End of listing");
//	}

	/**
	 * Gets and prints all products.
	 * 
	 * @modified by Abshir
	 */
	public void getProductList() {
		Iterator<Result> iterator = groceryStore.getProductList();
		System.out.println("List All Products [Name, product id, price, quantity]");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println("[" + result.getProductName() + ", " + result.getProductId() + ", "
					+ result.getProductPrice() + ", " + result.getProductMinimumReorderLevel() + "]");
		}
		System.out.println("End of listing");
	}

	/**
	 * Method to be called for saving the Library object. Uses the appropriate
	 * Library method for saving.
	 * 
	 */
	private void save() {
		if (Store.save()) {
			System.out.println(" The library has been successfully saved in the file LibraryData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Library
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (groceryStore == null) {
				groceryStore = Store.retrieve();
				if (groceryStore != null) {
					System.out.println(
							" The groceryStore has been successfully retrieved from the file GroceryStoreData \n");
				} else {
					System.out.println("File doesnt exist; creating new groceryStore");
					groceryStore = Store.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	public void process() throws ProductCustomExceptions {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
//			case ENROLL_MEMBER:
//				addMember();
//				break;
//			case RETREIVE_MEMBER:
//				retreiveMember();
//				break;
//			case LIST_ALL_MEMBERS:
//				listMembers();
//				break;
//			case REMOVE_MEMBER:
//				removeMember();
//				break;
			case ADD_PRODUCT:
				addProduct();
				break;
			case CHANGE_PRODUCT_PRICE:
				changeProductPrice();
				break;
			case RETREIVE_PRODUCT:
				retreiveProduct();
				break;
			case REMOVE_PRODUCT:
				removeProduct();
				break;

			case LIST_ALL_PRODUCTS:
				getProductList();
				break;
//			case LIST_OUTSTANDING_ORDERS:
//				processOrders();
//				break;
//			case SHOPPINGCART_MENU:
//				ShoppingCartMenu();
//				break;
//			case PROCESS_SHIPMENT:
//				processShipment();
//				break;
//			case PRINT_TRANSACTIONS:
//				printTransactions();
//				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

//	public void ShoppingCartMenu() {
//		int command;
//		int customerId = 0;
//		getProductList();
//		shoppingCartHelp();
//		while ((command = getCommand()) != EXIT) {
//			switch (command) {
//			case LIST_ALL_PRODUCTS:
//				getProductList();
//				break;
//			case ADD_TO_SHOPPING_CART:
//				customerId = addToShoppingCart();
//				break;
//			case CHECK_OUT:
//				checkOutShoppingCart(customerId);
//				break;
//			case HELP:
//				help();
//				break;
//			}
//		}
//	}

//	public void shoppingCartHelp() {
//		final int GO_BACK = 0;
//		final int ADD_TO_SHOPPING_CART = 1;
//		final int CHECK_OUT = 2;
//		final int PRINT_TRANSACTIONS = 3;
//		final int HELP = 4;
//
//		System.out.println("Enter a number between 0 and 4 as explained below:");
//		System.out.println(GO_BACK + " to go back previous menu\n");
//		System.out.println(ADD_TO_SHOPPING_CART + " to add item to shopping shopping cart");
//		System.out.println(CHECK_OUT + " to check out shopping cart");
//		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
//		System.out.println(HELP + " for help");
//}

//	private int addToShoppingCart() {
//		int customerId = Integer.parseInt(getToken("Enter memberId"));
//		Request.instance().setMemberId(customerId);
//		Request.instance().setProductId(Integer.parseInt(getToken("Enter product id")));
//		Request.instance().setProductAmountInCart(Integer.parseInt(getToken("Enter quantity")));
//		return customerId;
//	}
//
//	private void printTransactions() {
//		// TODO Auto-generated method stub
//
//	}
//
//	private void processShipment() {
//		// TODO Auto-generated method stub
//
//	}

//	private void checkOutShoppingCart(int customerId) {
//		double total = 0;
//		Request.instance().setMemberId(customerId);
//		Iterator<Result> iterator = groceryStore.getShoppingCart(Request.instance());
//		while (iterator.hasNext()) {
//			Result result = iterator.next();
//			// total += result.computeTotal();
//		}
//		System.out.println("Total cost is " + total);
//	}

//	private void processOrders() {
//		// TODO Auto-generated method stub
//
//	}
//
	/*
	 * Retrieve a specific product from ProductList
	 * 
	 * @modified by Abshir
	 * 
	 */

	private void retreiveProduct() {
		System.out.println("Line 263, UI.retreiveMember()");
		Request.instance().setProductId(getNumber("Enter product id"));
		System.out.println("Line 265, ProductId = " + Request.instance().getProductId());
		Result result = groceryStore.retreiveProduct(Request.instance());
		if (result.getResultCode() == Result.PRODUCT_FOUND) {
			System.out.println("Product was retreived");
			System.out.println("[ProductId: " + result.getProductId() + ", ProductName: " + result.getProductName()
					+ ", getProductPrice: " + result.getProductPrice() + ", ProductQuanitity: "
					+ result.getProductMinimumReorderLevel() + "]");
		} else {
			System.out.println("Line 273, Fail, resultCode = " + result.getResultCode());
		}

	}

	/*
	 * Remove a specific product from the list
	 * 
	 * Modified by Abshir
	 */

	private void removeProduct() {
		Request.instance().setProductId(getNumber("Enter Product Id"));
		Result result = groceryStore.removeProduct(Request.instance());
		if (result.getResultCode() == Result.SUCCESS) {
			System.out.println("Product: " + result.getProductId() + " Removed");
		} else {
			System.out.println("Product could not be added");
		}

	}

//
	private void changeProductPrice() {
		Request.instance().setProductId(getNumber("Enter product id"));
		System.out.println("Line 265, ProductId = " + Request.instance().getProductId());
		Result result = new Result();
		// System.out.println(result.getProductPrice());

		// need evaluation
		// Request.instance().setProductPrice((getPrice("Enter product price")));
		Request.instance().setProductPrice(getPrice("Enter the new Price"));
		result = groceryStore.retreiveProduct(Request.instance());
		if (result.getResultCode() == Result.PRODUCT_FOUND) {

			// Request.instance().setNewPrice((getPrice("Enter product price")));
			System.out.println(
					"ProductName: " + result.getProductName() + ", getProductPrice: " + result.getProductPrice());
		} else {
			System.out.println("Line 273, Fail, resultCode = " + result.getResultCode());
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 * @throws ProductCustomExceptions
	 */
	public static void main(String[] args) {
		try {
			UserInterface.instance().process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}