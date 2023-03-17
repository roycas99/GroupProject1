package vang.ics372.gp1.gs.userinterface;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @author Say Chaleon Vang, Banji, Jeffrey, Abshir
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code
 *  in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import vang.ics372.gp1.grocerystore.business.facade.GroceryStore;
import vang.ics372.gp1.grocerystore.business.facade.Request;
import vang.ics372.gp1.grocerystore.business.facade.Result;

/**
 * modified by Say, Banji, Jeffrey, Abshir for use with group project1 Grocery
 * Store implementation
 * 
 * User interface for GroceryStore. commands are encoded as integers using a
 * number of static final variables. A number of utility methods exist to make
 * it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface singletonUserInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static GroceryStore groceryStore;
	private static final int EXIT = 0;
	private static final int ENROLL_MEMBER = 1;
	private static final int RETREIVE_MEMBER = 2;
	private static final int LIST_ALL_MEMBERS = 3;
	private static final int REMOVE_MEMBER = 4;
	private static final int ADD_PRODUCT = 5;
	private static final int CHANGE_PRODUCT_PRICE = 6;
	private static final int RETREIVE_PRODUCT = 7;
	private static final int LIST_ALL_PRODUCTS = 8;
	private static final int LIST_OUTSTANDING_ORDERS = 10;
	private static final int SHOPPINGCART_MENU = 11;
	private static final int PROCESS_SHIPMENT = 13;
	private static final int REMOVE_PRODUCT = 14;
	private static final int PRINT_TRANSACTIONS = 15;
	private static final int SAVE = 30;
	private static final int HELP = 31;
	// Shopping cart menu
	private static final int GO_BACK = 20;
	private static final int LIST_PRODUCTS = 21;
	private static final int ADD_TO_CART = 22;
	private static final int CHECK_OUT_CART = 23;
	private static final int DISPLAY_CART = 24;
	private static final int SHOPPING_CART_MENU = 25;

	private final double MEMBERFEE = 49.99;

	/**
	 * Private constructor, access through Instance(); Implements Singleton pattern
	 */
	private UserInterface() {
		Request request = Request.instance();
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			System.out.println("Data not loaded!\nEntering Testing Mode...");
			runTests();
			System.out.println("\nExiting testing mode...");
		}
		System.out.println("test is not working");
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
	 * @author Say Chaleon Vang
	 * 
	 *         gets the date in string format
	 * @return String
	 */
	private String getStringDate() {
		String stringDate = getToken("Enter month number") + "/" + getToken("Enter day of month") + "/"
				+ getToken("Enter 4 digit year");
		return stringDate;
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
				int value = Integer.parseInt(getToken("Enter command:"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Displays the help screen
	 * 
	 */
	public void help() {

		System.out.println("\nEnter a number between 0 and 31 as " + "explained below:");
		System.out.println(EXIT + " to Exit");
		System.out.println(ENROLL_MEMBER + " to enroll a new member");
		System.out.println(RETREIVE_MEMBER + " to retreive a member");
		System.out.println(LIST_ALL_MEMBERS + " to list all members");
		System.out.println(REMOVE_MEMBER + " to remove a member");
		System.out.println(ADD_PRODUCT + " to add a product");
		System.out.println(CHANGE_PRODUCT_PRICE + " to change a product price");
		System.out.println(RETREIVE_PRODUCT + " to retreive a product");
		System.out.println(LIST_ALL_PRODUCTS + " to list all products");
		System.out.println(LIST_OUTSTANDING_ORDERS + " to list all outstanding orders");
		System.out.println(SHOPPINGCART_MENU + " to display shopping cart memu");
		System.out.println(PROCESS_SHIPMENT + " to process shipment");
		System.out.println(REMOVE_PRODUCT + " to remove a product");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help\n");
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Method to be called for adding a member. Prompts the user for the
	 *           appropriate
	 * 
	 */
	public void addMember() {
		Request.instance().setMemberName(getName("Enter member name"));
		Request.instance().setMemberAddress(getName("Enter address"));
		Request.instance().setMemberPhone(getName("Enter phone"));
		Request.instance().setMemberFee(MEMBERFEE);
		Result result = groceryStore.addMember(Request.instance());
		if (result.getResultCode() == Result.MEMBER_ADDED) {
			System.out
					.println("[Member: " + result.getMemberName() + ", memberId = " + result.getMemberId() + "] added");
		} else {
			System.out.println("[" + result.getMemberName() + "] was not added ");
		}

	}

	/**
	 * @modified by Say Vang
	 * 
	 *           removes a member asks for memberId
	 * 
	 */
	private void removeMember() {
		Request.instance().setMemberId(Integer.parseInt(getToken("Enter member id")));
		Result result = groceryStore.removeMember(Request.instance());
		if (result.getResultCode() == Result.MEMBER_REMOVED) {
			System.out.println(
					"[Member: " + result.getMemberName() + ", memberId = " + result.getMemberId() + "] removed");
		} else {
			System.out.println("[memberId = " + Request.instance().getMemberId() + "] not found, could not be removed");
		}
		save();
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           retrieve a member ask for memberId
	 */
	private void retreiveMember() {
		Request.instance().setMemberId(Integer.parseInt(getToken("Enter member id")));
		Result result = groceryStore.retreiveMember(Request.instance());
		if (result.getResultCode() == Result.MEMBER_FOUND) {
			System.out.println("[" + result.getMemberName() + "] was retreived");
		} else {
			System.out.println("[" + result.getMemberName() + "] was not found");
		}
	}

	/**
	 * Method to be called for adding a product. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * product.
	 * 
	 * @modified by Abshir
	 */
	public void addProduct() {
		do {
			Request.instance().setProductName(getName("Enter  name"));
			Request.instance().setProductId((getNumber("Enter product id")));
			Request.instance().setProductPrice((getPrice("Enter product price")));
			Request.instance().setProductMinimumReorderLevel(getNumber("Enter minimum order level"));
			Result result = groceryStore.addProduct(Request.instance());
			if (result.getResultCode() != Result.SUCCESS) {
				System.out.println("Product could not be added");
			} else {
				System.out.println("Product " + result.getProductName() + " added");
			}
		} while (yesOrNo("Add more products?"));
		save();
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Displays all members
	 */
	public void listMembers() {
		Iterator<Result> iterator = groceryStore.getMembers();
		System.out.println("\nList of members \n[Id    , Name    , Fee   " + ", Address   , Phone   ]");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println("[" + result.getMemberId() + ", " + result.getMemberName() + ", " + result.getMemberFee()
					+ ", " + result.getMemberAddress() + ", " + result.getMemberPhone() + "]");
		}
		System.out.println("End of listing\n");
	}

	/**
	 * Gets and prints all products.
	 * 
	 * @modified by Abshir
	 */
	public void getProductList() {
		Iterator<Result> iterator = groceryStore.getProductList();
		System.out.println("List All Products\n[productId, name, price, " + "quantity in stock, " + "minimumLevel]");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(
					"[(" + result.getProductId() + "), " + result.getProductName() + ", " + result.getProductPrice()
							+ ", " + result.getProductInStock() + ", " + result.getProductMinimumReorderLevel() + "]");
		}
		System.out.println("End of listing");
	}

	/**
	 * decision switch
	 */
	public void process() {
		help();
		int command;
		while ((command = getCommand()) != EXIT) {
			help();
			switch (command) {
			case EXIT: // = 0
				break;
			case ENROLL_MEMBER: // = 1
				addMember();
				help();
				break;
			case RETREIVE_MEMBER: // = 2
				listMembers();
				retreiveMember();
				help();
				break;
			case LIST_ALL_MEMBERS: // = 3
				listMembers();
				help();
				break;
			case REMOVE_MEMBER: // = 4
				listMembers();
				removeMember();
				help();
				break;
			case ADD_PRODUCT: // = 5
				addProduct();
				help();
				break;
			case CHANGE_PRODUCT_PRICE: // = 6
				getProductList();
				changeProductPrice();
				help();
				break;
			case RETREIVE_PRODUCT: // = 7
				getProductList();
				retreiveProduct();
				help();
				break;
			case LIST_ALL_PRODUCTS: // = 8
				getProductList();
				help();
				break;
			case LIST_OUTSTANDING_ORDERS: // = 10
				listOutstandingOrders();
				help();
				break;
			case SHOPPINGCART_MENU: // = 11
				ShoppingCartMenu();
				help();
				break;
			case PROCESS_SHIPMENT: // = 13
				processShipment();
				help();
				break;
			case REMOVE_PRODUCT: // = 14
				getProductList();
				removeProduct();
				help();
				break;
			case PRINT_TRANSACTIONS: // = 15
				printTransactions();
				help();
				break;
			case SAVE: // = 30
				save();
				break;
			case HELP: // = 31
				help();
				break;
			}
		}
		save();
	}

	/*
	 * the method is able to change the price of a specific product.
	 * 
	 * @author Abshir
	 * 
	 */
	private void changeProductPrice() {

		Request.instance().setProductId(getNumber("Enter product id"));
		System.out.println("Line 265, ProductId = " + Request.instance().getProductId() + ", Product price "
				+ Request.instance().getProductPrice());

		// Setting the product price into the the new price
		Request.instance().setProductPrice(getPrice("Enter the new Price"));
		// retrieving the old product
		Result result = groceryStore.retreiveProduct(Request.instance());

		Result newResult = new Result();

		newResult = groceryStore.changeProductPrice(Request.instance());
		System.out.println(
				"The new price for the " + Request.instance().getProductName() + " is: " + newResult.getProductPrice());

	}// end of ChangePrice

	/**
	 * decision switch
	 */
	public void ShoppingCartMenu() {
		shoppingCartHelp();
		int command;
		int customerId = -1;
		do {
			System.out.println("Shopping Cart Menu");
			command = getCommand();
			switch (command) {
			case GO_BACK:
				break;
			case LIST_PRODUCTS:
				getProductList();
				shoppingCartHelp();
				break;
			case ADD_TO_CART:
				customerId = addToShoppingCart();
				shoppingCartHelp();
				break;
			case CHECK_OUT_CART:
				customerId = showShoppingCart();
				pay(customerId);
				shoppingCartHelp();
				break;
			case DISPLAY_CART:
				showShoppingCart();
				shoppingCartHelp();
			case SHOPPING_CART_MENU:
				shoppingCartHelp();
				break;
			}
		} while (command != GO_BACK);
		save();
		help();
	}

	/**
	 * @author by Say Vang and Banji
	 * 
	 *         shows the shopping cart ask for memberId
	 */
	@SuppressWarnings("unused")
	private int showShoppingCart() {
		int memberId = getNumber("Enter member Id");
		Request.instance().setMemberId(memberId);
		Result result = groceryStore.getShoppingCart(Request.instance());
		System.out.println(result.getResultString());
		return memberId;
	}

	/**
	 * @author by Say Vang
	 * 
	 *         displays the shopping cart menu
	 */
	public void shoppingCartHelp() {
		System.out.println("__________________________________________________" + "___________");
		System.out.println("|*****Enter a number between 20 and 25 as explained" + " below****|");
		System.out.println("|--------------------------------------------------" + "----------|");
		System.out.println("|(" + GO_BACK + ") to go back previous menu      " + "                         |");
		System.out.println("|(" + LIST_PRODUCTS + ") to list all grocery " + "store products                     |");
		System.out.println("|(" + ADD_TO_CART + ") to add item to shopping " + "shopping cart                  |");
		System.out.println("|(" + CHECK_OUT_CART + ") to check out shopping " + "cart                             |");
		System.out.println("|(" + DISPLAY_CART + ") to show customer's shopping " + "cart                       |");
		System.out
				.println("|(" + SHOPPING_CART_MENU + ") for help             " + "                                  |");
		System.out.println("|________________________________________________" + "____________|");
	}

	/**
	 * @author by Say Vang and Banji
	 * 
	 *         adds items to shopping cart ask for memberId
	 * @return int customerId
	 */
	private int addToShoppingCart() {
		int customerId = Integer.parseInt(getToken("Enter memberId"));
		Request.instance().setMemberId(customerId);
		do {
			getProductList();
			Request.instance().setProductId(Integer.parseInt(getToken("Enter product id")));
			Request.instance().setRequestedAmount(Integer.parseInt(getToken("Enter quantity")));
			Request.instance().setProductPrice(customerId);
			Result result = groceryStore.addToShoppingCart(Request.instance());
			System.out.println("\n" + result.getResultString());
		} while (yesOrNo("Add product to cart"));
		Request.instance().setMemberId(customerId);
		pay(customerId);
		save();
		return customerId;
	}

	/**
	 * @author by Say Vang
	 * 
	 *         ask user if they want to pay calls checkOut() to check out shopping
	 *         cart or saves shopping cart
	 * 
	 * @param customerId
	 */
	private void pay(int customerId) {
		if (customerId == -1) {
			customerId = Integer.parseInt(getToken("Enter member id"));
		}
		if (yesOrNo("***Check Out***")) {
			checkOutShoppingCart(customerId);
		} else {
			System.out.println("***Your shopping cart will be saved***");
			Request.instance().setMemberId(customerId);
			Result result = groceryStore.getShoppingCart(Request.instance());
			System.out.println(result.getResultString());
			save();
		}
	}

	/**
	 * @author by Say Vang and Banji
	 * 
	 *         prints the transactions ask for memberId and date
	 */
	private void printTransactions() {
		Request.instance().setMemberId(Integer.parseInt(getToken("Enter member id")));
		Request.instance().setStringDate(getStringDate());
		Result result = groceryStore.printTransaction(Request.instance());
		System.out.println(result.getResultString());
	}

	/**
	 * takes id of the order,and quantity the store received. if the order exist, it
	 * will update the product quantity, remove the order from the orderList
	 * 
	 * @author Abshir Mohamed
	 */

	private void processShipment() {
		do {
			Request.instance().setOrderId(getNumber("Enter order id"));
			Request.instance().setOrderAmount(getNumber("Enter the order quantity the store received"));

			Result result = groceryStore.processShipment(Request.instance());
			if (result.getResultCode() != Result.SUCCESS) {
				System.out.println("Shipment could not be found");
				break;
			} else {
				System.out.println("Shipment processed.");
				break;
			}

		} while (yesOrNo("Process another shipment?\""));

	}

	// end of processShipment
	/**
	 * @author by Say Vang and Banji
	 * 
	 *         checkout the shopping cart
	 * 
	 * @param customerId
	 * @return int customerId
	 */
	private int checkOutShoppingCart(int customerId) {
		Result result = new Result();
		Request.instance().setMemberId(customerId);
		Request.instance().setPaid(true);
		result = groceryStore.checkOut(Request.instance());
		if (result.getResultString() == null) {
			System.out.println("Shopping cart is sempty");
		} else {
			System.out.println(result.getResultString());
		}
		save();
		return customerId;
	}

	/**
	 * Retrieve a specific product from ProductList
	 * 
	 * @modified by Abshir
	 */
	private void retreiveProduct() {
		Request.instance().setProductId(getNumber("Enter product id"));
		Result result = groceryStore.retreiveProduct(Request.instance());
		if (result.getResultCode() == Result.PRODUCT_FOUND) {
			System.out.println("Product was retreived");
			System.out.println("[ProductId: " + result.getProductId() + ", ProductName: " + result.getProductName()
					+ ", getProductPrice: " + result.getProductPrice() + ", ProductQuanitity: "
					+ result.getProductMinimumReorderLevel() + "]");
		} else {
			System.out.println("Line 273, Fail, resultCode = " + result.getResultCode());
		}
		save();
	}

	/**
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
		save();
	}

	/**
	 * @author Jeffrey
	 * 
	 *         list the outstanding product orders
	 */

	// List Outstanding Orders method
	private void listOutstandingOrders() {
		Iterator<Result> iterator = groceryStore.getOrderList();
		System.out.println("Outstanding Orders");
		System.out.println("[Name, order id, quantity]");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(
					"[" + result.getOrderName() + ", " + result.getOrderId() + ", " + result.getOrderAmount() + "]");
		}
		System.out.println("End of listing");
	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 *         runs the AutomatedTester in auto mode runs the AtumatedTester in
	 *         manual mode testAddMember(); testRemoveMember(); testAddProduct();
	 *         testCheckOut(); testProcessShipment(); testChangeProductPrice();
	 * 
	 */
	public void runTests() {
		Request request = Request.instance();
		request.setAutoTester(true);
		groceryStore = GroceryStore.instance();
		groceryStore.runAutomatedTester(request);
		if (yesOrNo("Run AutomatedTester in manual mode?")) {
			request.setAutoTester(false);
			do {
				request.setMemberName(getToken("Name"));
				request.setMemberAddress(getToken("Enter address"));
				request.setMemberPhone(getToken("Enter phone number"));
				request.setProductId(getNumber("Enter a product Id"));
				request.setProductName(getToken("Enter product name"));
				request.setProductPrice(getPrice("Enter Price"));
				request.setProductMinimumReorderLevel(getNumber("Enter minimum order"));
				request.setProductInStock(getNumber("Enter amount to stock"));
				request.setProductNewPrice(getPrice("Enter new price"));
				request.setOrderId(getNumber("Enter order Id"));
				request.setRequestedAmount(getNumber("Enter requested amount les than stock amount"));
				groceryStore.runAutomatedTester(request);
			} while (yesOrNo("would you like to run manual testing again?"));
		}
	}

//
	/**
	 * Method to be called for retrieving saved data
	 * 
	 */
	private void retrieve() {
		try {
			if (groceryStore == null) {
				groceryStore = GroceryStore.retrieve();
				if (groceryStore != null) {
					System.out.println("Grocery store loaded\n");
				} else {
					System.out.println("File doesnt exist; creating new groceryStore");
					groceryStore = GroceryStore.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Method to be called for saving the GroceryStore object
	 * 
	 */
	private void save() {
		if (GroceryStore.save()) {
			System.out.println("GroceryStore saved\n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("Running j.a.b.s. GroceryStore UserInterface");
		UserInterface userInterface = UserInterface.instance();
		userInterface.process();
		System.out.println("End Grocery Store Business" + "\nEnd User Interface");
		System.exit(0);
	}
}