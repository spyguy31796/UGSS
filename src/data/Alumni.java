package data;
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
    //private List<Internship> myInternships; 
    //private List<Job> myJobs;
    //private List<TransferCollege> myTransferColleges;
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
     * TODO Complete this constructor.
     */
    /*public Alumni(final String theName, final int theID, final String theDegreeTrack, 
            final String theDegreeLevel, final String theYear, final String theTerm,
            final double theCurrentGPA, final String theUniEmail, final String thePersEmail, 
            final List<Internship> theInternships, final List<Job> theJobs, 
            final List<TransferCollege> theTransferColleges) {
        
    }*/
    

}
