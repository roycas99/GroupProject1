package vang.ics372.gp1.grocerystore.business.interfaces;

import vang.ics372.gp1.grocerystore.business.entities.Member;
import vang.ics372.gp1.grocerystore.business.entities.Product;
import vang.ics372.gp1.grocerystore.business.facade.Result;

public interface Data {
	public Result visit(Member member);
	public Result visit(Product product);
}
