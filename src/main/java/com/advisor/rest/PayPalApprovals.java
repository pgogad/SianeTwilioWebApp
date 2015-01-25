package com.advisor.rest;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Statement;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;

import com.advisor.db.DbManager;

@Singleton
@Path("/")
public class PayPalApprovals
{
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/paypalapproval/{payLoad}")
	@GET
	public String doStoreApprovals( @PathParam("payLoad") String incomingData )
	{
		try
		{
			try
			{
				System.out.println( URLDecoder.decode( incomingData, "UTF-8" ) );
				Connection conn = DbManager.getConnection( );
				System.out.println( "========= Database connection created =====================" );
				Statement stmt = conn.createStatement( );

				DateTime date = new DateTime( );
				String format = "yyyy-MM-dd HH:mm:ss";
				String query = "INSERT INTO pay_pal_approval (APPROVAL_OBJ,USER_ID,APPROVAL_DATE ) values( '" + URLDecoder.decode( incomingData, "UTF-8" )
						+ "','test', '" + date.toString( format ) + "')";

				stmt.execute( query );

				if ( !conn.isClosed( ) )
				{
					conn.close( );
				}
			}
			catch ( Exception ex )
			{
				throw new Exception( "Could not connect to Database ", ex );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
			return "Fail";
		}
		return "Success";
	}
}
