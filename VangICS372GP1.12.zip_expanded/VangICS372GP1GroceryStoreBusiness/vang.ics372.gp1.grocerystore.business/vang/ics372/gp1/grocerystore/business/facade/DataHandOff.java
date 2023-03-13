package vang.ics372.gp1.grocerystore.business.facade;

import java.util.Calendar;

import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;
import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.Order;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath and adapted by Say Vang
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */

import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;
import vang.ics372.gp1.grocerystore.business.entities.Transaction;

/**
 * The DataTransfer class is used to facilitate data transfer between Library
 * and UserInterface. It is also used to support iterating over Member and Book
 * objects. The class stores copies of fields that may be sent in either
 * direction.
 * 
 * @author Brahma Dathan
 * @param <T>
 *
 */
public abstract class DataHandOff<T> {
	
	private int productId;
	private String productName;
	private double productPrice;
	private int productMinimumReorderLevel;
	private int productInStock;
	
	private int sellableProductId;
	private String sellableProductName;
	private double sellableProductPrice;
	private int sellableProductQuantity;
	private int sellableProductDifference;
	private double sellableProductTotalCost = 0;
	private int requestedAmount;
	
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private double memberFee;
	//private int orderAmount;
	
	private String orderProductName;
	private int orderProductId;
	private int orderAmount;
	
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
	
	/**
	 * This sets all fields to "none".
	 */
	public DataHandOff() {
		reset();
	}
	
	/////   Member field setter getter   /////
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		//System.out.println("Line 87, DataTransfer.setMemberId(): memberId = " + memberId +
		//		", this.memberId = " + this.memberId);
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
	public void setMemberFields(T t) {
		memberId = ((Member) t).getId();
		memberName = ((Member) t).getName();
		memberPhone = ((Member) t).getPhoneNumber();
		memberAddress = ((Member) t).getAddress();
		memberFee = ((Member) t).getFeePaid();
	}
	
	/////   Product field setters getters   /////
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
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
	 * Sets all the product-related fields 
	 * 
	 * @param Entity 
	 */
	public void setProductFields(T t) {
		this.productId = ((Product) t).getProductId();
		this.productName = ((Product) t).getProductName();
		this.productPrice = ((Product) t).getPrice();
		this.productMinimumReorderLevel = ((Product) t).getMinimumReorderLevel();
		this.productInStock = ((Product) t).getProductStock();
	}
	
	public void setSellableProductFields(T t) {
		this.sellableProductId = ((SellableProduct) t).getProductId();
		this.sellableProductName = ((SellableProduct) t).getProductName();
		this.sellableProductPrice = ((SellableProduct) t).getPrice();
		this.sellableProductQuantity = ((SellableProduct) t).getQuantity();
		this.sellableProductDifference = ((SellableProduct) t).getDifference();
		sellableProductTotalCost += ((SellableProduct) t).computeTotalCost();
		//System.out.println("DHO ln 203: this.sellableProductId = " + this.sellableProductId);
		//System.out.println("DHO ln 204: this.sellableProductName = " + this.sellableProductName);
		//System.out.println("DHO ln 205: this.sellableProductPrice = " + this.sellableProductPrice);
		//System.out.println("DHO ln 206: t.getQuant = " + ((SellableProduct) t).getQuantity());
		//System.out.println("DHO ln 207: this.sellableProductQuantity = " + this.sellableProductQuantity);
		//System.out.println("DHO ln 208: this.sellableProductDifference = " + this.sellableProductDifference);
		//System.out.println("DHO ln 209: this.sellableProductTotalCost = " + this.sellableProductTotalCost);
	}
	
	public void setOrderFields(Order order) {
        this.productName = order.getName();
        this.productId = order.getId();
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
		//System.out.println("DHO.getRequested ln 298: requestedAmount = " + requestedAmount);
		return requestedAmount;
	}

	public void setRequestedAmount(int requestedAmount) {
		//System.out.println("DHO.getRequested ln 303: requestedAmount = " + requestedAmount);
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
	

	public int getTransactionMemberId () {
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

	
	public Calendar getTransactionDate () {
		return transactionDate;
	}
	
	public String printTransactionDate () {
		String string = transactionDate.get(java.util.Calendar.MONTH) 
				+ "/" + transactionDate.get(Calendar.DATE) 
				+ "/" + transactionDate.get(Calendar.YEAR);
		return string;
	}
	
	// Setters
	
	public void setTransactionMemberId (int transactionMemberId) {
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

	
	public void setTransactionDate (Calendar transactionDate) {
		this.transactionDate = transactionDate;
	}
	/*
	public void setTransactionFields(T t) {
	this.transactionDate = ((Transaction t)).getDate();
	this.transactionMemberId = ((Transaction) t).getMemberId();
	this.transactionProductId = ((Transaction) t).getSellableProduct().getProductId(); 
	this.transactionProductName = ((Transaction) t).getSellableProduct().getProductName();
	this.transactionProductPrice = ((Transaction) t).getSellableProduct().getPrice();
	this.transactionProductQuantity = ((Transaction) t).getSellableProduct().getQuantity();
	this.transactionProductDifference = ((Transaction) t).getSellableProduct().getDifference();
	this.transactionProductRequestedAmount = ((Transaction) t).getSellableProduct().getRequestedAmount();
	this.transactionProductTotalCost = ((Transaction) t).getSellableProduct().getTotalCost();
	}*/

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
		sellableProductId = 0;
		sellableProductName = null;
		sellableProductPrice = 0;
		sellableProductQuantity = 0;
		sellableProductDifference = 0;
		sellableProductTotalCost = 0;
		resultString = null;
		requestedAmount = 0;

	}
	
	/*
	interface Clone {
		
	}
	
	Clone clone = T -> {
			String name = ((Entity) t).getName();
			String Id = Integer.toString(((Entity) t).getId());
			String date = ((Member) t).getDate().toString();
			String fee = Double.toString(((Member) t).getFeePaid());
			String shoppincart = ((Member) t).getShoppingCart().toString();
			String productPrice = Double.toString(((Product) t).getPrice());
			String productMinimumReorderLevel = Double.toString(((Product) t).getMinimumReorderLevel());
			String productStock = Integer.toString(((Product) t).getProductStock());
	};
	*/
}


