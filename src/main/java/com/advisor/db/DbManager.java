package com.advisor.db;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager
{
	private static Connection _connection = getConnection();

	private DbManager()
	{
		try
		{
			if (null == _connection)
			{
				_connection = getConnection();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static Connection getInstance()
	{
		return _connection;
	}

	private static Connection getConnection()
	{
		try
		{
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

			return DriverManager.getConnection(dbUrl, username, password);
		}
		catch (Exception ex)
		{
			return null;
		}
	}
}
