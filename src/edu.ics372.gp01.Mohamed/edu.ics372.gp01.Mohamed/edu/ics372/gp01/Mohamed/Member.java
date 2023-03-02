package edu.ics372.gp01.lawal;

public class Member {
	private String name;
	private int id;
	ShoppingCart shoppingCart;
	
	private static int serialNumber = 1;
	
	public Member (String name) {
		if (!validName(name) == false) {
			throw new IllegalArgumentException(name + " is not a valid name");
		}
		this.name = name;
		this.shoppingCart = new ShoppingCart();
		this.id = serialNumber();
	} // close constructor
	
	public void setName (String name) {
		if (!validName(name) == false) {
			throw new IllegalArgumentException(name + " is not a valid name");
		}
		this.name = name;		
	} // close setName
	
	
	public String getName () {
		return this.name;
	}
	
	public int getId () {
		return this.id;
	}
	
	public ShoppingCart getShopppingCart() {
		return this.shoppingCart;
	}
	
	public String toString () {
		String string = "Customer\n---------"
				+ " name:" + this.name + " id:" + this.id
				+ "\n" + this.shoppingCart.toString();
		
		return string;	
	} // close toString
  	
	
	private  boolean validName (String name) {
		boolean isValid = false;
		
		return isValid;
	} // close validName;
	
	
	public static int serialNumber () {
		return serialNumber++;
	} // close serialNumber

} // end class Member