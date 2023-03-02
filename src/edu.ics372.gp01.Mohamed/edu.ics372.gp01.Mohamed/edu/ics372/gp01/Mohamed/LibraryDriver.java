package edu.ics372.gp01.Mohamed;

import java.util.Iterator;

/* this class is mainly me to test 
 * how my class cohesive
 * */

public class LibraryDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create Product Objects

		Product product1 = new Product("1", "Apple", 3.99, 10);
		Product product2 = new Product("2", "Orange", 2.99, 20);
		Product product3 = new Product("3", "Orange", 2.99, 20);
		Product product4 = new Product("4", "Mango", 4.99, 23);
		// create productList

		// Instantiating Singleton class
		ProductList productList = ProductList.instance();
		// addition
		productList.insertProduct(product1);
		productList.insertProduct(product2);
		productList.insertProduct(product4);

		// test duplicate productId and productName
		productList.insertProduct(product1);
		productList.insertProduct(product3);

		// Search
		System.out.println("------------------search result-----------------------");
		System.out.println(productList.searchProduct("4").getProductName());

		// removal

		System.out.println("-------------product 1 removal------------- ");

		productList.removeProduct("1");

		// display product objects
		for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println(product);

		}

		Transaction ts = new Transaction("visit", "1");
		System.out.println(ts.getDate());
		System.out.println(ts.getMemberId());

		// productList.remove(product1.getProductId());

	} // end of main

} // end of Driver class
