package data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Alumni;
import model.*;
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
	 */
	public List<Alumni> getAlumni(String column, String search) throws SQLException {
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
				String serInternships = rs.getString("internships");
				String serJobs= rs.getString("jobs");
				String serColleges = rs.getString("transferColleges");
				Alumni alumni = null;
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
	

	
/*
	*//**
	 * Returns all items that contain the search keyword in the name or
	 * TODO description. 
	 * @param name
	 * @return list of items that match.
	 * @throws SQLException
	 *//*
	public List<Item> getItems(String name) throws SQLException {
		List<Item> filterList = new ArrayList<Item>();
		if (mAlumniList == null) {
			getItems();
		}
		name = name.toLowerCase();
		for (Item item : mAlumniList) {
			if (item.getName().toLowerCase().contains(name)) {
				filterList.add(item);
			}
		}
		return filterList;
	}*/
/*
	*//**
	 * Retrieve the item with the given id or null if not found.
	 * @param id
	 * @return item
	 * @throws SQLException
	 *//*
	public Item getItem(String id) throws SQLException {
		if (mConnection == null) {
			DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from Item where id = " + id;

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String name = rs.getString("name");
				String desc = rs.getString("description");
				double price = rs.getDouble("price");
				String condition = rs.getString("condition");
				String category = rs.getString("category");
				return new Item(name, desc, price, condition, new ItemCategory(
						category));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}*/

	/**
	 * Adds a new item to the Item table. 
	 * @param item
	 * @return Returns "Added Item Successfully" or "Error adding item: " with the sql exception.
	 */
	public String addAlumni(Alumni al) {
		String sql = "insert into Alumni(`name`, degreeTrack, degreeLevel, year, term, gpa, uniEmail, persEmail, internships, jobs, transferColleges) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		if (mConnection == null) {
			try {
				DataConnection.getConnection();
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
	        ArrayList arr = new ArrayList();
	        arr.add(new Internship("","","","","",5,5));
	        preparedStatement.setObject(9, arr);
	        arr = new ArrayList();
	        arr.add(new Job("","","","","",5,true));
			preparedStatement.setObject(10, arr);
	        arr = new ArrayList();
	        arr.add(new TransferCollege("",5,"","",""));
			preparedStatement.setObject(11, arr);
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
	 * Modifies the data on an Item - Only description, price and condition can be modified.
	 * @param row
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	/*public String updateItem(Item item, String columnName, Object data) {
		
		String name = item.getName();
		int id = Integer.parseInt(item.getId());
		String sql = "update Item set `" + columnName
				+ "` = ?  where name= ? and id = ? ";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data); 
			else if (data instanceof Double)
				preparedStatement.setDouble(1, (Double) data);
			preparedStatement.setString(2, name); // for name = ?
			preparedStatement.setInt(3, id); // for id = ?
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating item: " + e.getMessage();
		}
		return "Updated Item Successfully";
	
	}*/
}
