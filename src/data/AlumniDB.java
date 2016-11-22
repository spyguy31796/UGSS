package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to access Item and ItemCategory tables data.
 * @author mabraham
 *
 */
public class AlumniDB {

	private Connection mConnection;
	private List<Alumni> mAlumniList;

	/**
	 * Retrieves all items from the Item table.
	 * 
	 * @return list of items
	 * @throws SQLException
	 */
	public List<Item> getItems() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from Item ";

		mAlumniList = new ArrayList<Item>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String desc = rs.getString("description");
				double price = rs.getDouble("price");
				String condition = rs.getString("condition");
				String category = rs.getString("category");
				Item item = null;
				if (desc == null) {
					item = new Item(name, new ItemCategory(category));
					item.setId(id);
				} else {
					item = new Item(name, desc, price, condition,
							new ItemCategory(category));
					item.setId(id);
				}
				mAlumniList.add(item);
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
	 * Retrieves all categories from the ItemCategory table.
	 * 
	 * @return list of categories
	 * @throws SQLException
	 */
	public Object[] getItemCategories() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from ItemCategory ";

		List<String> list = new ArrayList<String>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String category = rs.getString("category");
				list.add(category);
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

	/**
	 * Returns all items that contain the search keyword in the name or
	 * TODO description. 
	 * @param name
	 * @return list of items that match.
	 * @throws SQLException
	 */
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
	}

	/**
	 * Retrieve the item with the given id or null if not found.
	 * @param id
	 * @return item
	 * @throws SQLException
	 */
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
	}

	/**
	 * Adds a new item to the Item table. 
	 * @param item
	 * @return Returns "Added Item Successfully" or "Error adding item: " with the sql exception.
	 */
	public String addItem(Item item) {
		String sql = "insert into Item(`name`, description, price, `condition`, category) values "
				+ "(?, ?, ?, ?, ?); ";

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
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getDescription());
			preparedStatement.setDouble(3, item.getPrice());
			preparedStatement.setString(4, item.getCondition());
			preparedStatement
					.setString(5, item.getItemCategory().getCategory());
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
	public String updateItem(Item item, String columnName, Object data) {
		
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
	
	}
}
