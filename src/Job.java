
public class Job extends Employment {
	
	private double salary;
	
	private boolean active;
	
	public Job(String theCompany, String thePosition, String theSkillsReq, String theDescription, String theMiscComments, double theSalary, boolean theActive) {
		super(theCompany, thePosition, theSkillsReq, theDescription, theMiscComments);
		salary = theSalary;
		active = theActive;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double theSalary) {
		salary = theSalary;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean theActive) {
		active = theActive;
	}
}
