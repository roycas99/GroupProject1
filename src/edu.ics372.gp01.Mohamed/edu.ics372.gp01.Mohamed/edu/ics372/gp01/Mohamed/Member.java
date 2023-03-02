package edu.ics372.gp01.Mohamed;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Member {

	// fields
	private List<Transaction> transactions = new LinkedList<Transaction>();
	// list of checked out products needed
	private String name;
	private String address;
	private String phone;
	private Calendar dateJoined;
	private double fee;
	private String id;
	private static int idCounter;
	private final static String MEMBER_STRING = "m";

	public Member(String name, String address, String phone, Calendar dateJoined, double fee, String id) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.dateJoined = dateJoined;
		this.fee = fee;
		this.id = MEMBER_STRING + ++idCounter;

	} // close constructor

	/**
	 * Stores the product as bought to the member
	 * 
	 * @param Product the product to be checkedout
	 * @return true iff the product could be marked as checkedout. always true
	 *         currently need implemantion
	 */
//	public  abstract checkout(Product product) {
//		if (// product is checked out true) {
	// transactions.add(new Transaction("checked out", product.getProductName()));
//			return true;
//		}return false;

//	}

	/**
	 * Gets an iterator to the checkedout products
	 * 
	 * @return Iterator to the collection of checkedout products need more
	 *         implemantion
	 */
	public Iterator<Product> getProductsCheckedout() {
		// it returns checkedoutList.iterator
		return null;

	}

	/**
	 * we need this method if we need filters transaction only specification date
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param date the date for which the transactions have to be retrieved
	 * @return the iterator to the collection
	 */
	// public Iterator<Result> getTransactionsOnDate(Calendar date) {
	// return new SafeTransactionIterator(
	// new FilteredIterator(transactions.iterator(), transaction ->
	// transaction.onDate(date)));
	// }

	/**
	 * Returns the list of all transactions for this member.
	 * 
	 * @return the iterator to the list of Transaction objects
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	/**
	 * Getter for name
	 * 
	 * @return member name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for address
	 * 
	 * @return member address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Getter for id
	 * 
	 * @return member id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName member's new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Setter for address
	 * 
	 * @param newName member's new address
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	/**
	 * Setter for phone
	 * 
	 * @param newName member's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	public Calendar getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Calendar dateJoined) {
		this.dateJoined = dateJoined;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	/**
	 * String form of the member
	 * 
	 */
	@Override
	public String toString() {
		String string = "Member name " + name + " address " + address + " id " + id + "phone " + phone;
		string += " checkedout: [";
		for (Iterator iterator = checkedoutList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			string += " " + product.getProductName();
		}

		string += "] transactions: [";
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			string += (Transaction) iterator.next();
		}
		string += "]";
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Checks whether the member is equal to the one supplied
	 * 
	 * @param object the member who should be compared
	 * @return true iff the member ids match
	 */

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Member other = (Member) object;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/*
	 * Iterator method that iterator checkedoutList
	 */

//	public Iterator<Checkout> getCheckout() {
//		return checkedoutList.iterator();
//	}

	/* to save id into disk */
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	/* to retrieve id from disk */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

} // end class Member