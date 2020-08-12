package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//this class is for control the connection
//and return the object from database
public class PostgreSQLDbDAOFactory {

	public static final String DBURL = "jdbc:postgresql://localhost:5432/classicmodels";
	private static InitialContext ctx;
	private static DataSource ds;

	public static Connection createConnection() throws SQLException {
		Connection conn=null;
		//Write some codes here to create connection object using DriverManager
		//conn = DriverManager.getConnection(DBURL, "postgres", "postgresql");
		//return conn;
		// Use DRIVER and DBURL to create a connection
		// Recommend connection pool implementation/usage
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/classicmodels");
			} catch (NamingException e) {
			//throw new ServletException(e);
			}
			conn = ds.getConnection();
			return conn;
			// Use DRIVER and DBURL to create a connection
			// Recommend connection pool implementation/usage
		
	}

	public PostgreSQLDbOrdersDAO getOrdersDAO() {
		// PostgreSQLDbEmployeeDAO implements OrdersDAO
		// Write some codes here to return PostgreSQLDbEmployeeDAO object
		return new PostgreSQLDbOrdersDAO();
	}
}
