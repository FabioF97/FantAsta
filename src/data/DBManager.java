package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class loads vendor specific driver,
 * establishes a connection with the database,
 * creates a statement, 
 * provides methods to executes SQL statement
 * and closes the connection with the database.
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class DBManager {

	/**
	 * Statement used in executeQuery and executeUpdate
	 */
	protected Statement statement;
	/**
	 * Connection with the database
	 */
	protected Connection connection;
	
	public DBManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC"); //Load vendor specific driver
		connection = DriverManager.getConnection("jdbc:sqlite:FantAsta.db"); //Establish a connection
		statement = connection.createStatement(); //Create the statement
		statement.setQueryTimeout(30); //This is the time the driver will wait for a Statement object to execute
	}
	
	/**
	 * Used to read data from the database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	/**
	 * Used to write changes on the database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	/**
	 * Used to close the connection with the database
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (connection != null) {
			statement.close();
			connection.close();
		}
	}
}
