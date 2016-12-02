package model;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.AlumniDB;

public class AlumniCollection {

    private static AlumniDB mAlumniDB;
    
    public static List<Alumni> search(String category, String search){
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        ArrayList<Alumni> cList = new ArrayList<Alumni>();
        try {
            cList = (ArrayList<Alumni>) mAlumniDB.getAlumni(category, search);
        } catch (SQLException e) {
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
     * Add Alumni
     * @param theAlumni
     */
    public static boolean add(final Alumni theAlumni){
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
        }
        try {
            mAlumniDB.addAlumni(theAlumni);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean addcategory(String category){
        
        return false;
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
    @return
  */       
  public static Object[] getDegreeTrack() {
        if (mAlumniDB == null) {
            mAlumniDB = new AlumniDB();
            }
        try {
            return mAlumniDB.getDegreeTrack();
        } catch (SQLException e) {
          e.printStackTrace();
      }
      return null;
  }
}
