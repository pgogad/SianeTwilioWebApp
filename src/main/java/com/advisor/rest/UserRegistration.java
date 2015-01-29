package com.advisor.rest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.advisor.db.DbManager;

@Singleton
@Path("/")
public class UserRegistration
{
	@SuppressWarnings("resource")
	@Path("/register/{email}/{name}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String doRegistration( @PathParam("email") String email, @PathParam("name") String name, @PathParam("password") String password )
	{
		Connection conn = null;
		String returnStatement = "Failure";
		try
		{
			String fetch = "SELECT count(*) AS ROWCOUNT FROM user_name WHERE EMAIL_ID='" + email + "'";
			conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );
			ResultSet rs = stmt.executeQuery( fetch );
			rs.next( );
			int count = rs.getInt( "ROWCOUNT" );
			rs.close( );
			System.out.println( "Count = " + count );

			if ( count == 0 )
			{
				String insert = "INSERT INTO user_name (EMAIL_ID,USER_NAME,PASSWORD) values( '" + URLDecoder.decode( email, "UTF-8" ) + "','"
						+ URLDecoder.decode( name, "UTF-8" ) + "', '" + URLDecoder.decode( password, "UTF-8" ) + "')";
				if ( conn.isClosed( ) )
				{
					conn = DbManager.getConnection( );
					stmt = conn.createStatement( );
				}
				stmt.execute( insert );
				returnStatement = "Success";
			}
			else
			{
				returnStatement = "Duplicate";
			}

			if ( conn.isClosed( ) )
			{
				conn.close( );
			}

		}
		catch ( URISyntaxException e )
		{
			e.printStackTrace( );
		}
		catch ( SQLException e )
		{
			e.printStackTrace( );
		}
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			try
			{
				if ( !conn.isClosed( ) )
				{
					conn.close( );
				}
			}
			catch ( SQLException e )
			{
				e.printStackTrace( );
			}
		}
		return returnStatement;
	}
}
