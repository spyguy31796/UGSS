package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.TransferCollege;

public class transferCollegeTest {
    
    @Before
    public void SetUp(){
    }
    /**
     * Test Constructor.
     */
    @Test
    public void testConstructor(){
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        assertEquals("SSC", trans.getMyName());
        assertEquals(3.8, trans.getMyGPA(),0.000001);
        assertEquals("CSS", trans.getMyDegree());
        assertEquals("2012",trans.getMyYear());
        assertEquals("Spring",trans.getMyTerm());
    }
    /**
     * Tests constructor with null College name passed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullCollegeConstructor() {           
        TransferCollege trans = new TransferCollege(null, 3.8, "abc", "abc","abc");
    }
    /**
     * Tests constructor with null degree name passed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullDegreeConstructor() {           
        TransferCollege trans = new TransferCollege("abc", 3.8, null, "abc","abc");
    }
    /**
     * Tests constructor with null degree name passed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullYearConstructor() {           
        TransferCollege trans = new TransferCollege("abc", 3.8, "abc", null, "abc");
    }
    /**
     * Tests constructor with null term passed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullTermsConstructor() {           
        TransferCollege trans = new TransferCollege("abc", 3.8, "abc", "abc", null);
    }
    
   /**
    * Test toString method.
    */
    @Test
    public void testToString() {
        TransferCollege trans = new TransferCollege("abc", 3.8, "abc", "abc", "Spring");
        assertEquals("abc", trans.toString());
    }
    /**
     * Testing set Name.
     */
    @Test
    public void testSetName() {
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        trans.setMyName("temp");
        assertEquals("temp", trans.getMyName());
    }
    /**
     * Testing set GPA.
     */
    @Test
    public void testSetGPA() {
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        trans.setMyGPA(4.0);
        assertEquals(4.0, trans.getMyGPA(),0.000001);
    }
    /**
     * Testing set Degree.
     */
    @Test
    public void testSetDegree() {
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        trans.setMyDegree("CES");
        assertEquals("CES", trans.getMyDegree());
    }
    /**
     * Testing set Year.
     */
    @Test
    public void testSetYear() {
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        trans.setMyYear("2016");
        assertEquals("2016", trans.getMyYear());
    }
    /**
     * Testing set Term.
     */
    @Test
    public void testSetTerm () {
        TransferCollege trans = new TransferCollege("SSC",3.8,"CSS","2012","Spring");
        trans.setMyTerm("Summer");
        assertEquals("Summer", trans.getMyTerm());
    }
}
