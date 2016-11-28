package model;
/**
 * Holds data regarding an internship. 
 * @author Dema
 *@version 11.15.2016
 */
class Internship extends Employment {

    /** Hourly pay rate (dollars). */
    private double myWage;
    
    /** Duration of internship (months). */
    private int myDuration;
    
    /**
     * Constructs the Internship object.
     * @param theCompany The company that the internship took place at.
     * @param thePosition The position title of internship.
     * @param theSkillsReq The skills needed during this internship.
     * @param theDescription A description of what the job entailed.
     * @param theMiscComments Any miscellaneous comments.
     * @param theWage Hourly wage in dollars.
     * @param theDuration Length of internship in months.
     */
    Internship(final String theCompany, final String thePosition, final String theSkillsReq, 
            final String theDescription, final String theMiscComments,
            final double theWage, final int theDuration) {
        super(theCompany, thePosition, theSkillsReq, theDescription, theMiscComments);
        myWage = theWage;
        myDuration = theDuration;
    }

    /**
     * @return the wage.
     */
    public double getMyWage() {
        return myWage;
    }

    /**
     * @param theWage changes the wage.
     */
    public void setMyWage(final double theWage) {
        myWage = theWage;
    }

    /**
     * @return the duration.
     */
    public int getMyDuration() {
        return myDuration;
    }

    /**
     * @param theDuration changes the duration.
     */
    public void setMyDuration(final int theDuration) {
        myDuration = theDuration;
    }
}
