package com.advisor.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager
{
	public static Connection getConnection() throws URISyntaxException, SQLException
	{
		//URI dbUri = new URI( System.getenv( "DATABASE_URL" ) );
		String conf = "mysql://bbd1ca47079425:20d6d60d@us-cdbr-iron-east-01.cleardb.net/heroku_55dcc7186e48953?reconnect=true";
		//String conf = System.getenv( "DATABASE_URL" );
		String userPass = conf.substring( 0,conf.indexOf( "@" ) );
		String host = conf.substring( conf.indexOf( "@" ) + 1 );
		userPass = userPass.substring( userPass.lastIndexOf( "/" ) + 1);
		
		String[] cred = userPass.split( ":" );
//		System.out.println( host );
//		System.out.println( cred[0] );
//		System.out.println( cred[1] );
		
		
//		URI dbUri = new URI( "mysql://bbd1ca47079425:20d6d60d@us-cdbr-iron-east-01.cleardb.net/heroku_55dcc7186e48953?reconnect=true" );
//		String username = dbUri.getUserInfo( ).split( ":" )[0];
//		//String password = dbUri.getUserInfo( ).split( ":" )[1];
		String dbUrl = "jdbc:mysql://" + host;
		return DriverManager.getConnection( dbUrl, cred[0], cred[1] );
	}
}
