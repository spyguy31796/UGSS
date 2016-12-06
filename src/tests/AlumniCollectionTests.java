package tests;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Alumni;
import model.AlumniCollection;

/**
 * Test class for Alumni Collection
 * @author GROUP8
 * @version 12/6/2016
 *
 */
public class AlumniCollectionTests {
    @Before
    public void SetUp(){
        
    }
    /**
     * Test reportSearch.
     */
    @Test
    public void testReportAlumni(){
        assertNotNull(AlumniCollection.reportsearch("All", "All"));
    }
    /**
     * Test get degreeTrack.
     */
    @Test
    public void testDegreeTrack(){
        assertNotNull(AlumniCollection.getDegreeTrack());
    }
    /**
     * Test get degreeLevel.
     */
    @Test
    public void testDegreeLevel(){
        assertNotNull(AlumniCollection.getDegreeLevel());
    }
    // What about add methods?
    // Our ability to test is limited, right? Ex. GetAlumni - size of list will vary, just test for null?
    
    /** Tests whether name is correctly gotten. */
    @Test
    public void testGetName() {
        List<Alumni> alumni = AlumniCollection.searchName("Alec Walsh");
        Alumni alum = null;
        if (alumni.size() != 0) {
            alum = alumni.get(0);
        }
        assertNotNull(alum);
        assertEquals("Alec Walsh", alum.getMyName());
    }
    
    /** Tests whether ID is correctly gotten. */
    @Test
    public void testGetID() {
        Alumni alumni = AlumniCollection.searchID(1);
        assertNotNull(alumni);
        assertEquals("Alec Walsh", alumni.getMyName());
        assertEquals(1, alumni.getMyID());
    }
    
    /** Tests whether returned list not not null. */
    @Test
    public void testGetAlumni() {
        List<Alumni> alumni = AlumniCollection.getAlumni();
        assertNotNull(alumni);
    }
    
    
    
}
