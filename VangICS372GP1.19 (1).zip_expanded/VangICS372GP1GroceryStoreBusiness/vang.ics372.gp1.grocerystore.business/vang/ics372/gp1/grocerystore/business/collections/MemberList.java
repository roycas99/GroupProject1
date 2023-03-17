package vang.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * MeberList, keeps a collection of Member objects
 */

import vang.ics372.gp1.grocerystore.business.entities.Member;

public class MemberList implements Iterable<Member>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();
	private static MemberList singletonMemberList;

	/**
	 * private constructor
	 * access through instance();
	 */
	private MemberList() {

	}
	
	/**
	 * A singleton pattern
	 * 
	 * @return a singletonMemberList
	 */
	public static MemberList instance() {
		if (singletonMemberList == null) {
			singletonMemberList = new MemberList();
		}
		return singletonMemberList;
	}

	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberId the id of the member
	 * @return Member object
	 * 
	 */
	public Member search(int memberId) {
		//System.out.println("MemList line: 47 MemberList: memberId = " + memberId);
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			//System.out.println("MemList line: 50 memberId = " + memberId + ", this.memberId = " + member.getId());
			if (member.getId() == memberId) {
				return member;
			}
		}
		return null;
	}

	/**
	 * Inserts a Member object into the collection
	 * 
	 * @param Member object the member to be inserted
	 * @return true iff the member could be inserted. Currently always true
	 */
	public boolean addMember(Member member) {
		//System.out.println("Line 50, Memberist.addMember(): memberId = " + member.getId() + ": memberName = ");
		return members.add(member);
	}
	
	/**
	 * remove a Member object from collection
	 * @param memberId
	 * @return true if Member is successfully removed
	 */
	public boolean removeMember(int memberId) {
		//System.out.println("Line 57, Memberist.addMember(): memberId = " + memberId);
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			//System.out.println("Line 60 member.getId = " + member.getId() + ", memberId = " + memberId);
			if (member.getId() == memberId) {
				//System.out.println("Line 62");
				iterator.remove();
				//System.out.println("Line 63");
				return true;
			}
		}
		System.out.println("Line 68: false");
		return false;
	}

	/**
	 * iterator for operations
	 * 
	 * @returns Iterator<Member>
	 */
	public Iterator<Member> iterator() {
		return members.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return members.toString();
	}
}
