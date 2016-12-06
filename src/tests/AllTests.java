package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests.
 * @author GROUP8
 * @version 12/6/2016
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AlumniCollectionTests.class, AlumniTest.class, testAlumniDB.class, testInternship.class, testJob.class,
    transferCollegeTest.class})
public class AllTests {

}

