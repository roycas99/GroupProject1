package vang.ics372.gp1.grocerystore.business.facade;

/**
 * Operations performed by the system return a code indicating their outcome.  Result also assist error handling 
 * and understanding the system state.  Datatransfers are the main sources of operations that provide result codes 
 * as feedback
 *
 * @author Say Vng, Abshir, Jeff
 * @author Documented by Banji
 * 
 *  updated 3/15/2023
 */
@SuppressWarnings("rawtypes")
public class Result extends DataHandOff{
	
	
	public static final int SUCCESS = 1;
	public static final int ERROR = 2;
	public static final int MEMBER_NOT_FOUND = 3;
	public static final int MEMBER_FOUND = 4;
	public static final int MEMBER_ADDED = 5;
	public static final int MEMBER_REMOVED = 6;
	public static final int PRODUCT_ADDED = 7;
	public static final int PRODUCT_NOT_FOUND = 8;
	public static final int PRODUCT_NOT_ADDED = 9;
	public static final int PRODUCT_FOUND = 10;
	public static final int MEMBER_NOT_ADDED = 11;
	public static final int PATIAL_PRODUCT_AMOUNT_ADDED = 12;
	public static final int PARTIAL_PRODUCT_ADDED = 13;
	public static final int SHOPPING_CART_PAID = 14;
	public static final int SHOPPING_CART_PENDING = 15;
	public static final int SHOPPING_ADDED_TO_TRANSACTION = 16;
	public static final int SHIPMENT_PROCESSED = 17;
	public static final int SHIPMENT_NOT_PROCESSED = 18;
	public static final int PRICE_CHANGED = 19;
	public static final int PRICE_NOT_CHANGED = 20;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
