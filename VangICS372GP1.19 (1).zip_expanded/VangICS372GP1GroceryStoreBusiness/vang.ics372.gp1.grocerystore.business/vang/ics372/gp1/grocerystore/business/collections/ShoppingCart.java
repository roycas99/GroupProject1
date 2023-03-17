package vang.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;

public class ShoppingCart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ProductList productList;
	private List<SellableProduct> cart;
	private double totalPrice;
	private int memberId;
	
	private String stringDate = null;

	/**
	 * Initializes @param cart to an empty <code>ArrayList<Product</code>
	 * set @param totalPrice to zero.
	 * Gets an instance of <code>ProductList</code> singleton.
	 * 
	 * TODOS
	 * -------
	 * <p>
	 * Ideally would prefer to not get <code>ProductList</code> instance. This approach might have
	 * side effects and could lead to multiple instances of the singleton.  Better practice would be
	 * to invoke as needed through an iterator. like he showed in class.
	 * </p>
	 * 
	 */	
	public ShoppingCart () {
		productList = ProductList.instance();
		cart = new LinkedList<SellableProduct>();
		totalPrice = 0.00;
	} // close constructor
	
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	
	/**
	 * Try using this search
	 * 
	 * @param productId
	 * @return
	 */
	public SellableProduct search(int productId) {
		//System.out.println("ShopCart.search line 45: productId = " + productId);
		for (Iterator<SellableProduct> iterator = cart.iterator(); iterator.hasNext();) {
			SellableProduct product = iterator.next();
			//System.out.println("ShopCart.search line 48 product = " + productId + ", this.productId = " + product.getProductId());
			if (product.getProductId() == productId) {
				//System.out.println("ShopCart.search line 45: product to string: " + product);
				return product;
			}
		}
		return null;
	}
	

	
	/**
	 * @param object the checkOutDate to set
	 */
	public void setCheckOutDate(int month, int day, int year) {
		stringDate = (month + 1) + "/" + day + "/" + year;
		//System.out.println("ShopCart line 87: string = " + stringDate);
	}
	
	
	
	/* @Banji
	@SuppressWarnings("unused")
	public SellableProduct search (int productId) {
		Product product = ProductList.instance().searchProduct(productId);
		// If there is a wrapper method in ProductList we can do....
		// Product product ProductList.instance().contains(productId);
		if (product != null) {
			SellableProduct sellableProduct = (SellableProduct) product;
			return (SellableProduct) product;
		}
		return null;
	} // close search*/
	
	/**
	 * @return the stringDate
	 */
	public String getStringDate() {
		return stringDate;
	}

	/**
	 * @param stringDate the stringDate to set
	 */
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public boolean addSellableProduct (SellableProduct sellableProduct) {
		return add(sellableProduct);
	} // addSellableProduct


	public boolean add (SellableProduct sellableProduct) {
		totalPrice += sellableProduct.computeTotalCost();
		return cart.add(sellableProduct);
	} // 
	

	public boolean remove (SellableProduct sellableProduct) {		
		if (!cart.contains(sellableProduct)) {
			return false;
		}
		return cart.remove(sellableProduct);
	} // close remove
	

	public double getTotalPrice () {
		return totalPrice;
	} // close getTotalPrice
	
	
	/**
		 * Removes all items from <code>Shopping<Cart</code> restoring to 
		 * initial state
	 */
	public void empty () {
		this.cart = new LinkedList<SellableProduct>();
		totalPrice = 0;
	} // close empty

	
	/**
		 * Until the products are paid for the totalPrice (a record of the outstanding balance will be greater than zero)
		 * As each item in the cart is paid for the size of the cart decreases.
		 * 
		 * TODO
		 * -----
		 * Roll back transaction if payment fails
	 */
	/*
	public boolean checkout () {
		Transaction transaction = new Transaction(this, member.getId());
		empty()
		boolean checkOutResult = TransactionList.instance().add(transaction);
		if (!checkOutResult) {
			this = transaction.getShoppingCart();
		}
		return checkOutResult;
	}// close checkOut*/
	/*
	public double checkout () {
		double totalPrice = getTotalPrice();
		TransactionList.instance().add(this);
		empty();
		return totalPrice;
	}// close pay
	/*
	private boolean recordItemSales ()  {
		Calendar date = Calendar.getInstance();
		for (Iterator<SellableProduct> iterator = this.cart.iterator(); iterator.hasNext();) {
			SellableProduct sellableProduct = iterator.next();
			Transaction transaction = new Transaction(sellableProduct,memberId);
			if (!(TransactionList.instance().add(transaction))) {
				return false;
			}
		}
		return true;
	}*/
	
	/**
	 * Returns an iterator to all items in cart
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<SellableProduct> iterator() {
		return (Iterator<SellableProduct>) cart.iterator();
	}
	
	/**
		 * Creates a string conveying information about the cart's state
	 */
	/*
	public String toString () {
		String string = "";
		
		if (this.cart.isEmpty()) {
			string = "The cart is empty";
		}
		else {
			for (Product product : this.cart) {
				string += "\n" + product.toString();
			}
			string += "\nTotal Price:" + getTotalPrice();			
		}
		return string;
	} // close toString
	*/
	
	/**
	 * @Say Vang
	 * 
	 * new toString that works
	 * using StringBuilder
	 * 
	 * @return string
	 */
	public String toString () {
		StringBuilder string = new StringBuilder("\n----Shopping Cart------------------------------------");
		
		if (this.cart.isEmpty()) {
			return "The cart is empty";
		}
		else {
			for (SellableProduct sellableProduct : this.cart) {
				string.append(sellableProduct.toString());
			}		
		}
		string.append(String.format("\n*** Grocery Cart Total = %.2f",totalPrice));
		string.append("\n--------------------------------------------------------------------------");
		return string.toString();
	} // close toString
} // end class ShoppingCart