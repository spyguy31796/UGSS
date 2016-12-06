package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
}
