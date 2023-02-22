package edu.ics372.gp01.Mohamed;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Made class abstract because dont know 
 * full implementation;
 * this class will extended EntityList by Vang
 * */

public class ProductList implements Iterator<Product> {

// id of the list needed maybe

	//
	private ArrayList<Product> productListArray = new ArrayList<>();

	/* Singleton Design begin */
	private static ProductList productListSingleton;

	private ProductList() {

	}// end of private constructor

	public static ProductList instance() {
		if (productListSingleton == null) {
			productListSingleton = new ProductList();

		}

		return productListSingleton;
	}

	/* Singleton Design Phase end */
	/*
	 * method add product into list
	 * 
	 * @param Product Type
	 */
	public void addProduct(Product product) {

		/*
		 * add product only if the id and name not existed in the productList
		 */
		productListArray.add(product);

	} // end of addProductMethod

	/*
	 * remove the product from the ProductList Parameter product id
	 */
	public void remove(int productId) {
		Iterator<Product> iteratorList = productListArray.iterator();

		while (iteratorList.hasNext()) {
			Product product = iteratorList.next();

			if (product.getProductId() == productId)
				iteratorList.remove();
		} // end of while

	} // end of remove method

	/* retrieve the productlist */
	public void retrieve() {
		Iterator<Product> iteratorList = productListArray.iterator();
		while (iteratorList.hasNext()) {
			System.out.println(iteratorList.next());
		}

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (next() == null) {
			return false;
		}

		else {
			return true;
		}
	}

	@Override
	public Product next() {
		// TODO Auto-generated method stub
		return next();
	}

}
