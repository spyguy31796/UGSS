package data;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Alumni;
import model.Internship;
import model.Job;
import model.TransferCollege;

/**
 * This class contains methods to access Alumni table data.
 * @author GROUP8
 * @version 12/6/2016
 *
 */
    
public class AlumniDB {
    /**
     * String variable to use throughout the class.
     */
    private static final String ALL = "All";
    /**
     * Connection object to access database.
     */
    private Connection myConnection;
    /**
     * Retrieve all Alumni that satisfy report conditions. 
     * @param theDegreeLevel degree Level.
     * @param theDegreeTrack degree Track.
     * @throws SQLException DB exception
     * @return alumni that match
     */
    public List<Alumni> getReportAlumni(final String theDegreeLevel,
            final String theDegreeTrack) throws SQLException {
        final List<Alumni> filterList = new ArrayList<Alumni>();
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        String query;
        Statement stmt = null;
        if (ALL.equals(theDegreeLevel) && ALL.equals(theDegreeTrack)) {
            query = "select * " + "from Alumni";
        } else if (ALL.equals(theDegreeLevel) && !ALL.equals(theDegreeTrack)) {
            query = "Select * from Alumni where degreeTrack = \"" + theDegreeTrack + "\"";
        } else if (!ALL.equals(theDegreeLevel) && ALL.equals(theDegreeTrack)) {
            query = "Select * from Alumni where degreeLevel = \"" + theDegreeLevel + "\"";
        } else {
            query = "select * from Alumni where degreeLevel = \"" + theDegreeLevel 
                    + "\"  and degreeTrack = \"" + theDegreeTrack + "\"";
        }     
        try {
            stmt = myConnection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                final String name = rs.getString("name");
                final int id = rs.getInt("id");
                final String track = rs.getString("degreeTrack");
                final String level = rs.getString("degreeLevel");
                final Alumni temp = new Alumni(name, id, track, level);
                filterList.add(temp);
            }
        } catch (final SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        if (filterList.size() == 0) {
            JOptionPane.showMessageDialog(null, "No Data Found");            
        }
        return filterList;
    }
    
    
    /**
     * Retrieves all Alumni from DataBase.
     * @return list of Alumni
     * @throws SQLException
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("unchecked")
    public List<Alumni> getAllAlumni() {
        if (myConnection == null) {
            try {
                myConnection = DataConnection.getConnection();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        Statement stmt = null;
        final String query = "select * " + "from Alumni";

        final List<Alumni> mAlumniList = new ArrayList<Alumni>();
        try {
            stmt = myConnection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final String track = rs.getString("degreeTrack");
                final String level = rs.getString("degreeLevel");
                final String year = rs.getString("year");
                final String term = rs.getString("term");
                final Double gpa = rs.getDouble("gpa");
                final String uniEmail = rs.getString("uniEmail");
                final String persEmail = rs.getString("persEmail");
                final byte[] serInternships = (byte[]) rs.getObject("internships");
                final byte[] serJobs = (byte[]) rs.getObject("jobs");
                final byte[] serColleges = (byte[]) rs.getObject("transferColleges");
                Alumni alumni = null;

                // Cast internships, jobs, and colleges to lists
                // Create the Alumni to return
                // Add alumni to list
                List<Internship> internships = new ArrayList<Internship>();
                List<Job> jobs = new ArrayList<Job>();
                List<TransferCollege> colleges = new ArrayList<TransferCollege>();

                ByteArrayInputStream baip;
                ObjectInputStream ois;
                if (serInternships != null) {
                    baip = new ByteArrayInputStream(serInternships);
                    ois = new ObjectInputStream(baip);
                    internships = (List<Internship>) ois.readObject();             

                    ois.close();
                    baip.close();
                }              
                if (serJobs != null) {
                    baip = new ByteArrayInputStream(serJobs);
                    ois = new ObjectInputStream(baip);
                    jobs = (ArrayList<Job>) ois.readObject();

                    ois.close();
                    baip.close();
                }             
                if (serColleges != null) {
                    baip = new ByteArrayInputStream(serColleges);
                    ois = new ObjectInputStream(baip);
                    colleges = (List<TransferCollege>) ois.readObject();

                    ois.close();
                    baip.close();
                }
               
                alumni = new Alumni(name, track, level,
                        year, term, gpa, uniEmail, persEmail,
                        internships, jobs, colleges);
                alumni.setMyID(id);
                
                mAlumniList.add(alumni);                
            }
            stmt.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return mAlumniList;
    }

    /**
     * Modifies the data in an Alumni.
     * @param theID The ID of the Alumni to be modified.
     * @param theColumnName The column to be updated.
     * @param theData the Updated Data.
     * @return Returns boolean signifying success or failure.
     */
    public boolean updateAlumni(final int theID, 
            final String theColumnName, final Object theData) {
        
        final int id = theID;
        final String sql = "update Alumni set " + theColumnName
                + " = ?  where id = ? ";
        // For debugging - System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConnection.prepareStatement(sql);
            if (theData instanceof String) {
                preparedStatement.setString(1,  (String) theData); 
            } else if (theData instanceof List) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(theData);
                final byte[] itemAsBytes = baos.toByteArray();
                final ByteArrayInputStream bais = new 
                        ByteArrayInputStream(itemAsBytes);
                preparedStatement.setBinaryStream(1, bais, itemAsBytes.length);
            } else {
                return false;
            }
            preparedStatement.setInt(2, id); // for id = ?
            preparedStatement.executeUpdate();
            return true;
        } catch (final SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Allows an Alumni to be added.
     * @param theAl the Alumni object to be added.
     * @return String representing failure or success.
     * @throws IOException if there is a SQL error.
     */
    public String addAlumni(final Alumni theAl) throws IOException {
        final String sql = "insert into Alumni(`name`, degreeTrack, degreeLevel,"
                + " `year`, term, gpa, uniEmail, persEmail, internships,"
                + " jobs, transferColleges) values "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        if (myConnection == null) {
            try {
                myConnection = DataConnection.getConnection();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setString(1, theAl.getMyName());
            preparedStatement.setString(2, theAl.getMyDegreeTrack());
            preparedStatement.setString(3, theAl.getMyDegreeLevel());
            preparedStatement.setString(4, theAl.getMyYear());
            preparedStatement.setString(5, theAl.getMyTerm());
            preparedStatement.setDouble(6, theAl.getMyCurrentGPA());
            preparedStatement.setString(7, theAl.getMyUniEmail());
            preparedStatement.setString(8, theAl.getMyPersEmail());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(theAl.getMyInternships());
            final byte[] internshipAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new 
                    ByteArrayInputStream(internshipAsBytes);
            preparedStatement.setBinaryStream(9, bais, internshipAsBytes.length);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(theAl.getMyJobs());
            final byte[] jobAsBytes = baos.toByteArray();
            bais = new 
                    ByteArrayInputStream(jobAsBytes);
            preparedStatement.setBinaryStream(10, bais, jobAsBytes.length);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(theAl.getMyTransferColleges());
            final byte[] collegeAsBytes = baos.toByteArray();
            bais = new 
                    ByteArrayInputStream(collegeAsBytes);
            preparedStatement.setBinaryStream(11, bais, collegeAsBytes.length);
            //preparedStatement.setObject(9, al.getMyInternships());
            //preparedStatement.setObject(10, al.getMyJobs());
            //preparedStatement.setObject(11, al.getMyTransferColleges());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            return "Error adding alumni: " + e.getMessage();
        }
        return "Added Alumni Successfully";
    }
    
    /**
     * Retrieves all Distinct Degree Level in database.
     * @return list of Degree Level
     * @throws SQLException throws exception.
     */
    public Object[] getDegreeLevel() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        final String query = "select distinct degreeLevel " + "from Alumni ";
        final List<String> list = new ArrayList<String>();
        try {
            stmt = myConnection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                final String major = rs.getString("degreeLevel");
                list.add(major);
            }
        } catch (final SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        list.add("All");
        return list.toArray();
    }
    
    /**
     * Retrieves all Distinct Degree Track in database.
     * @return list of Degree Track
     * @throws SQLException throws DB exception
     */
    public Object[] getDegreeTrack() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        final String query = "select distinct degreeTrack " + "from Alumni ";
        final List<String> list = new ArrayList<String>();
        try {
            stmt = myConnection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                final String major = rs.getString("degreeTrack");
                list.add(major);
            }
        } catch (final SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        list.add("All");
        return list.toArray();
    }
}
