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
	public void addEntity(Product product) {

		/*
		 * add product only if the id and name not existed in the productList
		 */
		if (productListArray.contains(product.getProductId())) {
			System.out.println(" the product is already in the list");
		}

		else if (productListArray.contains(product.getProductName())) {

			System.out.println("choose another name for product");
		}

		else {
			productListArray.add(product);
		}
	}

	// remove the product from the ProductList
//	public void remove(Product product) {
//		if (entityList.contains(product.getProductId())) {
//			entityList.remove(product);
//		}
//
//		else {
//			System.out.println("product not existed");
//		}
//
//	}

	Iterator<Product> iteratorList = productListArray.iterator();

	public void retrieve() {
		System.out.println(productListArray.size());
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product next() {
		// TODO Auto-generated method stub
		return next();
	}

}
