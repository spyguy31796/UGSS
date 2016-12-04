package model;

import data.AlumniDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Collection which stores Alumni.
 * @author GROUP8
 * @version 12/3/2016
 *
 */
public final class AlumniCollection {
    /**
     * AlumniDB object to store and retrieve from database.
     */
    private static AlumniDB mAlumniDB;
    /**
     * Private Constructor for Utility Class.
     */
    private AlumniCollection() { };  
    /**
     * Finds all alumni with a certain value in a certain category.
     * @param theCategory the category to be searched.
     * @param theSearch desired value of the selected category.
     * @return a list of alumni meeting the search criteria.
     */
    public static List<Alumni> search(final String theCategory, final String theSearch) {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        ArrayList<Alumni> cList = new ArrayList<Alumni>();
        try {
            cList = (ArrayList<Alumni>) mAlumniDB.getAlumni(theCategory, theSearch);
        } catch (final SQLException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return cList;
    }
    
    /**
     * Return a list of Alumni for report with the matching conditions.
     * @param theLevel degree Level
     * @param theTrack degree Track
     * @return a list of items that match
     */
    public static List<Alumni> reportsearch(final String theLevel, 
            final String theTrack) {
        final List<Alumni> list = new ArrayList<Alumni>();
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            return mAlumniDB.getReportAlumni(theLevel, theTrack);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    /**
     * Add Alumni.
     * @param theAlumni the alumni object to add.
     * @return boolean representing failure or success of adding alumni.
     */
    public static boolean add(final Alumni theAlumni) {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            mAlumniDB.addAlumni(theAlumni);
            return true;
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * This will return a list of Alumni.
     * @return list of Alumni
     */
    public static List<Alumni> getAlumni() {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            return mAlumniDB.getAllAlumni();
        } catch (final SQLException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
      This method will provide all available majors.
      @return array of Degree Level.
    */       
    public static Object[] getDegreeLevel() {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            return mAlumniDB.getDegreeLevel();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
    This method will provide all available majors.
    @return an object array of all objects, returns null if an error occurs.
  */       
    public static Object[] getDegreeTrack() {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            return mAlumniDB.getDegreeTrack();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
