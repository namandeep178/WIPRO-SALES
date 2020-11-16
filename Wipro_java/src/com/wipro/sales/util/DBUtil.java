package com.wipro.sales.util;

import java.sql.DriverManager;
import java.sql.*;

public class DBUtil {
	static Connection con;
	public static Connection getDBConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","naman178");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		return con;
	}
}
