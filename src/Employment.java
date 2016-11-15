
public abstract class Employment {
	
	private String company;
	private String position;
	private String skillsReq;
	private String description;
	private String miscComments;
	
	protected Employment(String theCompany, String thePosition, String theSkillsReq, String theDescription, String theMiscComments) {		
		company = theCompany;
		position = thePosition;
		skillsReq = theSkillsReq;
		description = theDescription;
		miscComments = theMiscComments;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String theCompany) {
		company = theCompany;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String thePosition) {
		position = thePosition;
	}

	public String getSkillsReq() {
		return skillsReq;
	}

	public void setSkillsReq(String theSkillsReq) {
		skillsReq = theSkillsReq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String theDescription) {
		description = theDescription;
	}

	public String getMiscComments() {
		return miscComments;
	}

	public void setMiscComments(String theMiscComments) {
		miscComments = theMiscComments;
	}
}
