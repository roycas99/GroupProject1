package edu.ICS372.gps1.Mohamed.Store.Business.facade;

public class Result extends DataTransfer {

	public static final int SUCCESS = 1;
	public static final int ERROR = 2;
	public static final int MEMBER_NOT_FOUND = 3;
	public static final int MEMBER_FOUND = 4;
	public static final int MEMBER_ADDED = 5;
	public static final int PRODUCT_ADDED = 6;
	public static final int PRODUCT_NOT_FOUND = 7;
	public static final int PRODUCT_FOUND = 8;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
