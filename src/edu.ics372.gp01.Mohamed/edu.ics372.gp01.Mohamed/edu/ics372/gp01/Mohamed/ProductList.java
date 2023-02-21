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
        // getting id and name of product 
        int productIdLocal = product.getProductId();
        String productNameLocal = product.getProductName();

		/*
		 * add product only if the id and name not existed in the productList
		 */
		if (productListArray.contains(productIdLocal)) {
			System.out.println(" the product is already in the list");
		}

		else if (productListArray.contains(productNameLocal)) {

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

	

	public void retrieve() {
        Iterator<Product> iteratorList = productListArray.iterator();
        while(iteratorList.hasNext()){
		System.out.println(iteratorList.next());
        }
        
	}

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Product next() {
        // TODO Auto-generated method stub
        return next();
    }

	

}
