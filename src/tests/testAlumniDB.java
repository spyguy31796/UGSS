package tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import data.AlumniDB;

/**
 * Tests the Alumni Database.
 * @author GROUP8
 * @version 12/6/2016
 *
 */
public class testAlumniDB {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testGetAlumni() throws SQLException, ClassNotFoundException, IOException {
        AlumniDB db = new AlumniDB();
        assertNotNull(db.getAllAlumni());
    }

}
