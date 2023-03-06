package edu.ics372.gp01.Mohamed;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String type;
	private String memberId;
	private Calendar date;

	/**
	 * Creates the transaction with a given type and member identification number.
	 * The date is the current date.
	 * 
	 * @param type     The type of transaction
	 * @param memberId The identification number of the member
	 * 
	 */
	public Transaction(String type, String memberId) {
		this.type = type;
		this.memberId = memberId;
		date = new GregorianCalendar();
	}

	/**
	 * Checks whether this transaction is on the given date
	 * 
	 * @param date The date for which transactions are being sought
	 * @return true iff the dates match
	 */
	public boolean onDate(Calendar date) {
		return ((date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR))
				&& (date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH))
				&& (date.get(Calendar.DATE) == this.date.get(Calendar.DATE)))
				&& (date.get(Calendar.HOUR) == this.date.get(Calendar.HOUR));
	}

	/**
	 * Returns the type field
	 * 
	 * @return type field
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the memberId field
	 * 
	 * @return memberId field
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * Returns the date as a String issue: months range 0-11 solution: add one to
	 * the month
	 * 
	 * @return date with month, date, and year
	 */
	public String getDate() {

		return date.get(Calendar.MONTH) + 1 + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);

	}

	/**
	 * String form of the transaction
	 * 
	 */
	@Override
	public String toString() {
		return (type + "   " + memberId);
	}

} // end of Transaction
