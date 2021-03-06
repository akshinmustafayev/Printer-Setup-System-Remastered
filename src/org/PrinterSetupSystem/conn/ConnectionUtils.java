package org.PrinterSetupSystem.conn;

import java.sql.Connection;
import java.sql.SQLException;

/** Represents ConnectionUtils to connect to DB
@author Akshin A. Mustafayev
@version 1.0
*/
public class ConnectionUtils 
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		// todoo: Add possibility to connect for another DB Engines

		return MySqlConnectionUtils.getMySQLConnection();
		// return OracleConnUtils.getOracleConnection();
		// return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
		// return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
		// return PostGresConnUtils.getPostGresConnection();
	}
   
	public static void closeQuietly(Connection conn) 
	{
		try 
		{
	    	conn.close();
	    } 
		catch (Exception e) { }
	}

	public static void rollbackQuietly(Connection conn) 
	{
		try
		{
			conn.rollback();
		} 
		catch (Exception e) { }
	}
	
	public static Connection getConnectionInstall(String hostName, String dbname, String userName, String password) throws ClassNotFoundException, SQLException 
	{
		return MySqlConnectionUtils.getMySQLConnectionInstall(hostName, dbname, userName, password);
	}
}
