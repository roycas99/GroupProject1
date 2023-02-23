package edu.ics372.gp01.Mohamed;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 * Made class abstract because dont know 
 * full implementation;
 * this class will extended EntityList by Vang
 * */

public class ProductList {

	//
	private List<Product> productListArray = new ArrayList<>();

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
	 * @paramProduct Type
	 */
	public void addProduct(Product product) {
		// if the list empty just add
		if (productListArray.isEmpty()) {
			productListArray.add(product);
		} // end of if

		/*
		 * otherwise add product only if the id and name not existed in the productList
		 */
		else {
			boolean canAdd = true;
			for (Product product1 : productListArray) {
				if (product1.getProductId() == product.getProductId()) {
					System.out.println("Throw error like - product is already exist");
					canAdd = false;
					break;
				}
				if (product1.getProductName().equals(product.getProductName())) {
					System.out.println("Throw error like - produc name is taken");
					canAdd = false;
					break;
				}

			} // end of loop
			if (canAdd) {
				productListArray.add(product);

			}
		} // end of else

	} // end of addProductMethod

	/*
	 * remove the product from the ProductList Parameter product id
	 * 
	 */
	public void remove(int productId) {
		if (productListArray.isEmpty()) {
			System.out.println("list is empty");

		} else {
			ListIterator<Product> iteratorList = productListArray.listIterator();
			while (iteratorList.hasNext()) {
				Product product = iteratorList.next();
				if (product.getProductId() == productId) {
					iteratorList.remove();
					System.out.println("the product id " + productId + " is removed");
					break;
				} else {
					System.out.println("Product is not found");
					break;
				}

			} // end of while
		} // end of else

	} // end of remove method

	/* retrieve the productlist */
	public void retrieve() {
		for (Product productRetrieve : productListArray) {
			System.out.println(productRetrieve);
		}

	}

} // end of the class
