package vang.ics372.gp1.grocerystore.business.entities;

/**
 * This class holds the data for any order
 * including product name, product id, and
 * the number of the product ordered. 
 *
 * updated 3/15/2023
 */
public class Order extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int amount;
	
	/**
	 * Constructor for an order of a certain product
	 * that orders twice the minimum reorder level
	 * of the product.
	 * @param product
	 */
	public Order(Product product) {
		super(product.getName(), product.getId());
		this.amount = 2 * product.getMinimumReorderLevel();
	}
	
	/**
	 * Constructor for an order of a certain product that
	 * requests a specific integer amount of the product
	 * @param product
	 * @param amount
	 */
	public Order(Product product, int amount) {
		super(product.getName(), product.getId());
		this.amount = amount;
	}

	/**
	 * getter for product name of the Order
	 * @return
	 */
	public String getName() {
		return super.getName();
	}
	
	/**
	 * getter for product id of the order
	 * @return
	 */
	public int getId() {
		return super.getId();
	}
	
	/**
	 * getter for amount ordered of the product
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * setter for order amount
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * toString method to display Order details
	 * @return
	 */
	public String toString() {
		
		return String.format("\n[name: %20s, id: %4s, orderAmount: %4s]", 
				super.getName(), super.getId(), amount + "]");
	}
}


