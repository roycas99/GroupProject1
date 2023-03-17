package vang.ics372.gp1.grocerystore.business.facade;

import java.util.Calendar;

import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;

/**
 * <code>DataTransfer</code> assures there is separation between the interface
 * and data stores (ProductList, OrderList, and MemberList). By maintaining
 * separation there is separation between the businesslogic and the interface.
 * While the work in <code>DataTransfer</code> could be implemented by a using a
 * <code>Transferable</code> interface which requires the implementation of copy
 * constructors there could be data leakage. While a lot of fields are
 * duplicated so entities can be created to exist only in the iterface or facade
 * care must be taken to assure there is consistency.
 * 
 * NOTE ---- All transferable data items must be atomic (no collections can be
 * used. Because the items in the collection will have to be processed
 * individually
 * 
 * ABOUT THE GETTERS AND SETTERS ------------------------------- Whereas
 * instances of transfered objects are created in methods whose names are
 * prefixed with setField mutation and access to properties in either the
 * interface or data store occuer with getters and setters.
 * 
 * 
 * @author Say Vang, Jeffrey, Abshir, Banji Lawal
 * @param <T>
 *
 *            updated 3/15/2023
 */
public abstract class DataHandOff<T> {

	/*
	 * All fields in all objects are needed here so they can be recreated
	 * independently.
	 * 
	 * Naming Convention ------------------- Entities can exist in differing places
	 * depending on their is-a or has-a relationship with other objects. To show
	 * what object the field creates the target object prefixes the field
	 */

	// Fields required to create <code>Product</code> instance
	private int productId;
	private String productName;
	private double productPrice;
	private double productNewPrice;
	private int productMinimumReorderLevel;
	private int productInStock;

	// Fields required to order <code>Order</code> instance
	private int orderId;
	private String orderName;
	private int orderAmount;

	// Fields required to create <code>SellableProduct</code> instance.
	// despite SellableProduct being derived from <code>Product</code>
	private int sellableProductId;
	private String sellableProductName;
	private double sellableProductPrice;
	private int sellableProductQuantity;
	private int sellableProductDifference;
	private double sellableProductTotalCost = 0;
	private int requestedAmount;

	// Fields for recreating <code>Member</code> instance
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private double memberFee;

	// Transaction Data Handoff Fields//////////////
	private int transactionMemberId;
	private int transactionProductId;
	private double transactionProductPrice;
	private int transactionProductQuantity;
	private int transactionProductDifference;
	private int transactionProductRequestedAmount;
	private static double transactionProductTotalCost = 0;

	private Calendar transactionDate;
	private String transactionProductName;

	private boolean paid;
	private String resultString = null;
	private String stringDate = null;
	private boolean autoTester = true;

	////////////////////////////////// Getters and Setters for Order Instance
	////////////////////////////////// Transfers //////////////////////////////////
	/**
	 * @return the orderProductName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderProductName the orderProductName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param set product id
	 */
	public void setProductId(int id) {
		this.productId = id;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public String getProductName() {
		return productName;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setTransactionProductRequestedAmount(int transactionProductRequestedAmount) {
		this.transactionProductRequestedAmount = transactionProductRequestedAmount;
	}

	/**
	 * This sets all fields to "none".
	 */
	public DataHandOff() {
		reset();
	}

//	////////////////////////////////// Getters and Setters for Member Instance
//	////////////////////////////////// Transfers //////////////////////////////////
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		System.out.println(
				"Line 87, DataTransfer.setMemberId(): memberId = " + memberId + ", this.memberId = " + this.memberId);
		this.memberId = memberId;
	}

	/**
	 * @param memberFee the memberFee to set
	 */
	public void setMemberFee(double memberFee) {
		this.memberFee = memberFee;
	}

	public double getMemberFee() {
		return memberFee;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	/**
	 * Sets all the entity-related.
	 * 
	 * @param an abstract object.
	 */
	public void setMemberFields(Member member) {
		this.memberId = member.getId();
		this.memberName = member.getName();
		memberPhone = member.getPhoneNumber();
		memberAddress = member.getAddress();
		memberFee = member.getFeePaid();
	}

	///// Product field setters getters /////

//	public int getProductId() {
//		return id;
//	}
//
//	public void setProductId(int productId) {
//		this.id = productId;
//	}
//
//	public String getProductName() {
//		return name;
//	}
//
//	public void setProductName(String productName) {
//		this.name = productName;
//	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductPrice() {

		return productPrice;
	}

	/**
	 * @return the productMinimumReorderLevel
	 */
	public int getProductMinimumReorderLevel() {
		return productMinimumReorderLevel;
	}

	/**
	 * @param productMinimumReorderLevel the productMinimumReorderLevel to set
	 */
	public void setProductMinimumReorderLevel(int productMinimumReorderLevel) {
		this.productMinimumReorderLevel = productMinimumReorderLevel;
	}

	/**
	 * @return the productInStock
	 */
	public int getProductInStock() {
		return productInStock;
	}

	/**
	 * @param productInStock the productInStock to set
	 */
	public void setProductInStock(int productInStock) {
		this.productInStock = productInStock;
	}

	/**
	 * @return the productNewPrice
	 */
	public double getProductNewPrice() {
		return productNewPrice;
	}

	/**
	 * @param productNewPrice the productNewPrice to set
	 */
	public void setProductNewPrice(double productNewPrice) {
		this.productNewPrice = productNewPrice;
	}

	/**
	 * If the transfer object is a Product sets all their product fields
	 * 
	 * @param Entity
	 */
	public void setProductFields(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productPrice = product.getPrice();
		this.productMinimumReorderLevel = product.getMinimumReorderLevel();
		this.productInStock = product.getProductStock();
	}

	/**
	 * If the transfer object is a SellableProduct sets all their product fields
	 * 
	 * @param Entity
	 */
	public void setSellableProductFields(T t) {
		this.sellableProductId = ((SellableProduct) t).getProductId();
		this.sellableProductName = ((SellableProduct) t).getProductName();
		this.sellableProductPrice = ((SellableProduct) t).getPrice();
		this.sellableProductQuantity = ((SellableProduct) t).getQuantity();
		this.sellableProductDifference = ((SellableProduct) t).getDifference();
		sellableProductTotalCost += ((SellableProduct) t).computeTotalCost();
	}

	/**
	 * If the transfer object is a Order sets all their product fields
	 * 
	 * @param Entity
	 */
	public void setOrderFields(Order order) {
		this.orderName = order.getName();
		this.orderId = order.getId();
		this.orderAmount = order.getAmount();
	}

	/**
	 * @return the sellableProductId
	 */
	public int getSellableProductId() {
		return sellableProductId;
	}

	/**
	 * @param sellableProductId the sellableProductId to set
	 */
	public void setSellableProductId(int sellableProductId) {
		this.sellableProductId = sellableProductId;
	}

	/**
	 * @return the sellableProductName
	 */
	public String getSellableProductName() {
		return sellableProductName;
	}

	/**
	 * @param sellableProductName the sellableProductName to set
	 */
	public void setSellableProductName(String sellableProductName) {
		this.sellableProductName = sellableProductName;
	}

	/**
	 * @return the sellableProductPrice
	 */
	public double getSellableProductPrice() {
		return sellableProductPrice;
	}

	/**
	 * @param sellableProductPrice the sellableProductPrice to set
	 */
	public void setSellableProductPrice(int sellableProductPrice) {
		this.sellableProductPrice = sellableProductPrice;
	}

	/**
	 * @return the sellableProductTotalCost
	 */
	public double getSellableProductTotalCost() {
		return sellableProductTotalCost;
	}

	/**
	 * @param sellableProductTotalCost the sellableProductTotalCost to set
	 */
	public void setSellableProductTotalCost(double sellableProductTotalCost) {
		this.sellableProductTotalCost = sellableProductTotalCost;
	}

	/**
	 * @param sellableProductPrice the sellableProductPrice to set
	 */
	public void setSellableProductPrice(double sellableProductPrice) {
		this.sellableProductPrice = sellableProductPrice;
	}

	/**
	 * @return the sellableProductQuantity
	 */
	public int getSellableProductQuantity() {
		return sellableProductQuantity;
	}

	/**
	 * @param sellableProductQuantity the sellableProductQuantity to set
	 */
	public void setSellableProductQuantity(int sellableProductQuantity) {
		this.sellableProductQuantity = sellableProductQuantity;
	}

	/**
	 * @return the sellableProductDifference
	 */
	public int getSellableProductDifference() {
		return sellableProductDifference;
	}

	/**
	 * @param sellableProductDifference the sellableProductDifference to set
	 */
	public void setSellableProductDifference(int sellableProductDifference) {
		this.sellableProductDifference = sellableProductDifference;
	}

	/**
	 * @return the requestedAmount
	 */
	public int getRequestedAmount() {
		// System.out.println("DHO.getRequested ln 298: requestedAmount = " +
		// requestedAmount);
		return requestedAmount;
	}

	public void setRequestedAmount(int requestedAmount) {
		// System.out.println("DHO.getRequested ln 303: requestedAmount = " +
		// requestedAmount);
		this.requestedAmount = requestedAmount;
	}

	/**
	 * @return the resultString
	 */
	public String getResultString() {
		return resultString;
	}

	/**
	 * @param resultString the resultString to set
	 */
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	/**
	 * @return the paid
	 */
	public boolean getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getTransactionMemberId() {
		return transactionMemberId;
	} //

	public int getOrderAmount() {
		return orderAmount;
	}

	public int getTransactionProductId() {
		return transactionProductId;
	}

	public double getTransactionProductPrice() {
		return transactionProductPrice;
	}

	public int getTransactionProductQuantity() {
		return transactionProductQuantity;
	}

	public int getTransactionProductDifference() {
		return transactionProductDifference;
	}

	public static double getTransactionProductTotalCost() {
		return transactionProductTotalCost;
	}

	public int getTransactionProductRequestedAmount() {
		return transactionProductRequestedAmount;
	}

	public String getTransactionProductName() {
		return transactionProductName;
	}

	public Calendar getTransactionDate() {
		return transactionDate;
	}

	public String printTransactionDate() {
		String string = transactionDate.get(java.util.Calendar.MONTH) + "/" + transactionDate.get(Calendar.DATE) + "/"
				+ transactionDate.get(Calendar.YEAR);
		return string;
	}

	// Setters

	public void setTransactionMemberId(int transactionMemberId) {
		this.transactionMemberId = transactionMemberId;
	} //

	public void setTransactionProductId(int transactionProductId) {
		this.transactionProductId = transactionProductId;
	}

	public void setTransactionProductPrice(double transactionProductPrice) {
		this.transactionProductPrice = transactionProductPrice;
	}

	public void setTransactionProductQuantity(int transactionProductQuantity) {
		this.transactionProductQuantity = transactionProductQuantity;
	}

	public void setTransactionProductDifference(int transactionProductDifference) {
		this.transactionProductDifference = transactionProductDifference;
	}

	public static void setTransactionProductTotalCost(double transactionProductTotalCost) {
		DataHandOff.transactionProductTotalCost = transactionProductTotalCost;
	}

	public void setTransactionProductrequestedAmount(int transactionProductRequestedAmount) {
		this.transactionProductRequestedAmount = transactionProductRequestedAmount;
	}

	public void setTransactionProductName(String transactionProductName) {
		this.transactionProductName = transactionProductName;
	}

	public void setTransactionDate(Calendar transactionDate) {
		this.transactionDate = transactionDate;
	}
	/*
	 * public void setTransactionFields(T t) { this.transactionDate = ((Transaction
	 * t)).getDate(); this.transactionMemberId = ((Transaction) t).getMemberId();
	 * this.transactionProductId = ((Transaction)
	 * t).getSellableProduct().getProductId(); this.transactionProductName =
	 * ((Transaction) t).getSellableProduct().getProductName();
	 * this.transactionProductPrice = ((Transaction)
	 * t).getSellableProduct().getPrice(); this.transactionProductQuantity =
	 * ((Transaction) t).getSellableProduct().getQuantity();
	 * this.transactionProductDifference = ((Transaction)
	 * t).getSellableProduct().getDifference();
	 * this.transactionProductRequestedAmount = ((Transaction)
	 * t).getSellableProduct().getRequestedAmount();
	 * this.transactionProductTotalCost = ((Transaction)
	 * t).getSellableProduct().getTotalCost(); }
	 */

	/**
	 * @return the stringDate
	 */
	public String getStringDate() {
		return stringDate;
	}

	/**
	 * @param stringDate the stringDate to set
	 */
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	/**
	 * @return the autoTester
	 */
	public boolean isAutoTester() {
		return autoTester;
	}

	/**
	 * @param autoTester the autoTester to set
	 */
	public void setAutoTester(boolean autoTester) {
		this.autoTester = autoTester;
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {

		productId = 0;
		productName = "Null";
		productPrice = 0;
		productMinimumReorderLevel = 0;
		productInStock = 0;
		memberId = 0;
		memberName = "Null";
		memberPhone = "Null";
		memberAddress = "Null";
		memberFee = 0;
		orderName = "NUll";
		orderId = 0;
		sellableProductId = 0;
		sellableProductName = null;
		sellableProductPrice = 0;
		sellableProductQuantity = 0;
		sellableProductDifference = 0;
		sellableProductTotalCost = 0;
		resultString = null;
		requestedAmount = 0;
		stringDate = null;
		autoTester = true;

	}

	/*
	 * interface Clone {
	 * 
	 * }
	 * 
	 * Clone clone = T -> { String name = ((Entity) t).getName(); String Id =
	 * Integer.toString(((Entity) t).getId()); String date = ((Member)
	 * t).getDate().toString(); String fee = Double.toString(((Member)
	 * t).getFeePaid()); String shoppincart = ((Member)
	 * t).getShoppingCart().toString(); String productPrice =
	 * Double.toString(((Product) t).getPrice()); String productMinimumReorderLevel
	 * = Double.toString(((Product) t).getMinimumReorderLevel()); String
	 * productStock = Integer.toString(((Product) t).getProductStock()); };
	 */
}
