package model;
import java.io.Serializable;

/**
 * TrasferCollege class handle transfer colleges. 
 * @author Bui
 * @version 11/16
 */
public class TransferCollege implements Serializable {
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
}
