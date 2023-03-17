package vang.ics372.gp1.grocerystore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import vang.ics372.gp1.grocerystore.business.facade.Result;


/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Transaction objects. The user should supply an iterator to Transaction as
 * the parameter to the constructor.
 * 
 * @author Brahma Dathan
 *
 */
public class SafeTransactionIterator implements Iterator<Result> {
	private FilterIterator iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Book>
	 */
	public SafeTransactionIterator(FilterIterator iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setTransacionFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
