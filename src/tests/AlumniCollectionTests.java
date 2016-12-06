package tests;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Alumni;
>>>>>>> refs/remotes/origin/master
import model.AlumniCollection;

/**
 * Test class for Alumni Collection
 * @author Bui
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
        List<Alumni> alumni = AlumniCollection.searchName("Merlin");
        Alumni alum = null;
        if (alumni.size() != 0) {
            alum = alumni.get(0);
        }
        assertNotNull(alum);
        assertEquals("Merlin", alum.getMyName());
    }
    
    /** Tests whether ID is correctly gotten. */
    @Test
    public void testGetID() {
        Alumni alumni = AlumniCollection.searchID(6);
        assertNotNull(alumni);
        assertEquals("Merlin", alumni.getMyName());
        assertEquals(6, alumni.getMyID());
    }
    
    /** Tests whether returned list not not null. */
    @Test
    public void testGetAlumni() {
        List<Alumni> alumni = AlumniCollection.getAlumni();
        assertNotNull(alumni);
    }
    
    
    
}
