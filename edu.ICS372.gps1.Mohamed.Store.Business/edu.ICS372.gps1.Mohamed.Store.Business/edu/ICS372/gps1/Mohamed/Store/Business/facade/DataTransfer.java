package edu.ICS372.gps1.Mohamed.Store.Business.facade;

import edu.ICS372.gps1.Mohamed.Store.Business.entities.Order;
import edu.ICS372.gps1.Mohamed.Store.Business.entities.Product;

public abstract class DataTransfer<T> {
	private int productId;
	private int productAmountInCart;
	private String productName;
	private double productPrice;
	private int productMinimumReorderLevel;
	private int productStock;
	private int orderQuantity;
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private double memberFee;
	private String transactionType;
	private String transactionDate;
	// private List<SellableProduct> shoppingCart;

	/**
	 * This sets all fields to "none".
	 */
	public DataTransfer() {
		reset();
	}

	///// Member field setter getter /////

	/**
	 * @return the productAmountInCart
	 */
	public int getProductAmountInCart() {
		return productAmountInCart;
	}

	/**
	 * @param productAmountInCart the amountInCart to set
	 */
	public void setProductAmountInCart(int productAmountInCart) {
		this.productAmountInCart = productAmountInCart;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		System.out.println(
				"Line 87, DataTransfer.setMemberId(): memberId = " + memberId + ", this.memberId = " + this.memberId);
		this.memberId = memberId;
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
	 * Sets all the member-related fields using the Member parameter.
	 * 
	 * @param member the member whose fields should be copied.
	 */
//	public void setMemberFields(Member member) {
//		memberId = member.getId();
//		memberName = member.getName();
//		memberPhone = member.getPhoneNumber();
//		memberAddress = member.getAddress();
//	}

	///// Product field setters getters /////

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

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

//	public double getMemberFee() {
//		return memberFee;
//	}

	///// Transaction field setters getters /////

//	public String getTransactionType() {
//		return transactionType;
	// }

//	public void setTransactionType(String transactionType) {
//		this.transactionType = transactionType;
//	}
//
//	public String getTransactionDate() {
//		return transactionDate;
//	}

//	public void setTransactionDate(String localDateTime) {
//		this.transactionDate = localDateTime;
//	}

//	public void setTransacionFields(Transaction transaction) {
//		setTransactionType(transaction.getType());
//		setTransactionDate(transaction.getDate());
//		setProductName(transaction.getProductName());
//	}

	/**
	 * Sets all the product-related fields using the Product parameter. If the
	 * product is not added reset the fields.
	 * 
	 * @param product the product whose fields should be copied.
	 * @modified by Abshir
	 */
	public void setProductFields(Product product) {
		this.productId = product.getId();
		this.productName = product.getName();
		this.productPrice = product.getPrice();
		this.productMinimumReorderLevel = product.getMinimumReorderLevel();
		this.productStock = product.getProducStock();
	}

	public void setOrderFields(Order order) {
		this.productName = order.getName();
		this.productId = order.getId();
		this.orderQuantity = order.getOrderQuantity();
	}

	/**
	 * @return the shoppingCart
	 */
//	public List<SellableProduct> getShoppingCart() {
//		return shoppingCart;
//	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
//	public void setShoppingCart(List<SellableProduct> shoppingCart) {
//		this.shoppingCart = shoppingCart;
//	}

	public int getProductMinimumReorderLevel() {
		return productMinimumReorderLevel;
	}

	public void setProductMinimumReorderLevel(int productMinimumReorderLevel) {
		this.productMinimumReorderLevel = productMinimumReorderLevel;
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {

		productId = 0;
		productName = "Null";
		productMinimumReorderLevel = 0;
		productPrice = 0;
		productStock = 0;
		memberId = 0;
		memberName = "Null";
		memberPhone = "Null";
		memberAddress = "Null";
	}
}
