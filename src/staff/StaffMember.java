package staff;

public class StaffMember implements Comparable{
	private String id;
	private String title;
	private String name;
	private Division div;

	public StaffMember(String name, Division div) {
		this.name = name;
		this.div = div;
		
	}
	
	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}
	
	public String getId() {
		return id;
	}
	
	public Division getDivision() {
		return div;
	}

//	@Override
//	public int compareTo(StaffMember sm) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
