package org.exam.repository;
import java.sql.*;

import org.exam.helper.PathHelper;
public class DBConfig {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	public DBConfig()
	{
		try {
			PathHelper phelp = new PathHelper();
			
			Class.forName(PathHelper.p.getProperty("driverClassName"));
			conn = DriverManager.getConnection(PathHelper.p.getProperty("url"),PathHelper.p.getProperty("userName"),PathHelper.p.getProperty("password"));
			
			
		}catch(Exception ex)
		{
			System.out.println("Error is "+ex);
		}
	}
}
