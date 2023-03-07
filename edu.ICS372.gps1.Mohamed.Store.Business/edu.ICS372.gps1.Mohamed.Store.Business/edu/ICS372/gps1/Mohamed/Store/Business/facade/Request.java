package edu.ICS372.gps1.Mohamed.Store.Business.facade;

import java.util.Calendar;

public class Request extends DataTransfer {
	private static Request request;
	private Calendar date;

	/**
	 * This is a singleton class. Hence the private constructor.
	 */
	private Request() {

	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the only instance
	 */
	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}



