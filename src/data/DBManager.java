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

	
	protected Statement statement;
	protected Connection connection;
	
	public DBManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:FantAsta.db");
		statement = connection.createStatement();
		statement.setQueryTimeout(30); //Questo non penso serva ma non ne sono sicuro
		//showMetadata(); Penso non ce ne freghi nulla di questo
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	public void close() throws SQLException {
		if (connection != null) {
			statement.close();
			connection.close();
		}
	}
}
