package vang.ics372.gp1.grocerystore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;
import vang.ics372.gp1.grocerystore.business.entities.Transaction;



/**
 * This class implements the Iterator interface to iterate only on items that
 * satisfy a certain predicate.
 * 
 * @author Brahma Dathan
 *
 * @param <T> the type of the item to be traversed
 */
public class FilterIterator implements Iterator<Transaction> {
	private Transaction item;
	private Predicate<Transaction> predicate;
	private Iterator<ShoppingCart> iterator;

	/**
	 * Sets the iterator and predicate fields and positions to the first item that
	 * satisfies the predicate.
	 * 
	 * @param iterator  the iterator to the list
	 * @param predicate specifies the test
	 */
	public FilterIterator(Iterator<ShoppingCart> iterator, Predicate<Transaction> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}

	@Override
	public boolean hasNext() {
		return item != null;
	}

	@Override
	public Transaction next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		Transaction returnValue = item;
		getNextItem();
		return returnValue;
	}

	/*
	 * This method searches for the next item that satisfies the predicate. If none
	 * is found, the item field is set to null.
	 */
	private void getNextItem() {
		while (iterator.hasNext()) {
			item = iterator.next();
			if (predicate.test(item)) {
				return;
			}
		}
		item = null;
	}

}