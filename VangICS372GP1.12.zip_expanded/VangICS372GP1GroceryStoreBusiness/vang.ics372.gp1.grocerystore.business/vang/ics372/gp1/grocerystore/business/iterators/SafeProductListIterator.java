package vang.ics372.gp1.grocerystore.business.iterators;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Product objects. The user should supply an iterator to Product as the parameter
 * to the constructor.
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
	 * @param iterator2
	 */
	public SafeProductListIterator(Iterator<Product> iterator) {
		this.iterator = iterator;
	}
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
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