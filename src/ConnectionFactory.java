package arena.db.common;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static ConnectionFactory instance = new ConnectionFactory();
	private static final String DbName="/arena"; 		//
	private static final String URL1="jdbc:mysql://";
	private static final String IP = "localhost:8080";
	private static final String URL = URL1+IP+DbName;
	private static final String USER = "root";								
	private static final String PASSWORD = "kumar";							
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 		

private ConnectionFactory() 
	{	
		try 
		{
			Class.forName(DRIVER_CLASS);
		}
		 catch (Exception e)
		  {
			System.out.println("Driver Detection Failed!");
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			System.out.println("Unable to Connect Database!");
		}
		return connection;
	}	
	
	public static Connection getConnection() {
		
		return instance.createConnection();
	}
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e){}
		}
	}
}
