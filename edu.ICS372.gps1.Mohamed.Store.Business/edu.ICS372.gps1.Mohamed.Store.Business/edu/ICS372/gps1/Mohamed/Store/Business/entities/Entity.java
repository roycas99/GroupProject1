package edu.ICS372.gps1.Mohamed.Store.Business.entities;

import java.io.Serializable;

import edu.ICS372.gps1.Mohamed.Store.Business.Exceptions.ProductCustomExceptions;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8468297837759306821L;
	private String name;
	private int id;

	/**
	 * constructor, sets name, id
	 * 
	 * @param String name
	 * @param int    id
	 * @throws ProductCustomExceptions
	 */
	public Entity(String name, int id) throws ProductCustomExceptions {
		if (id <= 0) {
			throw new ProductCustomExceptions("Product ID cannot be zero");
		}

		this.name = name;
		this.id = id;
	}

	/**
	 * get name
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set Name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get id
	 * 
	 * @return String id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		System.out.println("Inside Entity");
		return "Entity [name = " + name + ", id = " + id + "]";
	}
}
