
public class Internship extends Employment {

	private double wage;
	
	private int duration;
	
	public Internship(String theCompany, String thePosition, String theSkillsReq, String theDescription, String theMiscComments, double theWage, int theDuration) {
		super(theCompany, thePosition, theSkillsReq, theDescription, theMiscComments);
		wage = theWage;
		duration = theDuration;
	}
}
