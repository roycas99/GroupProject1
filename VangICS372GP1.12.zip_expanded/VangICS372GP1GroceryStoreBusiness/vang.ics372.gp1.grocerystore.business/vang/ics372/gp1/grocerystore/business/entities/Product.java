package vang.ics372.gp1.grocerystore.business.entities;

import java.io.Serializable;
import java.util.Objects;

import vang.ics372.gp1.grocerystore.business.interfaces.IteratorVisitor;
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

	public Product(int productId, String productName, double price, int minimumReorderLevel, int productStock) {
		super(productName, productId);
		this.price = price;
		this.minimumReorderLevel = minimumReorderLevel;
		this.productStock = productStock;
	}
	
	/**
	 * @author Say and Banji
	 * 
	 * overload, for subclass SellableProduct
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

	@Override
	public String toString() {
		return "Product [productId=" + super.getId() + ", productName=" + super.getName() + ", price=" + price
				+ ", minimumReorderLevel=" + minimumReorderLevel + "]";
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

	public void setPrice(double productPrice) {
		this.price = productPrice;
		
	}

	@Override
	public void accept(IteratorVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	

} // end of the class

