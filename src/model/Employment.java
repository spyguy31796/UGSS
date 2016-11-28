package model;
/**
 * Holds data regarding a place of employment. 
 * @author Dema
 *@version 11.15.2016
 */
public abstract class Employment {

    /** The employer name. */
    private String myCompany;

    /** The position title. */
    private String myPosition;

    /** Skills needed for this position. */
    private String mySkillsReq;

    /** Description of position. */
    private String myDescription;

    /** Any other comments. */
    private String myMiscComments;

    /**
     * Constructs the Employment object.
     * @param theCompany The company that the internship took place at.
     * @param thePosition The position title of internship.
     * @param theSkillsReq The skills needed during this internship.
     * @param theDescription A description of what the job entailed.
     * @param theMiscComments Any miscellaneous comments.
     */
    Employment(final String theCompany, final String thePosition, final String theSkillsReq, 
            final String theDescription, final String theMiscComments) {
        myCompany = theCompany;
        myPosition = thePosition;
        mySkillsReq = theSkillsReq;
        myDescription = theDescription;
        myMiscComments = theMiscComments;
    }

    /**
     * @return myCompany.
     */
    public String getMyCompany() {
        return myCompany;
    }

    /**
     * Changes myCompany.
     * @param theCompany new company that myCompany is changed to.
     */
    public void setMyCompany(final String theCompany) {
        myCompany = theCompany;
    }

    /**
     * @return myPosition.
     */
    public String getMyPosition() {
        return myPosition;
    }

    /**
     * Changes myPosition.
     * @param thePosition new position that myPosition is changed to.
     */
    public void setMyPosition(final String thePosition) {
        myPosition = thePosition;
    }

    /**
     * @return mySkillsReq.
     */
    public String getMySkillsReq() {
        return mySkillsReq;
    }

    /**
     *Changes mySkillsReq.
     * @param theSkillsReq new requirements for this position.
     */
    public void setMySkillsReq(final String theSkillsReq) {
        mySkillsReq = theSkillsReq;
    }

    /**
     * @return myDescription.
     */
    public String getMyDescription() {
        return myDescription;
    }

    /**
     * Changes myDescription.
     * @param theDescription new description for this position.
     */
    public void setMyDescription(final String theDescription) {
        myDescription = theDescription;
    }

    /**
     * @return myMiscComments.
     */
    public String getMyMiscComments() {
        return myMiscComments;
    }

    /**
     * Changes myMiscComments.
     * @param theMiscComments new miscellaneous comments for this position.
     */
    public void setMyMiscComments(final String theMiscComments) {
        myMiscComments = theMiscComments;
    }
}
