package tests;
import data.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

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
