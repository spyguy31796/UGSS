package model;
import java.util.List;

/**
 * Alumni object to store data on a single alumni.
 * 
 * @author GROUP8
 * @version 12/3/2016
 *
 */
public class Alumni {
    /**
     * Alumni Name.
     */
    private String myName;
    /**
     * Alumni ID.
     */
    private int myID;
    /**
     * Alumni DegreeTrack.
     */
    private String myDegreeTrack;
    /**
     * Alumni Degree Level.
     */
    private String myDegreeLevel;
    /**
     * Alumni Graduated Year.
     */
    private String myYear;
    /**
     * Alumni Graduated Term.
     */
    private String myTerm;
    /**
     * Alumni GPA.
     */
    private double myCurrentGPA;
    /**
     * Alumni University Email.
     */
    private String myUniEmail;
    /**
     * Alumni Personal Email.
     */
    private String myPersEmail;
    /**
     * Alumni Internships.
     */
    private List<Internship> myInternships;
    /**
     * Alumni Jobs.
     */
    private List<Job> myJobs;
    /**
     * Alumni Transfer Colleges.
     */
    private List<TransferCollege> myTransferColleges;
    /**
     * 
     * @param theName
     * @param theID
     * @param theDegreeTrack
     * @param theDegreeLevel
     * @param theYear
     * @param theTerm
     * @param theCurrentGPA
     * @param theUniEmail
     * @param thePersEmail
     */
    public Alumni(final String theName, final int theID, final String theDegreeTrack, 
            final String theDegreeLevel, final String theYear, final String theTerm,
            final double theCurrentGPA, final String theUniEmail, final String thePersEmail) {
        myName = theName;
        myID = theID;
        myDegreeTrack = theDegreeTrack;
        myDegreeLevel = theDegreeLevel;
        myYear = theYear;
        myTerm = theTerm;
        myCurrentGPA = theCurrentGPA;
        myUniEmail = theUniEmail;
        myPersEmail = thePersEmail;
    }
    /**
     * 
     * @param theName
     * @param theID
     * @param theDegreeTrack
     * @param theDegreeLevel
     * @param theYear
     * @param theTerm
     * @param theCurrentGPA
     * @param theUniEmail
     * @param thePersEmail
     * @param theInternships
     * @param theJobs
     * @param theTransferColleges
     */
    public Alumni(final String theName, final int theID, final String theDegreeTrack, 
            final String theDegreeLevel, final String theYear, final String theTerm,
            final double theCurrentGPA, final String theUniEmail, final String thePersEmail, 
            final List<Internship> theInternships, final List<Job> theJobs, 
            final List<TransferCollege> theTransferColleges) {
        this(theName, theID, theDegreeTrack, theDegreeLevel, theYear,
                theTerm, theCurrentGPA, theUniEmail, thePersEmail);
        
    }
    /**
     * Constructor for report GUI that take unique parameters.
     * @param theName name 
     * @param theId    ID  
     * @param theTrack  degreeTrack
     * @param theLevel     degree Level
     */
    public Alumni(final String theName, final int theId, final String theTrack, 
            final String theLevel) {
        myName = theName;
        myID = theId;
        myDegreeTrack = theTrack;
        myDegreeLevel = theLevel;
    }
    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }
    /**
     * @return the myID
     */
    public int getMyID() {
        return myID;
    }
    /**
     * @return the myDegreeTrack
     */
    public String getMyDegreeTrack() {
        return myDegreeTrack;
    }
    /**
     * @return the myDegreeLevel
     */
    public String getMyDegreeLevel() {
        return myDegreeLevel;
    }
    /**
     * @return the myYear
     */
    public String getMyYear() {
        return myYear;
    }
    /**
     * @return the myTerm
     */
    public String getMyTerm() {
        return myTerm;
    }
    /**
     * @return the myCurrentGPA
     */
    public double getMyCurrentGPA() {
        return myCurrentGPA;
    }
    /**
     * @return the myUniEmail
     */
    public String getMyUniEmail() {
        return myUniEmail;
    }
    /**
     * @return the myPersEmail
     */
    public String getMyPersEmail() {
        return myPersEmail;
    }
    /**
     * @return the myInternships
     */
    public List<Internship> getMyInternships() {
        return myInternships;
    }
    /**
     * @return the myJobs
     */
    public List<Job> getMyJobs() {
        return myJobs;
    }
    /**
     * @return the myTransferColleges
     */
    public List<TransferCollege> getMyTransferColleges() {
        return myTransferColleges;
    }

}
