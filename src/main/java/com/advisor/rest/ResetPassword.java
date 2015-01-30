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
public class ResetPassword
{

	@Path("/resetpassword/{email}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String resetPassword( @PathParam("email") String email, @PathParam("password") String password )
	{
		// Email does not exist
		// Success
		Connection conn = null;
		String returnStatement = "Failure";
		try
		{
			String fetch = "SELECT EMAIL_ID, PASSWORD FROM user_name WHERE EMAIL_ID='" + URLDecoder.decode( email, "UTF-8" ) + "'";
			conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );
			ResultSet rs = stmt.executeQuery( fetch );

			if ( rs.first( ) )
			{
				rs.close( );
				String update = "UPDATE user_name SET PASSWORD = '" + URLDecoder.decode( password, "UTF-8" ) + "' WHERE EMAIL_ID='"
						+ URLDecoder.decode( email, "UTF-8" ) + "'";
				
				if(conn.isClosed( ))
				{
					conn = DbManager.getConnection( );
				}
				
				stmt = conn.createStatement( );
				stmt.executeUpdate( update );
				
				returnStatement = "Success";
			}
			else
			{
				returnStatement = "Email does not exist";
			}

			if ( !conn.isClosed( ) )
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
