package model;
import java.io.Serializable;

/**
 * TrasferCollege class handle transfer colleges. 
 * @author Bui
 * @version 11/16
 */
public class TransferCollege implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2276135813180594849L;

    /** Variable for name. */
    private String myName;
    
    /** Variable for degree. */
    private String myDegree;
    
    /** Variable for term. */
    private String myTerm;
    
    /** Variable for year. */
    private String myYear;
    
    /** Variable for gpa. **/
    private double myGPA;
    /**
     * Constructor for Transfer College.
     * @param theName name of College
     * @param theGPA  Student's gpa
     * @param theDegree   degree track.
     * @param theYear     year completed
     * @param theTerm     term completed.
     */
    public TransferCollege(final String theName, final double theGPA, 
            final String theDegree, final String theYear, final String theTerm){
        myName = theName;
        myDegree = theDegree;
        myYear = theYear;
        myTerm = theTerm;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }
    /**
     * @return the myDegree
     */
    public String getMyDegree() {
        return myDegree;
    }
    /**
     * @return the myTerm
     */
    public String getMyTerm() {
        return myTerm;
    }
    /**
     * @return the myYear
     */
    public String getMyYear() {
        return myYear;
    }
    /**
     * @return the myGPA
     */
    public double getMyGPA() {
        return myGPA;
    }
    /**
     * @param myName the myName to set
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }
    /**
     * @param myDegree the myDegree to set
     */
    public void setMyDegree(String myDegree) {
        this.myDegree = myDegree;
    }
    /**
     * @param myTerm the myTerm to set
     */
    public void setMyTerm(String myTerm) {
        this.myTerm = myTerm;
    }
    /**
     * @param myYear the myYear to set
     */
    public void setMyYear(String myYear) {
        this.myYear = myYear;
    }
    /**
     * @param myGPA the myGPA to set
     */
    public void setMyGPA(double myGPA) {
        this.myGPA = myGPA;
    }
}
