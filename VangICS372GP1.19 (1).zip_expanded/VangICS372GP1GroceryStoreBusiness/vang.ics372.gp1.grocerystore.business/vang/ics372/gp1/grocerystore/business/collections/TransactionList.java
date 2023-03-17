package vang.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TransactionList extends LinkedList<ShoppingCart> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static TransactionList singletonTansactionList;
	private List<ShoppingCart> transactionList;
	
	public TransactionList() {
		transactionList = new LinkedList<>();
	}
	
	/*
	private TransactionList() {
		
	}
	
	public static TransactionList instance() {
		if (singletonTansactionList == null) {
			singletonTansactionList = new TransactionList();
		}
		return singletonTansactionList;
	}
	*/
	/**
	 * @author Banji
	 * 
	 * @param transaction
	 * @return
	 */
	public boolean addTransaction (ShoppingCart cart) {
		//System.out.println("TransList.add() line 34: add() is " + transactionList.add(cart));
		transactionList.add(cart);
		//System.out.println("TransList.add() line 35: add() transAction.toString " + transactionList.toString());
		return true;
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
	}
	
	/**
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param date the date for which the transactions have to be retrieved
	 * @return the iterator to the collection
	 */
	public String getTransactionsOnDate(String date) {
		StringBuilder string = new StringBuilder("----Transactions------------------------------------");
		for(ShoppingCart cart: transactionList) {
			System.out.println("TransList getTrans line 82: .equals is " + cart.getStringDate().equals(date));
			if(cart.getStringDate().equals(date)) {
				System.out.println("line 84: inside if ");
				string.append(cart.toString());
			}
		}
		string.append("\n----End transactions------------------------------------------");
		return string.toString();
	}

	/**
	 * Returns an iterator to all books
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<ShoppingCart> iterator() {
		return transactionList.iterator();
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder("\n----Transactions------------------------------------");
		
		if (transactionList.isEmpty()) {
			return "\nThe cart is empty";
		}
		else {
			System.out.println("TransList.toString line 82:");
			for (ShoppingCart cart : transactionList) {
				System.out.println("TransList.toString line 84: cart.toString() \n" + cart.toString());
				string.append(cart.toString());
			}		
		}
		string.append("\n----End Transactions------------------------------------------------------------");
		return string.toString();
	}
	
	
	
	
	
	
}


