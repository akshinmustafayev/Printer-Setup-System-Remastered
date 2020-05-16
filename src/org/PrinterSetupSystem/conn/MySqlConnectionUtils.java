package org.PrinterSetupSystem.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionUtils 
{
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException 
	{
	     String hostName = "localhost";
	     String dbName = "printersetupsystem";
	     String userName = "root";
	     String password = "";
	     return getMySQLConnection(hostName, dbName, userName, password);
	 }
	
	public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	    
	     Class.forName("com.mysql.jdbc.Driver");
	  
	     // URL Connection for MySQL:
	     // Example: 
	     // jdbc:mysql://localhost:3306/simplehr
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	     Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	     return conn;
	 }
}
