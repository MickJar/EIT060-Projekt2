package staff;

public class StaffMember implements Comparable{
	private int id;
	private String title;

	public StaffMember(String title) {
		this.title = title;
		
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}