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
	public int compareTo(Object o) {
		if(this.getClass() == o.getClass()){
			return 1;
		}
		return 0;
	}

}
