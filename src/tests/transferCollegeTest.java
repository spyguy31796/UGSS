package tests;

import org.junit.Before;
import org.junit.Test;

import model.TransferCollege;

public class transferCollegeTest {
    
    @Before
    public void SetUp(){
    }
    
    @Test
    public void testConstructor(){
        new TransferCollege("SSC",3.8,"CSS","2012","Spring");
    }
}
