package edu.ICS372.gps1.Mohamed.Store.Business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ICS372.gps1.Mohamed.Store.Business.entities.Product;
import edu.ICS372.gps1.Mohamed.Store.Business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Product objects. The user should supply an iterator to Product as the
 * parameter to the constructor.
 * 
 * @author Brahma Dathan
 *
 */
public class SafeProductListIterator implements Iterator<Result> {
	private Iterator<Product> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to
	 * 
	 * @param iterator
	 */
	public SafeProductListIterator(Iterator<Product> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setProductFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
