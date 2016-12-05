package model;

import data.AlumniDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.AlumniDB;

/**
 * For holding, modifying, and accessing all alumni in the system.
 * @author Group 8
 * @version 12.03.2016
 */
public class AlumniCollection {

    /** The DB where the alumni are actually stored. */
    private static AlumniDB mAlumniDB;

    /**
     * Will update an Alumni. All fields but the id field can be modified.
     * @param theAlumni the alumni to be updated.
     * @param theColumn The field to be updated.
     * @param theData The new data to be put in the field.
     * @return boolean signifying success or failure.
     */
    public static boolean updateAlumni(Alumni theAlumni, DataTypes theColumn, Object theData) {

        String stringColumn = null;


        // Figure out what we're modifying and correct the columns and data so that they'll work in SQL
        switch(theColumn) {
        case NAME: stringColumn = "`name`"; break;
        case TRACK: stringColumn = "degreeTrack"; break;
        case LEVEL: stringColumn = "degreeLevel"; break;
        case YEAR: stringColumn = "`year`"; break;
        case TERM: stringColumn = "term"; break;
        case GPA: stringColumn = "gpa"; break;
        case UNIEMAIL: stringColumn = "uniEmail"; break;
        case PERSEMAIL: stringColumn = "persEmail"; break;
        case INTNSHIP: stringColumn = "internships"; break;
        case JOB: stringColumn = "jobs"; break;
        case COLLEGES: stringColumn = "transferColleges"; break;
        default: return false;
        }

        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }

        return mAlumniDB.updateAlumni(theAlumni.getMyID(), stringColumn, theData);

    }

    /**
     * Searches for Alumni containing the name or part of the name given.
     * @param name
     * @return
     */
    public static List<Alumni> searchName(final String name) {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        ArrayList<Alumni> cList = new ArrayList<Alumni>();
        List<Alumni> filterList = new ArrayList<Alumni>();  
        cList = (ArrayList<Alumni>) mAlumniDB.getAllAlumni();
        String lwerCase = name.toLowerCase();
        for (Alumni a : cList) { 
            if (a.getMyName().toLowerCase().contains(lwerCase)) {
                filterList.add(a);
            }
        }

        return filterList;
    }

    /**
     * Will search for an Alumni with the matching ID.
     * @param theID
     * @return
     */
    public static Alumni searchID(int theID) {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        ArrayList<Alumni> cList = (ArrayList<Alumni>) mAlumniDB.getAllAlumni();
        for (Alumni a : cList) { 
            if (a.getMyID() == theID) {
                return a;
            }
        }

        return null;
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
     * This will return a list of all Alumni.
     * @return list of Alumni
     */
    public static List<Alumni> getAlumni() {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        return mAlumniDB.getAllAlumni();
    }

    /**
      This method will provide all available majors.
      @return
     */       
    public static Object[] getMajor(){
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        //        try {
        //            //return mAlumniDB.getMajor();
        //        } catch (SQLException e) {
        //            e.printStackTrace();
        //        }
        return null;
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
    /**
     * 
     * @return array of degree levels.
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
}
