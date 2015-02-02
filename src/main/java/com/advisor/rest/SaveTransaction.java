package com.advisor.rest;

import java.net.URLDecoder;
import java.sql.Connection;
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
public class SaveTransaction
{

	@Path("/savetransaction/{email}/{amount}/{date}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String savePurchase( @PathParam("email") String email, @PathParam("amount") String amount, @PathParam("date") String date )
	{
		Connection conn = null;
		String returnStatement = "Failure";

		try
		{
			String insert = "INSERT INTO USER_PAYMENT_INFO ( EMAIL_ID,AMOUNT,DATE) VALUES ('" + URLDecoder.decode( email, "UTF-8" ) + "','"
					+ URLDecoder.decode( amount, "UTF-8" ) + "','" + URLDecoder.decode( date, "UTF-8" ) + "')";
			conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );

			stmt.execute( insert );

			if ( !conn.isClosed( ) )
			{
				conn.close( );
			}

			returnStatement = "success";

		}
		catch ( Exception ex )
		{
			ex.printStackTrace( );
		}
		return returnStatement;
	}
}
