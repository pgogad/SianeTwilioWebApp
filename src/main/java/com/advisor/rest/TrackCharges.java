package com.advisor.rest;

import java.math.BigInteger;
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.advisor.db.DbManager;

@Singleton
@Path("/")
public class TrackCharges
{
	@Path("/amountcharged/{email}/{amount}/{advisorId}/{rate}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String insertCarges( @PathParam("email") String email, @PathParam("amount") String amount, @PathParam("advisorId") String advisorId,
			@PathParam("rate") String rate )
	{
		Connection conn = null;
		String returnStatement = "Failure";

		try
		{
			String format = "yyyy-MM-dd HH:mm:ss";
			DateTime date = new DateTime( DateTimeZone.UTC );
			String insert = "INSERT INTO call_charges (EMAIL_ID, AMOUNT, INSERT_DATE, ADVISOR_ID, ADVISOR_RATE ) VALUES('" +
					URLDecoder.decode( email, "UTF-8" ) + "','" +
					URLDecoder.decode( amount, "UTF-8" ) + "',"
					+ date.toString( format ) + "," +
					BigInteger.valueOf( Long.valueOf( URLDecoder.decode( advisorId, "UTF-8" ) ) ) + ",'" +
					URLDecoder.decode( rate, "UTF-8" ) + "')";
			
			conn = DbManager.getConnection( );
			Statement stmt = conn.createStatement( );
			
			boolean hasInserted = stmt.execute( insert );
			
			if(hasInserted)
			{
				returnStatement = "success";
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace( );
		}
		return returnStatement;
	}
}
