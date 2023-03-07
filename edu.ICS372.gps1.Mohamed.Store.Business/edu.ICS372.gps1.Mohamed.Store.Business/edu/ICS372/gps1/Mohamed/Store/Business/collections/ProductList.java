package edu.ICS372.gps1.Mohamed.Store.Business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ICS372.gps1.Mohamed.Store.Business.entities.Product;

/**
 * the class is list of products it can add,remove and retrieve a product. class
 * is serializable and has potential to extends EntityList. Author: By Abshir
 * 
 * @author Abshir
 */

public class ProductList implements Iterator<Product>, Serializable {

	// fields
	private List<Product> products = new LinkedList<>();
	private static ProductList productList;
	private static final long serialVersionUID = 1L;

	/* Singleton Design begin */
	// construction
	private ProductList() {

	}

	// end of private constructor

	public static ProductList instance() {
		if (productList == null) {
			productList = new ProductList();

		}

		return productList;
	}

	/* Singleton Design Phase end */

	/*
	 * method add product into list
	 * 
	 * @param Product product Type
	 * 
	 * @return true if is insertable
	 */
	public boolean insertProduct(Product product) {
		// if the list empty just add
		if (products.isEmpty()) {
			products.add(product);
			return true;
		} // end of if

		/*
		 * otherwise add product only if the id and name not existed in the productList
		 */
		else {
			boolean canAdd = true;
			for (Product product1 : products) {
				if (product1.getProductId() == (product.getProductId())) {
					System.out.println("Throw error like - product is already exist");
					canAdd = false;
					break;

				}
				if (product1.getProductName().equals(product.getProductName())) {
					System.out.println("Throw error like - product name is taken");
					canAdd = false;
					break;

				}

			} // end of loop
			if (canAdd) {
				products.add(product);
				return true;

			}
		} // end of else
		return false;

	} // end of addProductMethod

	/**
	 * Checks whether a product with a given product id exists.
	 * 
	 * @paramProductId the id of the product
	 * @return true if the product exists
	 * 
	 */
	public Product searchProduct(int productId) {
		for (Product element : products) {
			if (element.getProductId() == productId) {
				return element;
			}

		} // end of loop
		return null;
	} // end of Search method

	/**
	 * remove the product from the ProductList
	 * 
	 * @Parameter product id
	 * @return true if product could be removed
	 * 
	 */

	public boolean removeProduct(int productId) {
		Product product = searchProduct(productId);
		if (product == null) {
			return false;
		} else {
			return products.remove(product);
		}
	} // end of remove method

	/**
	 * Returns an iterator to all products
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Product> iterator() {
		return products.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	public String toString() {
		return products.toString();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product next() {
		// TODO Auto-generated method stub
		return null;
	}

} // end of the class
