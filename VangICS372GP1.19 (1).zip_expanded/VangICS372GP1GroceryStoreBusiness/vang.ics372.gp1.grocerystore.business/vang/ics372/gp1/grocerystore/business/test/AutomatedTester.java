package vang.ics372.gp1.grocerystore.business.test;

import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;
import vang.ics372.gp1.grocerystore.business.facade.GroceryStore;
import vang.ics372.gp1.grocerystore.business.facade.Request;
import vang.ics372.gp1.grocerystore.business.facade.Result;

/**
 * This class generates sample automated tests for the GroceryStore system using
 * asserts.
 * 
 * @author Brahma Dathan
 *
 */
public class AutomatedTester {
	private static final double MEMBERFEE = 9.99;
	private static final int MEMBER = 100001;
	private static final int PRODUCTID = 2;
	private static final double PRODUCTPRICE = 3.0;
	private GroceryStore store = GroceryStore.instance();
	Request request = Request.instance();

	private String[] names = { "n1", "n2", "n3", "n4", "n5" };
	private String[] addresses = { "a1", "a2", "a3", "a4", "a5" };
	private String[] phones = { "p1", "p2", "p3", "p4", "p5" };

	private Member[] members = new Member[5];
	private String[] products = { "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9", "t10", "t11", "t12", "t13",
			"t14", "t15", "t16", "t17", "t18", "t19", "t20" };
	private SellableProduct[] sellableProducts;

	private int[] productIds = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	private int[] minimums = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

	// private int[] stock = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
	// 10, 10, 10, 10, 10, 10, 10 };

	private double[] prices = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	/**
	 * test addMember
	 * 
	 * @param request obj
	 * @return Result obj
	 */
	@SuppressWarnings("static-access")
	public Result testAddMember(Request request) {
		Result result = new Result();
		System.out.println("\nTesting GroceryStore.instance().addMember()");
		for (int count = 0; count < 5; count++) {
			request.setMemberAddress(addresses[count]);
			request.setMemberName(names[count]);
			request.setMemberPhone(phones[count]);
			request.setMemberFee(MEMBERFEE);
			result = store.addMember(Request.instance());
			assert result.getResultCode() == (Result.MEMBER_ADDED) : "MEMBER_NOT_ADDED";

			assert (result.getMemberName().equals(names[count])) : "MEMBER_NAME_DOES_MATCH";
			assert (result.getMemberPhone().equals(phones[count])) : "PHONE_DOES_NOT_MATCH";
			assert (result.getMemberFee() == MEMBERFEE) : "FEE_DOES_NOT_MATCH";
		}
		System.out.println("*****addMember() : Passed");
		return result;
	}

	// 1
	public void testRemoveMember(Request request) {
		System.out.println("\nTesting GroceryStore.instance().removeMember()");
		// System.out.println("memberId = " + request.getId());
		if (request.isAutoTester()) {
			request.setMemberId(MEMBER);
		}
		// System.out.println("memberId = " + request.getId());
		Result result = store.removeMember(Request.instance());
		// System.out.println("getResultCode = " + result.getResultCode());
		assert result.getResultCode() == Result.MEMBER_REMOVED : "MEMBER_NOT_FOUND";
		System.out.println("*****removeMember() : Passed");
	}

	// 4
	public void testAddProduct(Request request) {
		System.out.println("\nTesting GroceryStore.instance().addProduct()");
		if (request.isAutoTester()) {
			for (int count = 0; count < products.length; count++) {
				request.setProductName(products[count]);
				request.setProductMinimumReorderLevel(minimums[count]);
				request.setProductId(productIds[count]);
				request.setProductPrice(prices[count]);
				// request.setProductInStock(stock[count]);
				Result result = store.addProduct(Request.instance());
				assert result.getResultCode() == Result.PRODUCT_ADDED : "PRODUCT_NOT_ADDED";
				assert result.getProductName().equals(products[count]) : "NAME_DOES_NOT_MATCH";
				assert result.getProductPrice() == (prices[count]) : "PRICE_DOES_NOT_NATCH";
				assert result.getProductId() == (productIds[count]) : "ID_DOES_NOT_MATCH";
			}
		} else {
			Result result = store.addProduct(Request.instance());
			assert result.getResultCode() == Result.PRODUCT_ADDED : "PRODUCT_NOT_ADDED";
			assert result.getProductName().equals(request.getProductName()) : "NAME_DOES_NOT_MATCH";
			assert result.getProductPrice() == (request.getProductPrice()) : "PRICE_DOES_NOT_NATCH";
			assert result.getProductId() == (request.getProductId()) : "ID_DOES_NOT_MATCH";
		}

		System.out.println("*****addProduct() : Passed");
	}

	// 5
//	public void testCheckOut(Request request) {
//		System.out.println("\nTesting GroceryStore.instance().checkOut()");
//		Result result = new Result();
//		if (request.isAutoTester()) {
//			request.setId(MEMBER + 1);
//			System.out.println("Adding to shopping cart()");
//			for (int count = 0; count < 5; count++) {
//				request.setId(productIds[count]);
//				request.setName(products[count]);
//				// System.out.println("prices[" + count + "]" + "=" + prices[count]);
//				request.setSellableProductPrice(prices[count]);
//				request.setRequestedAmount(2);
//				result = store.addToShoppingCart(request);
//				// System.out.println("Result.getRequestedAm = " + result.getRequestedAmount());
//				assert result.getResultCode() == Result.PRODUCT_ADDED : "PRODUCT_NOT_ADDED";
//			}
//		} else {
//			request.setId(MEMBER + 1);
//			result = store.retreiveProduct(request);
//			request.setId(result.getId());
//			request.setName(result.getName());
//			// System.out.println("A.T. line 123 : request.getSellProName = " +
//			// request.getSellableProductName());
//			request.setSellableProductPrice(result.getProductPrice());
//			request.setRequestedAmount(request.getRequestedAmount());
//			result = store.addToShoppingCart(request);
//			// System.out.println("Result.getRequestedAm = " + result.getRequestedAmount());
//			assert result.getResultCode() == Result.PRODUCT_ADDED : "PRODUCT_NOT_ADDED";
//		}
//		System.out.println("**addShoppinCart() : Passed");
//		System.out.println("Checking out");
//		result = store.checkOut(request);
//		System.out.println("ResultCode = " + result.getResultCode());
//		assert result.getResultCode() == Result.SHOPPING_CART_PAID : "SHOPPING_PENDING";
//		System.out.println("*****checkOut() : Passed");
//	}

	// 7
//	public void testProcessShipment(Request request) {
//		System.out.println("\nTesting GroceryStore.instance().processShipment()");
//		Result result = new Result();
//		result = store.processShipment(request);
//		assert result.getResultCode() == Result.SHIPMENT_PROCESSED : "SHIPPEMT_NOT_PROCESS";
//		System.out.println("*****processShipment() : Passed");
//	}

	// 8
//	public void testChangeProductPrice(Request request) {
//		System.out.println("\nTesting GroceryStore.instance().changeProductPrice()");
//		if(request.isAutoTester()) {
//			//System.out.println("A.T line 152 isAutoTester is " + request.isAutoTester());
//			request.setId(PRODUCTID);
//			request.setProductPrice(PRODUCTPRICE);
//		}
//		else {
//			request.setProductPrice(request.getProductNewPrice());
//		}
//		//System.out.println("line 159: request.getProId = " + request.getId());
//		//System.out.println("line 160: request.getProPrice = " + request.getProductPrice());
//		//System.out.println("line 161: request.getProNewPrice = " + request.getProductNewPrice());
//		Result result = store.changeProductPrice(request,result);
//		assert result.getResultCode() == Result.PRICE_CHANGED : "PRICE_NOT_CHANGED";
//		System.out.println("*****changeProductPrice() : Passed");
//	}

	public void testAll(Request request) {
		System.out.println("\nCreating member and product array...");
		System.out.println("Autotester is running!");
		testAddMember(request);
		testRemoveMember(request);
		testAddProduct(request);
		// testCheckOut(request);
		// testProcessShipment(request);
		// testChangeProductPrice(request);
	}

	public static void main(String[] args) {
		Request request = Request.instance();
		request.setAutoTester(true);
		new AutomatedTester().testAll(request);
	}
}
