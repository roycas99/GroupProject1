package vang.ics372.gp1.grocerystore.business.entities;

public class SellableProduct extends Product {
	/**
	 * A product that is being sold
	 */
	private static final long serialVersionUID = 1L;
	private int quantity;
	private int difference;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * @param productId
	 * @param price
	 * @param quantityInCart
	 */
	public SellableProduct(String name, int productId, double price, int quantity) {
		super(name, productId, price);
		this.quantity = quantity;
	}
	
	/**
	 * get the quantity in cart
	 * 
	 * @return int quantityInCart
	 */
	public int getQuantity () {
		//System.out.println("SelPro.getQuan ln 30: this.quantity = " + quantity);
		return quantity;
	}
	
	/**
	 * set the quantity in cart
	 * 
	 * @param quantityInCart
	 */
	public void setQuantity (int quantity) {
		//System.out.println("SelPro.setQuan ln 40: this.quantity = " + this.quantity);
		//System.out.println("SelPro.setQuan ln 41: requestedAmount = " + quantity);
		this.quantity = quantity;
		//System.out.println("SelPro.setQuan ln 43: this.quantity = " + this.quantity);
	}
	
	/**
	 * @return the difference
	 */
	public int getDifference() {
		return difference;
	}

	/**
	 * @param difference the difference to set
	 */
	public void setDifference(int difference) {
		this.difference = difference;
	}

	/**
	 * computes the total cost
	 * 
	 * @return price * quantityInCart
	 */
	public double computeTotalCost() {
		return super.getPrice() * quantity;
	}

	@Override
	public String toString() {
		return String.format("\n[id: %s, name: %20s, price: %.2f, quantity: %s, cost: %.2f]", super.getId(), 
				super.getName(), super.getPrice(), quantity, computeTotalCost());
	}
	
	/*
	@Override
	public String toString() {
		return "\n[productId = " + super.getId() + ", productName = " + 
				super.getName() + ", price = " + super.getPrice() + ", quantity = " + quantity + 
				", cost = " + String.format("%.2f", computeTotalCost()) +"]";
	}*/
}
