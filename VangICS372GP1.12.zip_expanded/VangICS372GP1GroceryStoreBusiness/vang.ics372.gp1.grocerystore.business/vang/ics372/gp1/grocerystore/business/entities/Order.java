package vang.ics372.gp1.grocerystore.business.entities;

import vang.ics372.gp1.grocerystore.business.interfaces.IteratorVisitor;

public class Order extends Entity{
	private int amount;

	public Order(Product product) {
		super(product.getName(), product.getId());
		this.amount = 2 * product.getMinimumReorderLevel();
	}

	public Order(Product product, int amount) {
		super(product.getName(), product.getId());
		this.amount = amount;
	}

	public String getName() {
		return super.getName();
	}

	public int getId() {
		return super.getId();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String toString() {
		
		return String.format("\n[name: %20s, id: %4s, orderAmount: %4s]", 
				super.getName(), super.getId(), amount + "]");

	}

	@Override
	public void accept(IteratorVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}

