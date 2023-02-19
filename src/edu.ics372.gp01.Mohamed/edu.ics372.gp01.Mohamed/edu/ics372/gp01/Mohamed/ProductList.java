package edu.ics372.gp01.Mohamed;
/*
 * Made class abstract because dont know 
 * full implementation;
 * this class will extended EntityList by Vang
 * */

public class ProductList extends EntityList1 {
	// id of the list needed maybe

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
	public void addEntity(Product product) {

		/*
		 * add product only if the id and name not existed in the productList
		 */

	}

	@Override
	// remove the product from the ProductList
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public String retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

}
