package vang.ics372.gp1.grocerystore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.facade.Result;

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
