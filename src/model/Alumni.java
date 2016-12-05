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
        this(theName,theDegreeTrack,theDegreeLevel,theYear,theTerm,theCurrentGPA,theUniEmail,thePersEmail);
        myInternships = theInternships;
        myJobs = theJobs;
        myTransferColleges = theTransferColleges;
        
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
    /**
     * @param myName the myName to set
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }
    /**
     * @param myID the myID to set
     */
    public void setMyID(int myID) {
        this.myID = myID;
    }
    /**
     * @param myDegreeTrack the myDegreeTrack to set
     */
    public void setMyDegreeTrack(String myDegreeTrack) {
        this.myDegreeTrack = myDegreeTrack;
    }
    /**
     * @param myDegreeLevel the myDegreeLevel to set
     */
    public void setMyDegreeLevel(String myDegreeLevel) {
        this.myDegreeLevel = myDegreeLevel;
    }
    /**
     * @param myYear the myYear to set
     */
    public void setMyYear(String myYear) {
        this.myYear = myYear;
    }
    /**
     * @param myTerm the myTerm to set
     */
    public void setMyTerm(String myTerm) {
        this.myTerm = myTerm;
    }
    /**
     * @param myCurrentGPA the myCurrentGPA to set
     */
    public void setMyCurrentGPA(double myCurrentGPA) {
        this.myCurrentGPA = myCurrentGPA;
    }
    /**
     * @param myUniEmail the myUniEmail to set
     */
    public void setMyUniEmail(String myUniEmail) {
        this.myUniEmail = myUniEmail;
    }
    /**
     * @param myPersEmail the myPersEmail to set
     */
    public void setMyPersEmail(String myPersEmail) {
        this.myPersEmail = myPersEmail;
    }
    /**
     * @param myInternships the myInternships to set
     */
    public void setMyInternships(List<Internship> myInternships) {
        this.myInternships = myInternships;
    }
    /**
     * @param myJobs the myJobs to set
     */
    public void setMyJobs(List<Job> myJobs) {
        this.myJobs = myJobs;
    }
    /**
     * @param myTransferColleges the myTransferColleges to set
     */
    public void setMyTransferColleges(List<TransferCollege> myTransferColleges) {
        this.myTransferColleges = myTransferColleges;
    }

}
