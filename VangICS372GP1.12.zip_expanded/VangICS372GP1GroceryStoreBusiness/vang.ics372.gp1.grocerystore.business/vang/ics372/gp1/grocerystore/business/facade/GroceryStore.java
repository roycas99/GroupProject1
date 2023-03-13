package vang.ics372.gp1.grocerystore.business.facade;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath and Say Vang
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;

import vang.ics372.gp1.grocerystore.business.collections.ProductList;
import vang.ics372.gp1.grocerystore.business.collections.MemberList;
import vang.ics372.gp1.grocerystore.business.collections.OrderList;
import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;
import vang.ics372.gp1.grocerystore.business.collections.TransactionList;
import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;
import vang.ics372.gp1.grocerystore.business.iterators.SafeMemberListIterator;
import vang.ics372.gp1.grocerystore.business.iterators.SafeProductListIterator;
import vang.ics372.gp1.grocerystore.business.iterators.SafeSellableProductListIterator;
import vang.ics372.gp1.grocerystore.business.iterators.SafeOrderListIterator;

/**
 * Facade GroceryStore
 * Implements store business
 * 
 * @author Brahma Dathan
 *
 */
public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList productList = ProductList.instance();
	private MemberList members = MemberList.instance();
	private OrderList orders = OrderList.instance();
	private TransactionList transactionList = TransactionList.instance();
	private static GroceryStore singletonGroceryStore;

	/**
	 * Private constructor
	 * Access through instance
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
	 * Organizes the operations for adding a book
	 * 
	 * @param title  book title
	 * @param author author name
	 * @param id     book id
	 * @return the Book object created
	 */
	@SuppressWarnings("unchecked")
	public Result addProduct(Request request) {
		Result result = new Result();
		// making Product
		Product product = new Product(request.getProductId(), request.getProductName(), request.getProductPrice(),
				request.getProductMinimumReorderLevel(), request.getProductInStock());
		if (productList.insertProduct(product)) {
			result.setResultCode(Result.SUCCESS);
			result.setProductFields(product);
			return result;
		}
		result.setResultCode(Result.ERROR);
		return result;
	}

	/**
	 * Operations for adding a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), 
				request.getMemberAddress(), request.getMemberPhone(), 
				request.getDate(), request.getMemberFee());
		System.out.println("Line 108, GorceryStore.addMember(): memberId = " + member.getId() +
				", memberName = " + member.getName() + ", memberAdress = " + member.getAddress() +
				", memberPhoneNumber = " + member.getPhoneNumber());
		if (members.addMember(member)) {
			result.setResultCode(Result.MEMBER_ADDED);
			result.setMemberFields(member);
			System.out.println("Line 114, GS.addMember.members.addMember(member): ResultCode = " +
			result.getResultCode());
			return result;
		}
		else {
			result.setResultCode(Result.MEMBER_NOT_ADDED);
			System.out.println("Line 119, else: resultCode = " + result.getResultCode());
			return result;
		}
	}

	/**
	 * Searches for a given member
	 * 
	 * @param Request Object
	 * @return Result Object
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveMember(Request request) {
		//System.out.println("Line 132, GorceryStore.retreiveMember(): memberId = " + request.getMemberId());
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		//System.out.println("Line 135, member.getId = " + member.getId());
		if (member == null) {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			//System.out.println("Line 114, GS.addMember.members.addMember(member): ResultCode = " +
					result.getResultCode();
			return result;
		} else {
			result.setResultCode(Result.MEMBER_FOUND);
			result.setMemberFields(member);
			return result;
		}
	}
	
	/**
	 * Removes a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result removeMember(Request request) {
		//System.out.println("Line 149, GorceryStore.removeMember(): memberId = " + request.getMemberId());
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		//System.out.println("Line 152, member.getId = " + member.getId());
		if(members.removeMember(request.getMemberId())){
			result.setResultCode(Result.MEMBER_REMOVED);
			result.setMemberFields(member);
			//System.out.println("Line 156, ResultCode = " + result.getResultCode());
			return result;
		}
		else {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			//System.out.println("Line 156, ResultCode = " + result.getResultCode());
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
		if (product.getProductId() == 0) {
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
	 * Returns an iterator to the info. in transactions for a specific member on a
	 * certain date
	 * 
	 * @param memberId member id
	 * @param date     date of issue
	 * @return iterator to the collection
	 */
	public Iterator<Result> getTransactions(Request request) {
		Member member = members.search(request.getMemberId());
		if (member == null) {
			return new LinkedList<Result>().iterator();
		}
		return member.getTransactionsOnDate(request.getDate());
	}

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
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveProduct(Request request) {
		System.out.println("Line 132, GorceryStore.retreiveProduct(): ProductId = " + request.getProductId());
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		System.out.println("Line 135, product.getId = " + product.getProductId());
		// issue started after this line
		if (product.getProductId() == 0) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			System.out.println(
					"Line 114, GS.addProduct.products.addProduct(product): ResultCode = " + result.getResultCode());
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
	
	@SuppressWarnings("exports")
	public Result addToShoppingCart(Request request) {
		Member member = members.search(request.getMemberId());
		Product product = productList.searchProduct(request.getProductId());
		SellableProduct sellableProduct = new SellableProduct(product.getProductName(), product.getProductId(), product.getPrice(), 0);
		int requestedAmount = request.getRequestedAmount();
		//System.out.println("GS.addToShop ln 332: requestedAmount = " + requestedAmount);
		int difference = processOrder(product, requestedAmount);
		ShoppingCart currentCart = (members.search(request.getMemberId())).getShoppingCart();
		//System.out.println("GS.addToShop ln 335: requestedAmount = " + requestedAmount);
		//System.out.println("GS.addToShop ln 336: difference = " + difference);
		if (difference >= 0) {
			//System.out.println("GS.addToShop.if ln 338: requestedAmount = " + requestedAmount);
			//System.out.println("GS.addToShop.if ln 339: difference = " + difference);
			sellableProduct.setQuantity(requestedAmount);
			//System.out.println("GS.addToShop.if ln 341 sellPro.getQuantity = " + sellableProduct.getQuantity());
			currentCart.addSellableProduct(sellableProduct);
			//System.out.println("G.S.addToShop line 342: currentCart.add is " + currentCart.addSellableProduct(sellableProduct)); //add works here: returns true
			//System.out.println("G.S.addToShop.elif line 343: currentCart.search is " + currentCart.search(request.getProductId())); //Does not find product
		}
		else if (difference < 0 && (difference + requestedAmount) >= 0) {
			//System.out.println("GS.addToShop.elif ln 346: requestedAmount = " + requestedAmount);
			//System.out.println("GS.addToShop.elif ln 347: difference = " + difference);
			sellableProduct.setQuantity(difference + requestedAmount);
			//System.out.println("GS.addToShop.elif ln 349: sellPro.getQuantity = " + sellableProduct.getQuantity());
			currentCart.addSellableProduct(sellableProduct);
		}
		
		else {
			//System.out.println("GS.addToShop.else ln 354: requestedAmount = " + requestedAmount);
			//System.out.println("GS.addToShop.else ln 355: difference = " + difference);
			sellableProduct.setQuantity(0);
			//System.out.println("GS.addToShop.else ln 356: sellPro.getQuantity = " + sellableProduct.getQuantity());
			currentCart.addSellableProduct(sellableProduct);
			
		}
		//System.out.println("G.S.Return line 361: Display Shopping cart***");
		//System.out.println(currentCart.toString());
		//System.out.println("G.S.addToShop.Return line 363: request.getProdId = " + request.getProductId());
		//System.out.println("G.S.addToShop.Return line 364: request.quantity = " + request.getRequestedAmount());
		//System.out.println("G.S.addToShop.Return line 365: currentCart.search is " + currentCart.search(request.getProductId()));  //can't search product from cart
		//System.out.println("G.S.addToShop.Return line 366: tempProduct.getProdId = " + tempProduct.getProductId());
		//System.out.println("G.S.addToShop.Return line 367: tempProduct.quantity = " + tempProduct.getQuantity());
		//currentCart.remove(sellableProduct);
		//SellableProduct tempProduct2 = currentCart.search(request.getProductId());
		//currentCart.toString();
		//System.out.println("G.S.Return line 371 end display shopping cart***");
		//return new SafeSellableProductListIterator(member.getShoppingCart().iterator());
		Result result = new Result();
		result.setResultString(currentCart.toString());
		return result;
	}
	
	@SuppressWarnings("exports")
	public int processOrder(Product product, int requestedAmount) {
		
		//Order orderProduct = new Order(product);
		//System.out.println(orderProduct.toString());
		//orders.insertOrder(orderProduct);
		
		//System.out.println("GS.checkAvail 377: productStock =  " + product.getProductStock());
		int difference = (product.getProductStock() - requestedAmount);
		//System.out.println("GS.checkAvail ln 379: requestedAmount = " + requestedAmount);
		//System.out.println("GS.checkAvail ln 380: difference = " + difference);
		if (difference >= product.getMinimumReorderLevel()) {
			product.setProductStock(difference);
		}
		else {
			Order orderProduct = new Order(product);
			System.out.println(orderProduct.toString());
			orders.insertOrder(orderProduct);
			System.out.println("G.S. processOrder line 392: " + orders.iterator().next().toString());
		}
		return difference;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result getShoppingCart(Request request) {
		ShoppingCart currentCart = (members.search(request.getMemberId())).getShoppingCart();
		transactionList.add(currentCart);
		Result result = new Result();
		result.setResultString(currentCart.toString());
		return result;
	}

	@SuppressWarnings("exports")
	public Result checkOut(Request request) {
		ShoppingCart currentCart = (members.search(request.getMemberId())).getShoppingCart();
		currentCart.setCheckOutDate(Calendar.getInstance());
		currentCart.setMemberId(request.getMemberId());
		transactionList.add(currentCart);
		Result result = new Result();
		if(currentCart.getTotalPrice() < .01) {
			result.setResultString(null);
		}
		else {
			result.setResultString(String.format("Shopping cart has been paid in full\n" +
					" - %.2f", currentCart.getTotalPrice()));
			currentCart.empty();
			currentCart.toString();
		}
		return result;
	}
	
	/**
	 * @author 
	 * 
	 * @return
	 */
	/*
	public Result getOrderList() {
		Result result = new Result();
		result.setResultString(orders.toString());
		return result;
	}*/
	
	/**
	 * @author 
	 * 
	 * @return
	 */
	
	public Iterator<Result> getOrderList() {
		return new SafeOrderListIterator(orders.iterator());
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
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonGroceryStore);
			Member.save(output);
			//Product.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	public Result printTransaction(Request instance) {
		StringBuilder string = new StringBuilder("----Transactions------------------------------------");
		for(Iterator<ShoppingCart> iterator = TransactionList.instance().iterator(); iterator.hasNext();) {
			ShoppingCart cart = iterator.next();
			if(cart.getMemberId() == Request.instance().getMemberId() && cart.getCheckOutDate().equals(Request.instance().getDate())) {
				string.append(cart.toString());
			}
		}
		Result result = new Result();
		result.setResultString(string.toString());
		return result;
	}
}
