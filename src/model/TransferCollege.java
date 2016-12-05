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
    private static final long serialVersionUID = -5402189238974342289L;

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
        myGPA = theGPA;
        myDegree = theDegree;
        myYear = theYear;
        myTerm = theTerm;
    }
    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }
    /**
     * @param myName the myName to set
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }
    /**
     * @return the myDegree
     */
    public String getMyDegree() {
        return myDegree;
    }
    /**
     * @param myDegree the myDegree to set
     */
    public void setMyDegree(String myDegree) {
        this.myDegree = myDegree;
    }
    /**
     * @return the myTerm
     */
    public String getMyTerm() {
        return myTerm;
    }
    /**
     * @param myTerm the myTerm to set
     */
    public void setMyTerm(String myTerm) {
        this.myTerm = myTerm;
    }
    /**
     * @return the myYear
     */
    public String getMyYear() {
        return myYear;
    }
    /**
     * @param myYear the myYear to set
     */
    public void setMyYear(String myYear) {
        this.myYear = myYear;
    }
    /**
     * @return the myGPA
     */
    public double getMyGPA() {
        return myGPA;
    }
    /**
     * @param myGPA the myGPA to set
     */
    public void setMyGPA(double myGPA) {
        this.myGPA = myGPA;
    }
    public String toString() {
        return myName;
    }
    
}
