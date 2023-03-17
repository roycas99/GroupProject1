/**
     * <p>
     *     Stores all current and past transactions in a single location accessible to other parts of the system. 
     *     To assure operations with <code>Order</code> items are atomic, consistent isolated and durable the class has been 
     *     implemented as a singleton.  Orders are only accessed through mechanisms provided by the collection. An <code>Order</code>
     *     object does not keep track of the date an item was restocked so the collection cannot be searched for a specific order.
     *     The iterator canbe used to search all orders by productId or name.
     * </p>
     * @param orders collection of <code>Order</code> instances
     * @param orderList the only instance of the class instanciated
     *
     * @author Say Vang wrote the class
     * @author Banji Lawal wrote the documentation
     * 
     * updated 3/152023
 */

package vang.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vang.ics372.gp1.grocerystore.business.entities.Order;

public class OrderList implements Iterable<Order>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

	/**
	 * Private default construtor
	 */
	private OrderList() {
	}

	/**
	 * Static method which returns an <code>OrderList</code> if one does not already
	 * exist. If one had already been created returns null.
	 * 
	 * @return OrderList
	 * @author Say Vang
	 */
	public static OrderList instance() {
		if (orderList == null) {
			orderList = new OrderList();
		}
		return orderList;
	} // close instance

	/**
	 * Adds a new, unique order to the collection. Returns true if the insertion was
	 * successful. Falsse otherwise false
	 * 
	 * @param order must not be null or empty
	 * @return boolean
	 */
	public boolean insertOrder(Order order) {
		// if the list empty just add
		// System.out.println("OrderList.insertOrder line 63:");
		if (orders.isEmpty()) {
			// System.out.println("line 32: orders.isEmpty() is " + orders.isEmpty());
			orders.add(order);
			// System.out.println("line 34: oders.add() is " + orders.add(order));
			/// System.out.println("line 35: orders.toString()" + orders.toString());
			// System.out.println("Exiting OrderList.insertOrder line 36");
			return true;
		} // end of if

		/*
		 * otherwise add product only if the id and name not existed in the productList
		 */
		else {
			// System.out.println("line 44");
			boolean canAdd = true;
			for (Order order1 : orders) {
				// System.out.println("line 47: order1.toString()" + order1.toString());
				// System.out.println("line 48: order1.getId = " + order1.getId() + " :: " +
				// order.getId() + " = order.getId()");
				if (order1.getId() == (order.getId())) {
					// System.out.println("line 50");
					// System.out.println("Throw error like - product is already exist");
					canAdd = false;
					break;

				}
				System.out.println("line 56: order1.getName() = " + order1.getName() + " :: " + order.getName()
						+ " = order.getName()");
				if (order1.getName().equals(order.getName())) {
					// System.out.println("line 58");
					// System.out.println("Throw error like - product name is taken");
					canAdd = false;
					break;

				}

			} // end of loop
				// System.out.println("line 66: canAdd is " + canAdd);
			if (canAdd) {
				// System.out.println("line 68");
				orders.add(order);
				// System.out.println("line 70: orders.toString()" + orders.toString());
				// System.out.println("Exiting OrderList.insertOrder line 72");
				return true;

			}
			// System.out.println("line 75: orders.toString()" + orders.toString());
			// System.out.println("Exiting OrderList.insertOrder line 76");
		} // end of else
		return false;
	} // close insertOrder

	/**
	 * Checks whether a order with a given id exists.
	 * 
	 * @paramOrderId the id of the order
	 * @return true if the order exists
	 * 
	 */
	public Order searchOrder(int orderId) {
		for (Order element : orderList) {
			if (element.getId() == orderId) {
				return element;
			}

		} // end of loop
		return null;
	} // end of Search method

	/**
	 * remove the order from the orderList
	 * 
	 * @Parameter order id
	 * @return true if order could be removed
	 * 
	 */

	public boolean removeOrder(int orderId) {
		Order order = searchOrder(orderId);
		if (order == null) {
			return false;
		} else {
			return orders.remove(order);
		}
	} // end of remove method

	/**
	 * Returns a string of state of all <code>Order</code> items
	 */
	public String toString() {
		StringBuilder string = new StringBuilder("----Order List------------------------------------");

		if (this.orders.isEmpty()) {
			return "The order list is empty";
		} else {
			for (Order order : this.orders) {
				string.append(order.toString());
			}
		}
		string.append("\n--------------------------------------------------------------------------");
		return string.toString();
	}

	/**
	 * An iterator used to parse through orders
	 */
	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	} // close iterator

	/**
	 * Clears the list of all orders. Might make this method private.
	 * 
	 * @return
	 */
	public boolean empty() {
		orders.clear();
		if (orders.iterator().hasNext()) {
			return false;
		} else {
			return true;
		}
	}
} // end class OrderList