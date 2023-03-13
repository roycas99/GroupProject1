package vang.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.entities.Transaction;

public class TransactionList extends LinkedList<ShoppingCart> implements Serializable{
	
	private static TransactionList singletonTansactionList;
	private List<ShoppingCart> transactionList = new LinkedList<>();
	private Calendar date;
	
private TransactionList() {
		
	}
	
	public static TransactionList instance() {
		if (singletonTansactionList == null) {
			singletonTansactionList = new TransactionList();
		}
		return singletonTansactionList;
	}
	/**
	 * @author Banji
	 * 
	 * @param transaction
	 * @return
	 */
	public boolean addTransaction (ShoppingCart transaction) {
		if (transactionList.contains(transaction)) {
			return false;
		}
		return transactionList.add(transaction);
	} //
	
	/**
	 * @return the transactionList
	 */
	public List<ShoppingCart> getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(List<ShoppingCart> transactionList) {
		this.transactionList = transactionList;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return getDate();
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Returns an iterator to all books
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<ShoppingCart> iterator() {
		return transactionList.iterator();
	}
}


