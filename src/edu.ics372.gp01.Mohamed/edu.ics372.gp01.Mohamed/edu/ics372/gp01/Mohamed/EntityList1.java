package edu.ics372.gp01.Mohamed;
/* this class is serializable
 * created by Say Vang
 * Modified by Abshir 
 * MemberList and Product list will be its subchilds. 
 * 
 * */

import java.io.Serializable;
import java.util.ArrayList;

public abstract class EntityList1 implements Serializable {

	private static final long serialVersionUID = -6784548878362734795L;

	int counter = 0;
	/* type Entity = Product */
	ArrayList<Product> entityList = new ArrayList<>();

	// add specific product into List
	public abstract void addEntity(Product product);

	public Product getProduct(int id) {
		return entityList.get(id);
	}

	/**
	 * @return the entityList
	 */
	public ArrayList<Product> getEntityList() {
		return entityList;
	}

	@Override
	public int hashCode() {
		counter++;
		return counter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityList1 other = (EntityList1) obj;
		if (entityList == null) {
			if (other.entityList != null)
				return false;
		} else if (!entityList.equals(other.entityList))
			return false;
		return true;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
