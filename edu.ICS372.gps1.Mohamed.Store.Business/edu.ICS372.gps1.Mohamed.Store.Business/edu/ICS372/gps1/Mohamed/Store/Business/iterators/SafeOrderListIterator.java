package edu.ICS372.gps1.Mohamed.Store.Business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ICS372.gps1.Mohamed.Store.Business.entities.Order;
import edu.ICS372.gps1.Mohamed.Store.Business.facade.Result;

public class SafeOrderListIterator implements Iterator<Result> {
	private Iterator<Order> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to
	 * 
	 * @param iterator
	 */
	public SafeOrderListIterator(Iterator<Order> iterator) {
		this.iterator = iterator;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Result next() {
		if (iterator.hasNext()) {
			result.setOrderFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
