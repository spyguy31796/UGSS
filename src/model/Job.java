package model;
/**
 * Holds data regarding a job.
 * @author Dema
 *@version 11.15.2016
 */
public class Job extends Employment {

    /** Salary in dollars. */
    private double mySalary;

    /** Status of job (1 for currently working, 0 for no longer working). */
    private boolean myActive;

    /**
     * Constructs the Job object.
     * @param theCompany The company that the internship took place at.
     * @param thePosition The position title of internship.
     * @param theSkillsReq The skills needed during this internship.
     * @param theDescription A description of what the job entailed.
     * @param theMiscComments Any miscellaneous comments.
     * @param theSalary The yearly pay for this job.
     * @param theActive Current job status.
     */
    public Job(final String theCompany, final String thePosition, final String theSkillsReq, 
            final String theDescription, final String theMiscComments, 
            final double theSalary, final boolean theActive) {
        super(theCompany, thePosition, theSkillsReq, theDescription, theMiscComments);
        mySalary = theSalary;
        myActive = theActive;
    }

    /**
     * @return mySalary.
     */
    public double getSalary() {
        return mySalary;
    }

    /**
     * Changes salary to new salary.
     * @param theSalary the new salary.
     */
    public void setSalary(final double theSalary) {
        mySalary = theSalary;
    }

    /**
     * @return myActive.
     */
    public boolean isActive() {
        return myActive;
    }

    /**
     * Changes the status of the job.
     * @param theActive the new status.
     */
    public void setActive(final boolean theActive) {
        myActive = theActive;
    }
}
