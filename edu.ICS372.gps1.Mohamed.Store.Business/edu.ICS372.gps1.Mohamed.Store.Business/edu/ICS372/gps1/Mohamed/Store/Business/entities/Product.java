package edu.ICS372.gps1.Mohamed.Store.Business.entities;

import java.io.Serializable;
import java.util.Objects;
/*
 * @author: Abshir
 * 
 * */

import edu.ICS372.gps1.Mohamed.Store.Business.Exceptions.ProductCustomExceptions;

public class Product extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double price;
	private int minimumReorderLevel;
	private int producStock;

	public Product(int productId, String productName, double price, int minimumReorderLevel)
			throws ProductCustomExceptions {
		super(productName, productId);
		/*
		 * Product id cannot be zero or less than zero
		 */

		this.price = price;
		this.minimumReorderLevel = minimumReorderLevel;
		/*
		 * the system will automatically reorder the new product and set its quanittiy
		 * 2xMinimum reorder Level
		 */
		Order order = new Order(this);
		producStock = order.getOrderQuantity();
		// OrderList orderList = OrderList.instance();
		// orderList.add()

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMinimumReorderLevel() {
		return minimumReorderLevel;
	}

	public int getProducStock() {
		return producStock;
	}

	public void setProducStock(int producStock) {
		this.producStock = producStock;
	}
//	@Override
//	/* this method is helpful for searching a product */
//	public boolean matches(int productId) {
//		return this.productId.equals(productId);
//	}

	@Override
	public String toString() {
		return "Product [productId=" + super.getId() + ", productName=" + super.getName() + ", price=" + price
				+ ", productStock=" + producStock + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(producStock, minimumReorderLevel, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		return minimumReorderLevel == other.minimumReorderLevel
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& super.getId() == other.getId() && Objects.equals(super.getName(), other.getName());
	}

} // end of the class
