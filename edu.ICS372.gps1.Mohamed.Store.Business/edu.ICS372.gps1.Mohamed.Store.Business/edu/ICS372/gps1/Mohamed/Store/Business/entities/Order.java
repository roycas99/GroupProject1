package edu.ICS372.gps1.Mohamed.Store.Business.entities;

import edu.ICS372.gps1.Mohamed.Store.Business.Exceptions.ProductCustomExceptions;

/*
 * @author Jeff
 * **/

public class Order extends Entity {

	private int orderQuantity;

	public Order(Product product) throws ProductCustomExceptions {
		super(product.getName(), product.getId());
		this.orderQuantity = 2 * product.getMinimumReorderLevel();
	}

	public Order(Product product, int amount) throws ProductCustomExceptions {
		super(product.getName(), product.getId());
		this.orderQuantity = amount;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int amount) {
		this.orderQuantity = amount;
	}

	public String toString() {
		return super.getName() + ", " + String.valueOf(super.getId()) + ", " + String.valueOf(orderQuantity);
	}

} // end of the class
