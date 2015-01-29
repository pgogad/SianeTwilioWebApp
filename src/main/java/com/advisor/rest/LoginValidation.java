package com.advisor.rest;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class LoginValidation
{

	@Path("/dologin/{username}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String doLoggingIn( @PathParam("username") String email, @PathParam("password") String password )
	{
		Connection conn = null;
		String returnStatement = "Failure";
		try
		{
			String fetch = "SELECT EMAIL_ID, PASSWORD FROM user_name WHERE EMAIL_ID='" + email + "'";
			conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );
			ResultSet rs = stmt.executeQuery( fetch );

			if ( rs.first( ) )
			{
				String emailDB = rs.getString( "EMAIL_ID" );
				String passwordDB = rs.getString( "PASSWORD" );
				rs.close( );

				if ( (URLDecoder.decode( email, "UTF-8" )).equals( emailDB ) && (URLDecoder.decode( password, "UTF-8" )).equals( passwordDB ) )
				{
					returnStatement = "Done";
				}
				else
				{
					returnStatement = "Email address or password did not match";
				}
			}
			else
			{
				returnStatement = "Invalid email address";
			}
			
			if(!conn.isClosed( ))
			{
				conn.close( );
			}

		}
		catch ( Exception ex )
		{
			ex.printStackTrace( );
		}
		return returnStatement;
	}
}
