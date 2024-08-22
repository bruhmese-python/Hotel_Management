package com.hotelmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String JDBC_URL="jdbc:sqlite:/home/admin/MySQLiteDB";
	private static final String DRIVER_CLASS="org.sqlite.JDBC";

    public static Connection getDBConnection() throws SQLException {
    	try
    	{
    		Class.forName(DRIVER_CLASS);
    	}
    	catch(ClassNotFoundException e)
    	{
    		throw new SQLException("Sqlite JDBC driver not found",e);
    	}
    	return DriverManager.getConnection(JDBC_URL);
         
    }

    public static void closeDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
