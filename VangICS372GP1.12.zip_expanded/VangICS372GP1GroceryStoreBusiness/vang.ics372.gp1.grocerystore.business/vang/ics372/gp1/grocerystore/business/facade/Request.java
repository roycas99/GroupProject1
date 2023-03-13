package vang.ics372.gp1.grocerystore.business.facade;


/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */

import java.util.Calendar;

/**
 * This class is used for requesting many of the results of the library system's
 * business logic to user interface. It is a singleton
 * 
 * At present, the Request object returns an int code,plus values of selected
 * fields of Book and Member. They are the book title, id, borrower id, due
 * date, member name, member phone, and member id.
 * 
 * @author Brahma Dathan
 *
 */
@SuppressWarnings("rawtypes")
public class Request extends DataHandOff {
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


