package vang.ics372.gp1.grocerystore.business.interfaces;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vang.ics372.gp1.grocerystore.business.entities.Entity;
import vang.ics372.gp1.grocerystore.business.entities.Member;

public abstract class AbstractList<T> implements Iterable<T>, Serializable{
	private static final long serialVersionUID = 1L;
	private List<T> members = new LinkedList<T>();

	/**
	 * private constructor
	 * access through instance();
	 */
	private AbstractList() {

	}
	
	public abstract AbstractList instance();


	public abstract T search(int id);
		
	public abstract boolean addMember(T t);
		
	public abstract boolean removeEntity(int id);

	public abstract Iterator<T> iterator();

	public abstract String toString();
}
