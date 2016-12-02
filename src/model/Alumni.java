package model;
import java.util.List;

public class Alumni {
    private String myName;
    private int myID;
    private String myDegreeTrack;
    private String myDegreeLevel;
    private String myYear;
    private String myTerm;
    private double myCurrentGPA;
    private String myUniEmail;
    private String myPersEmail;
    private List<Internship> myInternships; 
    private List<Job> myJobs;
    private List<TransferCollege> myTransferColleges;
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
    public Alumni(final String theName, final int theID, final String theDegreeTrack, 
            final String theDegreeLevel, final String theYear, final String theTerm,
            final double theCurrentGPA, final String theUniEmail, final String thePersEmail, 
            final List<Internship> theInternships, final List<Job> theJobs, 
            final List<TransferCollege> theTransferColleges) {
        this(theName,theID,theDegreeTrack,theDegreeLevel,theYear,theTerm,theCurrentGPA,theUniEmail,thePersEmail);
        
    }
    /**
     * Constructor for report GUI that take unique parameters
     * @param name
     * @param id
     * @param track
     * @param level
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
