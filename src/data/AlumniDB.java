package data;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Alumni;
import model.Internship;
import model.Job;
import model.TransferCollege;

/**
 * This class contains methods to access Alumni table data.
 * @author Group 8
 *
 */
public class AlumniDB {

    private Connection mConnection;
    private List<Alumni> mAlumniList;

    /**
     * Retrieves all Alumni that match the search term in the given column from the Alumni table.
     * 
     * @return list of Alumni
     * @throws SQLException
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public List<Alumni> getAlumni(String column, String search) throws SQLException, IOException, ClassNotFoundException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Alumni where " + column + " = " + search;

        mAlumniList = new ArrayList<Alumni>();
        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String track = rs.getString("degreeTrack");
                String level = rs.getString("degreeLevel");
                String year = rs.getString("year");
                String term = rs.getString("term");
                Double gpa = rs.getDouble("gpa");
                String uniEmail = rs.getString("uniEmail");
                String persEmail = rs.getString("persEmail");
                byte[] serInternships = (byte[])rs.getObject("internships");
                byte[] serJobs= (byte[])rs.getObject("jobs");
                byte[] serColleges = (byte[])rs.getObject("transferColleges");
                Alumni alumni = null;
                
                // Cast internships, jobs, and colleges to lists
                // Create the Alumni to return
                // Add alumni to list
                
                ByteArrayInputStream baip = new ByteArrayInputStream(serInternships);
                ObjectInputStream ois = new ObjectInputStream(baip);
                List<Internship> internships = (List<Internship>) ois.readObject();
                
                System.out.println(internships.toString());
                
                ois.close();
                baip.close();
                
                baip = new ByteArrayInputStream(serJobs);
                ois = new ObjectInputStream(baip);
                List<Job> jobs = (List<Job>) ois.readObject();
                
                ois.close();
                baip.close();
            
                baip = new ByteArrayInputStream(serColleges);
                ois = new ObjectInputStream(baip);
                List<TransferCollege> college = (List<TransferCollege>) ois.readObject();
                
                ois.close();
                baip.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return mAlumniList;
    }

    /**
     * Retrieves all Alumni from DataBase.
     * @return list of Alumni
     * @throws SQLException
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public List<Alumni> getAllAlumni() throws SQLException, IOException, ClassNotFoundException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Alumni";

        mAlumniList = new ArrayList<Alumni>();
        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String track = rs.getString("degreeTrack");
                String level = rs.getString("degreeLevel");
                String year = rs.getString("year");
                String term = rs.getString("term");
                Double gpa = rs.getDouble("gpa");
                String uniEmail = rs.getString("uniEmail");
                String persEmail = rs.getString("persEmail");
                byte[] serInternships = (byte[])rs.getObject("internships");
                byte[] serJobs= (byte[])rs.getObject("jobs");
                byte[] serColleges = (byte[])rs.getObject("transferColleges");
                Alumni alumni = null;
                
                // Cast internships, jobs, and colleges to lists
                // Create the Alumni to return
                // Add alumni to list
                
                ByteArrayInputStream baip = new ByteArrayInputStream(serInternships);
                ObjectInputStream ois = new ObjectInputStream(baip);
                List<Internship> internships = (List<Internship>) ois.readObject();
                
                System.out.println(internships.toString());
                
                ois.close();
                baip.close();
                
                baip = new ByteArrayInputStream(serJobs);
                ois = new ObjectInputStream(baip);
                List<Job> jobs = (List<Job>) ois.readObject();
                
                ois.close();
                baip.close();
            
                baip = new ByteArrayInputStream(serColleges);
                ois = new ObjectInputStream(baip);
                List<TransferCollege> college = (List<TransferCollege>) ois.readObject();
                
                ois.close();
                baip.close();
                
                Alumni al = new Alumni();
                // TO-DO
                // Cast internships, jobs, and colleges to lists
                // Create the Alumni to return
                // Add alumni to list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return mAlumniList;
    }

    /**
     * Modifies the data on an Item - Only description, price and condition can be modified.
     * @param row
     * @param columnName
     * @param data
     * @return Returns a message with success or failure.
     */
    public String updateAlumni(Alumni alum, String columnName, Object data) {
        int id = alum.getMyID();
        String sql = "update Alumni set `" + columnName
                + "` = ?  where id = ? ";
        // For debugging - System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mConnection.prepareStatement(sql);
            preparedStatement.setString(1, (String) data); 
            preparedStatement.setInt(2, id); // for id = ?
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return "Error updating item: " + e.getMessage();
        }
        return "Updated Item Successfully";
    }
    
    /**
     * Allows an Alumni to be added.
     * @param al
     * @return
     * @throws IOException 
     */
    public String addAlumni(Alumni al) throws IOException {
        String sql = "insert into Alumni(`name`, degreeTrack, degreeLevel, `year`, term, gpa, uniEmail, persEmail, internships, jobs, transferColleges) values "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        if (mConnection == null) {
            try {
                mConnection = DataConnection.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mConnection.prepareStatement(sql);
            preparedStatement.setString(1, al.getMyName());
            preparedStatement.setString(2, al.getMyDegreeTrack());
            preparedStatement.setString(3, al.getMyDegreeLevel());
            preparedStatement.setString(4, al.getMyYear());
            preparedStatement.setString(5, al.getMyTerm());
            preparedStatement.setDouble(6, al.getMyCurrentGPA());
            preparedStatement.setString(7, al.getMyUniEmail());
            preparedStatement.setString(8, al.getMyPersEmail());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(al.getMyInternships());
            byte[] internshipAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new 
                    ByteArrayInputStream(internshipAsBytes);
            preparedStatement.setBinaryStream(9, bais, internshipAsBytes.length);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(al.getMyJobs());
            byte[] jobAsBytes = baos.toByteArray();
            bais = new 
                    ByteArrayInputStream(jobAsBytes);
            preparedStatement.setBinaryStream(10, bais, jobAsBytes.length);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(al.getMyTransferColleges());
            byte[] collegeAsBytes = baos.toByteArray();
            bais = new 
                    ByteArrayInputStream(collegeAsBytes);
            preparedStatement.setBinaryStream(11, bais, collegeAsBytes.length);
            //preparedStatement.setObject(9, al.getMyInternships());
            //preparedStatement.setObject(10, al.getMyJobs());
            //preparedStatement.setObject(11, al.getMyTransferColleges());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding item: " + e.getMessage();
        }
        return "Added Item Successfully";
    }
    
    /**
     * Retrieves all majors in the database.
     * @return list of majors
     */
    public Object[] getMajor() throws SQLException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Alumni where degreeTrack = " ;
        List<String> list = new ArrayList<String>();
        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String major = rs.getString("degreeTrack");
                list.add(major);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return list.toArray();
    }
}
