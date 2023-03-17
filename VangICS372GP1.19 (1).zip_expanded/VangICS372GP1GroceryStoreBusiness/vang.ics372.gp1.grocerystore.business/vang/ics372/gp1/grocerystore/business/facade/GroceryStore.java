package vang.ics372.gp1.grocerystore.business.facade;

/**
 * @author Brahma Dathan and Sarnath Ramnath
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
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import vang.ics372.gp1.grocerystore.business.collections.MemberList;
import vang.ics372.gp1.grocerystore.business.collections.OrderList;
import vang.ics372.gp1.grocerystore.business.collections.ProductList;
import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;
import vang.ics372.gp1.grocerystore.business.collections.TransactionList;
import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;
import vang.ics372.gp1.grocerystore.business.iterators.SafeMemberListIterator;
import vang.ics372.gp1.grocerystore.business.iterators.SafeOrderListIterator;
import vang.ics372.gp1.grocerystore.business.iterators.SafeProductListIterator;
import vang.ics372.gp1.grocerystore.business.test.AutomatedTester;

/**
 * @author Brahma Dathan
 * @modified by Say Vang
 * 
 *           Facade GroceryStore Implements store business
 *
 */
public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList productList = ProductList.instance();
	private MemberList members = MemberList.instance();
	private OrderList orders = OrderList.instance();
	private static GroceryStore singletonGroceryStore;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Private constructor Access through instance
	 * 
	 */
	private GroceryStore() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (singletonGroceryStore == null) {
			return singletonGroceryStore = new GroceryStore();
		} else {
			return singletonGroceryStore;
		}
	}

	/**
	 * @modified by Abshir
	 * 
	 *           Organizes the operations for adding a product
	 * 
	 * @param int    productId
	 * @param String name
	 * @param double book id
	 * @return Product obj
	 */
	@SuppressWarnings("unchecked")
	public Result addProduct(Request request) {
		Result result = new Result();
		// making Product
		Product product = new Product(request.getProductId(), request.getProductName(), request.getProductPrice(),
				request.getProductMinimumReorderLevel());
		if (productList.insertProduct(product)) {
			result.setResultCode(Result.PRODUCT_ADDED);
			result.setProductFields(product);
			return result;
		}
		result.setResultCode(Result.PRODUCT_NOT_ADDED);
		return result;
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Operations for adding a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone(),
				request.getDate(), request.getMemberFee());
		// System.out.println("Line 108, GorceryStore.addMember(): memberId = " +
		// member.getId() + ", memberName = " + member.getName() +
		// ", memberAdress = " + member.getAddress() +
		// ", memberPhoneNumber = " + member.getPhoneNumber());
		if (members.addMember(member)) {
			result.setResultCode(Result.MEMBER_ADDED);
			result.setMemberFields(member);
			return result;
		} else {
			result.setResultCode(Result.MEMBER_NOT_ADDED);
			return result;
		}
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Searches for a given member
	 * 
	 * @param Request Object
	 * @return Result Object
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveMember(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			return result;
		} else {
			result.setResultCode(Result.MEMBER_FOUND);
			result.setMemberFields(member);
			return result;
		}
	}

	/**
	 * @modified by Say Vang
	 * 
	 *           Removes a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result removeMember(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (members.removeMember(request.getMemberId())) {
			System.out.println("MEMBER_REMOVED = " + Result.MEMBER_REMOVED);
			result.setResultCode(Result.MEMBER_REMOVED);
			result.setMemberFields(member);
			return result;
		} else {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			return result;
		}
	}

	/**
	 * Returns an iterator to productList. Iterator is a copy, returns only copies
	 * of the product list
	 * 
	 * @return an Iterator to Result - only the Product fields are valid. modified
	 *         by Abshir
	 */
	public Iterator<Result> getProductList() {
		return new SafeProductListIterator(productList.iterator());
	} // end of getProductList

	/**
	 * Removes a specific product from the catalog/productList
	 * 
	 * @param request object of the Request
	 * @return a result object modified by Abshir
	 */
	@SuppressWarnings("unchecked")
	public Result removeProduct(Request request) {
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		if (product.getId() == 0) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		result.setProductFields(product);

		if (productList.removeProduct(request.getProductId())) {
			result.setResultCode(Result.SUCCESS);
			return result;
		} else {
			result.setResultCode(Result.ERROR);
			return result;
		}
	}// end of removeProduct method

	/**
	 * Returns an iterator to Member info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
	public Iterator<Result> getMembers() {
		return new SafeMemberListIterator(members.iterator());
	}

	/*
	 * this method seems doing the same thing as getProductList() method except it
	 * has a paramater
	 * 
	 * @param request object of Request.
	 * 
	 * @modified by Abshir
	 * 
	 * @ return objectList iff the product existed or null otherwise
	 * 
	 */
	public Iterator<Result> getProducts(Request request) {
		Product product = productList.searchProduct(request.getProductId());
		if (product == null) {
			return null;
		} else {
			return new SafeProductListIterator(productList.iterator());
		}
	}

	/*
	 * Retrieves a specific product
	 * 
	 * @param a request object of the Request
	 * 
	 * @return result object modified by Abshir
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveProduct(Request request) {
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		if (product.getId() == 0) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			// System.out.println(
			// "Line 114, GS.addProduct.products.addProduct(product): "
			// + "ResultCode = " + result.getResultCode());
		} else {
			result.setResultCode(Result.PRODUCT_FOUND);
			result.setProductFields(product);
		}
		return result;

	} // end of retrieveProduct method

	/*
	 * Changes the old price of a product into new price
	 * 
	 * @param request, result Objects
	 * 
	 * @return a new result object with new price attribute
	 * 
	 * @author Abshir
	 */
	public Result changeProductPrice(Request request, Result result) {
		Result resultLocal = new Result();
		// get the product from the list
		Product newProduct = productList.searchProduct(result.getProductId());
		// update the price of the product
		newProduct.setPrice(request.getProductPrice());
		resultLocal = result;
		resultLocal.setProductPrice(request.getProductPrice());

		return result;

	}// end of changeProductPrice class

	@Override
	public String toString() {
		return productList + "\n" + members;
	}

	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 *         creates a sellableProduct adds sellableProduct to a shopping cart
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	public Result addToShoppingCart(Request request) {
		Member member = members.search(request.getMemberId());

		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		SellableProduct sellableProduct = new SellableProduct(product.getProductName(), product.getProductId(),
				product.getPrice(), 0);
		int requestedAmount = request.getRequestedAmount();
		int difference = processOrder(product, requestedAmount);
		ShoppingCart currentCart = member.getShoppingCart();
		if (difference >= 0) {
			sellableProduct.setQuantity(requestedAmount);
			if (currentCart.addSellableProduct(sellableProduct)) {
				result.setResultCode(Result.PRODUCT_ADDED);
			}
		} else if (difference < 0 && (difference + requestedAmount) >= 0) {
			sellableProduct.setQuantity(difference + requestedAmount);
			if (currentCart.addSellableProduct(sellableProduct)) {
				result.setResultCode(Result.PARTIAL_PRODUCT_ADDED);
			}
		}

		else {
			sellableProduct.setQuantity(0);
			if (currentCart.addSellableProduct(sellableProduct)) {
				result.setResultCode(Result.PRODUCT_NOT_ADDED);
			}

		}
		result.setResultString(currentCart.toString());
		return result;
	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 * @param product
	 * @param requestedAmount
	 * @return int difference
	 */
	@SuppressWarnings("exports")
	public int processOrder(Product product, int requestedAmount) {
		int difference = (product.getProductStock() - requestedAmount);
		if (difference >= product.getMinimumReorderLevel()) {
			product.setProductStock(difference);
		} else {
			// System.out.println("G.S proOrd line 368 diff = " +
			// difference + " : min = " +
			// product.getMinimumReorderLevel());
			Order orderProduct = new Order(product);
			orders.insertOrder(orderProduct);
			if (difference > 0) {
				product.setProductStock(difference);
			} else {
				product.setProductStock(0);
			}
		}
		return difference;
	}

	/**
	 * process the orderList update the product stock remove the order from the
	 * order list
	 * 
	 * @param request from UserInterface
	 * @return Result obj
	 * 
	 * @author Abshir Mohamed
	 * 
	 */

	public Result processShipment(Request request) {

		Result result = new Result();
		Order order = orders.searchOrder(request.getOrderId());
		Product product = productList.searchProduct(request.getProductId());

		if (order.getId() == 0 && product.getId() == 0) {

			throw new NullPointerException("Order/product was null");

		} else {
			product.setProductStock(product.getProductStock() + request.getOrderAmount());
			if (orders.removeOrder(order.getId())) {
				result.setResultCode(Result.SUCCESS);
				return result;
			} else {
				throw new NullPointerException("Order was null");

			}
		}

	}

	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	@SuppressWarnings({})
	public Result getShoppingCart(Request request) {
		ShoppingCart currentCart = (members.search(request.getMemberId())).getShoppingCart();
		TransactionList transactionList = (members.search(request.getMemberId())).getTransactionList();
		transactionList.add(currentCart);
		Result result = new Result();
		result.setResultString(currentCart.toString());
		return result;
	}

	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	public Result checkOut(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		ShoppingCart currentCart = member.getShoppingCart();
		TransactionList transactionList = member.getTransactionList();
		Calendar calendar = Calendar.getInstance();
		currentCart.setCheckOutDate(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR));
		currentCart.setMemberId(request.getMemberId());
		transactionList.addTransaction(currentCart);
		// System.out.println("G.S. line 457: getTotalPrice = " +
		// currentCart.getTotalPrice());
		if (currentCart.getTotalPrice() < .01) {
			// System.out.println("if");
			result.setResultString(null);
			result.setResultCode(Result.SHOPPING_CART_PENDING);
		} else {
			// System.out.println("else");
			result.setResultString(String.format("Shopping cart has been " + "paid in full\n" + " - %.2f",
					currentCart.getTotalPrice()));
			ShoppingCart newCart = new ShoppingCart();
			result.setResultCode(Result.SHOPPING_CART_PAID);
			member.setShoppingCart(newCart);
		}
		return result;
	}

	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param request
	 * @return
	 */
	public Result printTransaction(Request request) {
		Member member = (members.search(request.getMemberId()));
		TransactionList list = member.getTransactionList();
		Result result = new Result();
		result.setResultString(list.getTransactionsOnDate(request.getStringDate()));
		return result;
	}

	/**
	 * @author
	 * 
	 * @return
	 */

	public Iterator<Result> getOrderList() {
		return new SafeOrderListIterator(orders.iterator());
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

	public void runAutomatedTester(Request request) {
		AutomatedTester autoTester = new AutomatedTester();
		Result result = new Result();
		if (!request.isAutoTester()) {
			if (yesOrNo("Test addMember()?")) {
				result = autoTester.testAddMember(request);
			}
			if (yesOrNo("Test removeMember()?")) {
				System.out.println("memberId = " + result.getMemberId());
				request.setMemberId(result.getMemberId());
				autoTester.testRemoveMember(request);
			}
			if (yesOrNo("Test addProduct?")) {
				autoTester.testAddProduct(request);
			}
//			if (yesOrNo("Test changeProductPrice()?")) {
//				autoTester.testChangeProductPrice(request);
//			}
//			if (yesOrNo("Test checkOut()?")) {
//				autoTester.testCheckOut(request);
//			}
//			if (yesOrNo("Test processShipment()?")) {
//				autoTester.testProcessShipment(request);
//			}
		} else {
			autoTester.main(null);
		}
		System.out.println("*****Passed all manual tests");
	}

	/**
	 * Retrieves GroceryStore from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			singletonGroceryStore = (GroceryStore) input.readObject();
			Member.retrieve(input);
			return singletonGroceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializes the GroceryStore object
	 * 
	 * @return true if the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonGroceryStore);
			Member.save(output);
			// Product.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
