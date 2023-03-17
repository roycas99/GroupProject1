package vang.ics372.gp1.grocerystore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import vang.ics372.gp1.grocerystore.business.collections.*;
/**
 * This class hold the data for a given member of the grocery store; including
 * member id, address, phone, date of transactions list of transactions, items
 * in shopping cart and fee to be paid.
 *
 * updated 3/15/2023
 */
public class Member extends Entity {

	private static final long serialVersionUID = -7231447681100872980L;
	private final static int MEMBERCODE = 100000;
	private ShoppingCart shoppingCart;
	private TransactionList transactionList;
	private String address;
	private String phoneNumber;
	private Calendar date;
	private double feePaid;
	private static int memberIdCounter = 1;

	/**
	 * constructor creates new member
	 * @param String name
	 * @param int id
	 * @param String address
	 * @param String phoneNumber
	 * @param Calendar date
	 * @param double feePaid
	 */
	public Member(String name, String address, String phoneNumber, 
			Calendar date, double feePaid) {
		super(name, MEMBERCODE + (memberIdCounter++));
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.date = date;
		this.feePaid = feePaid;
		shoppingCart = new ShoppingCart();
		transactionList = new TransactionList();
	}
	
	/**
	 * get fee
	 * @return double feePaid
	 */
	public double getFeePaid() {
		return feePaid;
	}

	/**
	 * get time stamp
	 * @return LocalDateTime timeDate
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * set the timeDate
	 * @param timeDate the timeDate to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * return address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set address
	 * @param String address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get phone number
	 * @return String phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * set phone number
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the shoppingCart
	 */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	/**
	 * @return the transactionList
	 */
	public TransactionList getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(TransactionList transactionList) {
		this.transactionList = transactionList;
	}
	
	@Override
	public String toString() {
		return "[memberId = " + super.getId() + ", name = " + super.getName() +
				", address = " + address + ", phoneNumber = " + phoneNumber + 
				", member fee = " + feePaid + ", memebr since " + date + "]";
	}
	
	public static void retrieve(ObjectInputStream input) throws ClassNotFoundException, IOException {
		memberIdCounter = (int) input.readObject();
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(memberIdCounter);
	}
}
