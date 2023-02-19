package edu.ics372.gp01.Mohamed;

import java.util.Objects;

public class Product {
	private int productId;
	private String productName;
	private double price;
	private int minimumReorderLevel;

	public Product(int productId, String productName, double price, int minimumReorderLevel) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.minimumReorderLevel = minimumReorderLevel;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public int getMinimumReorderLevel() {
		return minimumReorderLevel;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", minimumReorderLevel=" + minimumReorderLevel + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(minimumReorderLevel, price, productId, productName);
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
				&& productId == other.productId && Objects.equals(productName, other.productName);
	}

} // end of the class