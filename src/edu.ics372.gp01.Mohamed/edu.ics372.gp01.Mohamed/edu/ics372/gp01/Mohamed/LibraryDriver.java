package edu.ics372.gp01.Mohamed;
/* this class is mainly me to test 
 * how my class cohesive
 * */

public class LibraryDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create Product Objects

		Product product1 = new Product(1, "Apple", 3.99, 10);
		Product product2 = new Product(2, "Orange", 2.99, 20);
		Product product3 = new Product(3, "Orange", 2.99, 20);

		// create productList

		// Instantiating Singleton class
		ProductList productList = ProductList.instance();
		// addition
		productList.addProduct(product1);
		productList.addProduct(product2);
		// test duplicate productId and productName
		productList.addProduct(product1);
		productList.addProduct(product3);

		// display product objects

		productList.retrieve();

		// removal

		productList.remove(product1.getProductId());

		// productList.remove(product1.getProductId());

		productList.retrieve();

	} // end of main

} // end of Driver class
