package model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.AlumniDB;

public class AlumniCollection {

    private static AlumniDB mAlumniDB;
    
    public static List<Alumni> search(String category, String search) {
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
     * Add Alumni
     * @param theAlumni
     */
    public static boolean add(final Alumni theAlumni){
        return true;
    }
}
