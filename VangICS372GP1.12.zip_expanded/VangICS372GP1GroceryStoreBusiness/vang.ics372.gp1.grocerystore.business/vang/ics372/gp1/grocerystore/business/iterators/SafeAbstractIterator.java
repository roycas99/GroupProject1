package vang.ics372.gp1.grocerystore.business.iterators;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath and adapted by Say Vang
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

import vang.ics372.gp1.grocerystore.business.entities.*;
import vang.ics372.gp1.grocerystore.business.facade.Result;
import vang.ics372.gp1.grocerystore.business.interfaces.IteratorVisitor;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Member objects. The user should supply an iterator to Member as the
 * parameter to the constructor.
 * 
 * @author Brahma Dathan
 * @param <T>
 *
 */
public class SafeAbstractIterator<T> implements Iterator<Result> , IteratorVisitor{
	private Iterator<T> iterator;
	private Result result = new Result();
	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Book>
	 */
	public SafeAbstractIterator(Iterator<T> iterator, T entity) {
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
			result.setMemberFields(iterator.next());
		}
		else {
		throw new NoSuchElementException("No such element");
		}
		return result;
	}
	
	/**
	 * visit pattern
	 * sets appropriate Result Member fields
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result visit(Member member) {
		if (iterator.hasNext()) {
			result.setMemberFields(iterator.next());
		}
		else {
			throw new NoSuchElementException("No such element");
			}
		return result;
	}
	
	/**
	 * visit pattern
	 * sets appropriate Result Product fields
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result visit(Product product) {
		if (iterator.hasNext()) {
			result.setProductFields(iterator.next());
		}
		else {
			throw new NoSuchElementException("No such element");
			}
		return result;
	}

	/*
	@SuppressWarnings("unchecked")
	public Result next() {
		if (iterator.hasNext()) {
			result.setMemberFields(iterator.next());
		}
		else {
		throw new NoSuchElementException("No such element");
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public Result nextProduct() {
		if (iterator.hasNext()) {
			result.setProductFields(iterator.next());
		}
		else {
		throw new NoSuchElementException("No such element");
		}
		return result;
	}*/

	@Override
	public Result next(Member member) {
		// TODO Auto-generated method stub
		return null;
	}
}


