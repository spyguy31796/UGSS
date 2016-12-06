package data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class has the information to connect to the cssgate server. 
 * to customize for your own project.
 * @author mabraham/GROUP8
 * @version 12/6/2016
 *
 */
public final class DataConnection {

    /**
     * The unetid for SQL database login.
     */
    private static String userName = "_360team8"; 
    /**
    * The mysqlpassword for SQL database login.
    */
    private static String password = "dasGiwum";
    /**
     * Server address for SQL database.
     */
    private static String serverName = "cssgate.insttech.washington.edu";
    /**
    * Connection object.
    */
    private static Connection sConnection;
    /**
     * Satisfies Checkstyle.
     */
    private DataConnection() { }
    /**Creates once instance of the connection to be reused in the different places in the
     * system. 
     * @throws SQLException SQL Exception.
     */
    private static void createConnection() throws SQLException {
        final Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        sConnection =  DriverManager.
                getConnection("jdbc:mysql://" + serverName + "/" + userName + "?user="
                                + userName + "&password=" + password);
    }
    /**
    * Returns a connection to the database so that queries can be executed.
    * @return Connection to the database
    * @throws SQLException SQL Exception.
    */
    public static Connection getConnection() throws SQLException {
        if (sConnection == null) {
            createConnection();
        }
        return sConnection;
    }
    /**
    * Close the connection to the database when done. 
    * @throws SQLException SQL Exception.
    */
    public static void closeConnection() throws SQLException {
        if (sConnection != null && !sConnection.isClosed()) {
            sConnection.close();
        }
    }

}
