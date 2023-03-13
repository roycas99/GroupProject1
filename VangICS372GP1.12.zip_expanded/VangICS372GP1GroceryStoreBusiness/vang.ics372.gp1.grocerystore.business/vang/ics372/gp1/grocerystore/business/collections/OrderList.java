package vang.ics372.gp1.grocerystore.business.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;

public class OrderList implements Iterable<Order> {
	List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

	private OrderList() {

	}

	public static OrderList instance() {
		if (orderList == null) {
			orderList = new OrderList();

		}
		return null;

		
	}

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
			System.out.println("OrederList line 39");
			boolean canAdd = true;
			for (Order order1 : orders) {
				System.out.println("OrederList line 42");
				if (order1.getId() == (order.getId())) {
					System.out.println("OrederList line 44");
					System.out.println("Throw error like - product is already exist");
					canAdd = false;
					break;

				}
				if (order1.getName().equals(order.getName())) {
					System.out.println("OrederList line 51");
					System.out.println("Throw error like - product name is taken");
					canAdd = false;
					break;

				}

			} // end of loop
			System.out.println("OrederList line 59");
			if (canAdd) {
				System.out.println("OrederList line 61");
				orders.add(order);
				return true;

			}
		} // end of else
		return false;
	}

	public String toString() {
		StringBuilder string = new StringBuilder("----Order List------------------------------------");
		
		if (this.orders.isEmpty()) {
			return "The order list is empty";
		}
		else {
			for (Order order : this.orders) {
				string.append(order.toString());
			}		
		}
		string.append("\n--------------------------------------------------------------------------");
		return string.toString();
	}

	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
}
