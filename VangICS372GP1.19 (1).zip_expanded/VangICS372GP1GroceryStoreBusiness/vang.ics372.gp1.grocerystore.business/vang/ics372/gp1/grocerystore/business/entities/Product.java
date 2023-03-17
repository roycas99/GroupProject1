package vang.ics372.gp1.grocerystore.business.entities;

import java.io.Serializable;
import java.util.Objects;

/*
 * @author: Abshir
 * 
 * */

public class Product extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double price;
	private int minimumReorderLevel;
	private int productStock;

	/*
	 * Constructor for new product
	 * 
	 * @ the system will set product quantity automatically
	 * 
	 * @author Abshir Mohamed
	 */
	public Product(int productId, String productName, double price, int minimumReorderLevel) {
		super(productName, productId);
		this.price = price;
		this.minimumReorderLevel = minimumReorderLevel;
		Order order = new Order(this);

		this.productStock = order.getAmount();
	}

	/**
	 * @author Say and Banji
	 * 
	 *         overload, for subclass SellableProduct
	 * 
	 * @param name
	 * @param price
	 * @param price2
	 */
	public Product(String name, int id, double price) {
		super(name, id);
		this.price = price;
	}

	public int getProductId() {
		return super.getId();
	}

	public String getProductName() {
		return super.getName();
	}

	public double getPrice() {
		return price;
	}

	public int getMinimumReorderLevel() {
		return minimumReorderLevel;
	}

//	@Override
//	/* this method is helpful for searching a product */
//	public boolean matches(int productId) {
//		return this.productId.equals(productId);
//	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 * @return the productStock
	 */
	public int getProductStock() {
		return productStock;
	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 * @param productStock the productStock to set
	 */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String shipment() {
		return "\n[productId=" + super.getId() + ", productName=" + super.getName() + ", minimumReorderLevel="
				+ minimumReorderLevel + ", stock=" + productStock + "]";
	}

	@Override
	public String toString() {
		return "\n[productId=" + super.getId() + ", productName=" + super.getName() + ", price=" + price
				+ ", minimumReorderLevel=" + minimumReorderLevel + ", stock=" + productStock + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(minimumReorderLevel, price, super.getId(), super.getName());
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

	public boolean setPrice(double productPrice) {
		double oldPrice = price;
		this.price = productPrice;
		return (oldPrice != price);
	}
} // end of the class
