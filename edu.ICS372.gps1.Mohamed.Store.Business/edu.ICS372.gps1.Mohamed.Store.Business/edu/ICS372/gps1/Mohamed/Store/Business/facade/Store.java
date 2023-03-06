package edu.ICS372.gps1.Mohamed.Store.Business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import edu.ICS372.gps1.Mohamed.Store.Business.collections.MemberList;
import edu.ICS372.gps1.Mohamed.Store.Business.collections.ProductList;
import edu.ICS372.gps1.Mohamed.Store.Business.entities.Product;
import edu.ICS372.gps1.Mohamed.Store.Business.iterators.SafeProductListIterator;

public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList catalog = ProductList.instance();
	private MemberList members = null;
	private static Store singletonGroceryStore;

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */
	private Store() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Store instance() {
		if (singletonGroceryStore == null) {
			return singletonGroceryStore = new Store();
		} else {
			return singletonGroceryStore;
		}
	}

	/**
	 * Organizes the operations for adding a product
	 * 
	 * @param Reuest request
	 * @param author Abshir
	 * @return the result object
	 */
	public Result addProduct(Request request) {
		Result result = new Result();
		Product product = new Product(request.getProductName(), request.getProductId(), request.getProductPrice(),
				request.getMinimumReorderLevel());
		if (catalog.insertProduct(product)) {
			result.setResultCode(Result.SUCCESS);
			result.setProductFields(product);
			return result;
		}
		result.setResultCode(Result.ERROR);
		return result;
	}

	/**
	 * Organizes the operations for adding a member
	 * 
	 * @param name    member name
	 * @param address member address
	 * @param phone   member phone
	 * @return the Member object created
	 */
//	public Result addMember(Request request) {
//		Result result = new Result();
//		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone(),
//				request.getDate(), request.getMemberFee());
//		System.out.println("Line 108, GorceryStore.addMember(): memberId = " + member.getId() + ", memberName = "
//				+ member.getName() + ", memberAdress = " + member.getAddress() + ", memberPhoneNumber = "
//				+ member.getPhoneNumber());
//		if (members.addMember(member)) {
//			result.setResultCode(Result.MEMBER_ADDED);
//			result.setMemberFields(member);
//			System.out.println(
//					"Line 114, GS.addMember.members.addMember(member): ResultCode = " + result.getResultCode());
//			return result;
//		} else {
//			result.setResultCode(Result.ERROR);
//			System.out.println("Line 119, else: resultCode = " + result.getResultCode());
//			return result;
//		}
//	}
//
//	/**
//	 * Searches for a given member
//	 * 
//	 * @param memberId id of the member
//	 * @return true iff the member is in the member list collection
//	 */
//	public Result retreiveMember(Request request) {
//		System.out.println("Line 132, GorceryStore.retreiveMember(): memberId = " + request.getMemberId());
//		Result result = new Result();
//		Member member = members.search(request.getMemberId());
//		System.out.println("Line 135, member.getId = " + member.getId());
//		if (member == null) {
//			result.setResultCode(Result.MEMBER_NOT_FOUND);
//			System.out.println(
//					"Line 114, GS.addMember.members.addMember(member): ResultCode = " + result.getResultCode());
//		} else {
//			result.setResultCode(Result.MEMBER_FOUND);
//			result.setMemberFields(member);
//		}
//		return result;
//	}

	public Result retreiveProduct(Request request) {
		System.out.println("Line 132, GorceryStore.retreiveProduct(): ProductId = " + request.getProductId());
		Result result = new Result();
		System.out.println(request.getProductId());
		Product product = catalog.searchProduct(request.getProductId());

		System.out.println("Line 135, product.getId = " + product.getProductId());

		if (product.matches(null)) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			System.out.println(
					"Line 114, GS.addProduct.products.addProduct(product): ResultCode = " + result.getResultCode());
		} else {
			result.setResultCode(Result.PRODUCT_FOUND);
			result.setProductFields(product);
		}
		return result;
	}

	public Iterator<Result> getProducts(Request request) {
		Product product = catalog.searchProduct(request.getProductId());
		if (product == null) {
			return null;
		} else {
			return new SafeProductListIterator(catalog.iterator());
		}
	}

	/**
	 * Removes a specific book from the catalog
	 * 
	 * @param bookId id of the book
	 * @return a code representing the outcome
	 */
	public Result removeProduct(Request request) {
		Result result = new Result();
		Product product = catalog.searchProduct(request.getProductId());
		System.out.println(product.getProductId());
		if (product.equals(null)) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		result.setProductFields(product);

		if (catalog.removeProduct(request.getProductId())) {
			result.setResultCode(Result.SUCCESS);
			return result;
		} else {
			result.setResultCode(Result.ERROR);
			return result;
		}
	}

	/**
	 * Returns an iterator to the info. in transactions for a specific member on a
	 * certain date
	 * 
	 * @param memberId member id
	 * @param date     date of issue
	 * @return iterator to the collection
	 */
//	public Iterator<Result> getTransactions(Request request) {
//		Member member = members.search(request.getMemberId());
//		if (member == null) {
//			return new LinkedList<Result>().iterator();
//		}
//		return member.getTransactionsOnDate(request.getDate());
//	}

	/**
	 * Returns an iterator to Member info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
//	public Iterator<Result> getMembers() {
//		return new SafeMemberListIterator(members.iterator());
//	}

	/**
	 * Returns an iterator to productList. Iterator is a copy, returns only copies
	 * of the product list
	 * 
	 * @return an Iterator to Result - only the Book fields are valid.
	 */
	public Iterator<Result> getProductList() {
		return new SafeProductListIterator(catalog.iterator());
	}

	/**
	 * Retrieves a deserialized version of the groceryStore from disk
	 * 
	 * @return a GroceryStore object
	 */

	/**
	 * Retrieves a de-serialized version of the GroceryStore from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static Store retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			singletonGroceryStore = (Store) input.readObject();
			// Member.retrieve(input);
			return singletonGroceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	} // end of Retrieve

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonGroceryStore);
			// Member.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}// end of Save

	/**
	 * String form of the library
	 * 
	 */
	@Override
	public String toString() {
		return catalog + "\n" + members;
	}

//	public Result removeMember(Request instance) {
//		// TODO Auto-generated method stub
//		return null;
//	}// end of RemoveMember
//
//	public Result addToShoppingCart(Request request) {
//		ShoppingCart currentCart = (members.search(request.getMemberId())).getShoppingCart();
//		currentCart.addToShoppingCart(request.getProductId(), request.getProductAmountInCart());
//		return null;
//	} // end of addToShoppingCart
//
//	public Iterator<Result> getShoppingCart(Request request) {
//		Member member = members.search(request.getMemberId());
//		if (member == null) {
//			return null;
//		} else {
//			return new SafeSellableProductListIterator(member.getShoppingCart().iterator());
//		}
//	} // end of Iterator
} // end of the class
