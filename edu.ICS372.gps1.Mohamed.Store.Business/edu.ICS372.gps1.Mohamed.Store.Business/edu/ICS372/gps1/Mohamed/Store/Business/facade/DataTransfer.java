package edu.ICS372.gps1.Mohamed.Store.Business.facade;

import edu.ICS372.gps1.Mohamed.Store.Business.entities.Product;

public abstract class DataTransfer<T> {
	// fields
	private String productId;
	private int productAmountInCart;
	private String productName;
	private double productPrice;
	private int ProductMinimumReorderLevel;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
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

	public int getMinimumReorderLevel() {
		return ProductMinimumReorderLevel;
	}

	public void setMinimumReorderLevel(int minimumReorderLevel) {
		this.ProductMinimumReorderLevel = minimumReorderLevel;
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
	 * Sets all the book-related fields using the Book parameter. If the book is not
	 * issued "none" is stored in the borrower and due date fields.
	 * 
	 * @param book the book whose fields should be copied.
	 */
	public void setProductFields(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productPrice = product.getPrice();
		this.ProductMinimumReorderLevel = product.getMinimumReorderLevel();
	}

	/**
	 * @return the shoppingCart
	 */
//	public List<SellableProduct> getShoppingCart() {
//		return shoppingCart;
//	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
//	public void setShoppingCart(List<SellableProduct> shoppingCart) {
//		this.shoppingCart = shoppingCart;
//	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {

		productId = "Null";
		productName = "Null";
		productPrice = 0;
		memberId = 0;
		memberName = "Null";
		memberPhone = "Null";
		memberAddress = "Null";
	}
}
