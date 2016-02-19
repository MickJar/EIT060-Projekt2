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
<<<<<<< HEAD
	
	public String getId() {
		return id;
=======

	@Override
	public int compareTo(Object o) {
		if(this.getClass() == o.getClass()){
			return 1;
		}
		return 0;
>>>>>>> origin/master
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
