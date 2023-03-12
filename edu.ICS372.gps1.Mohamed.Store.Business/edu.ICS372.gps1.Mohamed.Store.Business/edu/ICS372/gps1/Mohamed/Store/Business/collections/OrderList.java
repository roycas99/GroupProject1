package edu.ICS372.gps1.Mohamed.Store.Business.collections;

import java.io.Serializable;
/**
 *@author Jeff 
 * 
 * */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ICS372.gps1.Mohamed.Store.Business.Exceptions.ProductCustomExceptions;
import edu.ICS372.gps1.Mohamed.Store.Business.entities.Order;

public class OrderList implements Iterable<Order>, Serializable {
	List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;
	private static final long serialVersionUID = 1L;

	private OrderList() {

	}

	public static OrderList instance() {
		if (orderList == null) {
			orderList = new OrderList();

		}

		return orderList;
	}

	public boolean removeOrder(int productId) {
		Order order = searchOrder(productId);
		if (order == null) {
			return false;
		} else {
			return orders.remove(order);
		}
	} // end of remove method

	public boolean insertOrder(Order order) {
		// if the list empty just add
		if (orders.isEmpty()) {
			orders.add(order);
			return true;
		} // end of if

		/*
		 * otherwise add product only if the id and name not existed in the productList
		 */
		else {
			boolean canAdd = true;
			for (Order order1 : orders) {
				if (order1.getId() == (order.getId())) {
					new ProductCustomExceptions("order is already existed");
					canAdd = false;
					break;

				}
				if (order1.getName().equals(order.getName())) {
					new ProductCustomExceptions("order name is taken");
					canAdd = false;
					break;

				}

			} // end of loop
			if (canAdd) {
				orders.add(order);
				return true;

			}
		} // end of else
		return false;
	}

	public String toString() {
		String output = "";
		for (Order order : orders) {
			output += "Name: " + order.getName() + "ID: " + order.getId() + "Amount: " + order.getOrderQuantity();
		}
		return output;
	}

	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	}

	public boolean removeProduct(int productId) {
		Order order = searchOrder(productId);
		if (order == null) {
			return false;
		} else {
			return orders.remove(order);
		}
	}

	public Order searchOrder(int productId) {
		for (Order order : orders) {
			if (order.getId() == productId) {
				return order;
			}

		} // end of loop
		return null;
	}
}