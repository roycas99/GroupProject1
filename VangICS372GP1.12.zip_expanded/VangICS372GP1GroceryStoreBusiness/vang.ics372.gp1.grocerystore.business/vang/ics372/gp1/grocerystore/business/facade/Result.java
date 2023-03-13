package vang.ics372.gp1.grocerystore.business.facade;

import java.util.Iterator;

import vang.ics372.gp1.grocerystore.business.entities.Order;
import vang.ics372.gp1.grocerystore.business.entities.SellableProduct;

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

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
