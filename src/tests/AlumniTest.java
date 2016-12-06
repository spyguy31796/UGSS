package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Alumni;
import model.Internship;
import model.Job;
import model.TransferCollege;

/**
 * Test for Alumni Class
 * @author GROUP8
 * @version 12/6/2016
 *
 */
public class AlumniTest {
    Alumni theTestAlumni;
    @Before
    public void setUp() throws Exception {
        theTestAlumni = new Alumni("ARW", "CSS", "BS", "2017", "Spring", 3.7, "arw96@uw.edu", "junk@live.com", null, null, null);
    }
    @Test
    public void testConstructors(){
        Alumni newAlumni = new Alumni("Junk","Junk","Junk","Junk","Junk",3,"Junk","Junk",null,null,null);
        Alumni newAlumni2 = new Alumni(null,null,null,"Junk","Junk",3,"Junk","Junk",null,null,null);
        Alumni newAlumni3 = new Alumni("Junk",0, "Junk","Junk");
        Alumni newAlumni4 = new Alumni(null,3, null,null);
        assertNotNull(newAlumni);
        assertNotNull(newAlumni2);
        assertNotNull(newAlumni3);
        assertNotNull(newAlumni4);
    }

    @Test
    public void testMyName() {
        assertEquals(theTestAlumni.getMyName(),"ARW");
        theTestAlumni.setMyName("Alec Walsh");
        assertEquals(theTestAlumni.getMyName(),"Alec Walsh");
    }
    @Test
    public void testMyID(){
        assertEquals(theTestAlumni.getMyID(),0);
        theTestAlumni.setMyID(9001);
        assertEquals(theTestAlumni.getMyID(),9001);
    }
    @Test
    public void testMyDegreeTrack(){
        assertEquals(theTestAlumni.getMyDegreeTrack(),"CSS");
        theTestAlumni.setMyDegreeTrack("CES");
        assertEquals(theTestAlumni.getMyDegreeTrack(),"CES");
    }
    @Test
    public void testMyDegreeLevel(){
        assertEquals(theTestAlumni.getMyDegreeLevel(),"BS");
        theTestAlumni.setMyDegreeLevel("Ph.D");
        assertEquals(theTestAlumni.getMyDegreeLevel(),"Ph.D");
    }
    @Test
    public void testMyYear(){
        assertEquals(theTestAlumni.getMyYear(),"2017");
        theTestAlumni.setMyYear("2020");
        assertEquals(theTestAlumni.getMyYear(),"2020");
    }
    @Test
    public void testMyTerm(){
        assertEquals(theTestAlumni.getMyTerm(),"Spring");
        theTestAlumni.setMyTerm("Winter");
        assertEquals(theTestAlumni.getMyTerm(),"Winter");
    }
    @Test
    public void testMyGPA(){
        assertEquals(theTestAlumni.getMyCurrentGPA(),3.7,.01);
        theTestAlumni.setMyCurrentGPA(4.0);
        assertEquals(theTestAlumni.getMyCurrentGPA(),4.0,.01);
    }
    @Test
    public void testMyUniEmail(){
        assertEquals(theTestAlumni.getMyUniEmail(),"arw96@uw.edu");
        theTestAlumni.setMyUniEmail("arw@uw.edu");
        assertEquals(theTestAlumni.getMyUniEmail(),"arw@uw.edu");
    }
    @Test
    public void testMyPersEmail(){
        assertEquals(theTestAlumni.getMyPersEmail(),"junk@live.com");
        theTestAlumni.setMyPersEmail("junk@gmail.com");
        assertEquals(theTestAlumni.getMyPersEmail(),"junk@gmail.com");
    }
    @Test
    public void testMyTransferColleges(){
        assertEquals(theTestAlumni.getMyTransferColleges(),null);
        ArrayList list = new ArrayList();
        list.add(new TransferCollege("TheSchool", 4, "AS", "2014", "Spring"));
        theTestAlumni.setMyTransferColleges(list);
        assertEquals(theTestAlumni.getMyTransferColleges().get(0).toString(),"TheSchool");
    }
    @Test
    public void testMyJobs(){
        assertEquals(theTestAlumni.getMyJobs(),null);
        ArrayList list = new ArrayList();
        list.add(new Job("TheWork","Manager","None", "None", "None", 13,true));
        theTestAlumni.setMyJobs(list);
        assertEquals(theTestAlumni.getMyJobs().get(0).toString(),"TheWork");
    }
    @Test
    public void testMyInternships(){
        assertEquals(theTestAlumni.getMyInternships(),null);
        ArrayList list = new ArrayList();
        list.add(new Internship("TheInternship","Manager","None", "None", "None", 13,1));
        theTestAlumni.setMyInternships(list);
        assertEquals(theTestAlumni.getMyInternships().get(0).toString(),"TheInternship");
    }
}
