package staff;

import java.util.ArrayList;

public class Division {
	private ArrayList<User> members;
	private String divisionId;

	public Division(String id) {
		this.divisionId = id;
		members = new ArrayList<User>();
	}
	
	public void addMember(User member) {
		if (!members.contains(member)) {
			members.add(member);	
		}
	}
	
	public boolean containsMember(String id){
		for(User u: members){
			if(u.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<User> getMembers(){
		return members;
	}
	
	public void removeMember(User member) {
		if (members.contains(member)) {
			members.remove(member);
		} else {
			System.out.println(member.getTitle() + " " + member.getName() + " is not a member of division " + divisionId);
		}
	}
	public String toString(){
		return divisionId;
	}
	

}
