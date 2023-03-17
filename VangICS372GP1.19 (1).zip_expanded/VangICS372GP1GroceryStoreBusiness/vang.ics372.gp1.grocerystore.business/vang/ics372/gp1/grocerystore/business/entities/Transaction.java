package vang.ics372.gp1.grocerystore.business.entities;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

import vang.ics372.gp1.grocerystore.business.collections.ShoppingCart;

/**
 * @author Banji Lawal
 * Records the sale of product to a member.  The fields should not be changed once 
 * an instance of <code>Transaction</code> is created.  The class should only have 
 * getters with no setters.  If the fields were mutable we could not be sure when 
 * a transaction occurred.
 * 
 * @param sellableProduct the item being sold
 * @param memberId who made the transaction
 * @param when the item was sold
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int memberId;
	private SellableProduct sellableProduct;
	private Calendar date;
	
	public Transaction(SellableProduct sellableProduct, Calendar date, int memberId) {
		this.sellableProduct = sellableProduct;
		this.memberId = memberId;
		this.date = date;
	} // close constructor


	/**
	 * 
	 * @return <code>SellableProduct</code>
	 */
	public SellableProduct getSellableProduct () {
		return sellableProduct;
	} // 
	/**
	 * Returns the memberId
	 * @return <code>int<code>
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	private void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * Returns value of @param date
	 * @return <code>Calendar</code>
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Returns date in more readable form as string MM/DD/YYYY
	 */
	public String printDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}


	public Object onDate(Calendar date2) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
	