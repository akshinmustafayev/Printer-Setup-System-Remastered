package org.PrinterSetupSystem.conn;

import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Represents MySqlConnectionUtils to work with MySQL DB
@author Akshin A. Mustafayev
@version 1.0
*/
public class MySqlConnectionUtils 
{
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException
	{
		//String hostName = "localhost";
		//String dbName = "printersetupsystem";
		//String userName = "root";
		//String password = "";
		String hostName = "";
		String dbName = "";
		String userName = "";
		String password = "";
	    
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("config.properties");
		try 
		{
			prop.load(stream);
			
			hostName = prop.getProperty("db.hostname");
			dbName = prop.getProperty("db.dbname");
			userName = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnectionInstall(String hostName, String dbname, String userName, String password) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbname + "?useUnicode=yes&characterEncoding=UTF-8";
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	    return conn;
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException 
	{
	    Class.forName("com.mysql.jdbc.Driver");
	  
	    // URL Connection for MySQL:
	    // Example: 
	    // jdbc:mysql://localhost:3306/simplehr
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	    return conn;
	}
}
