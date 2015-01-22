package com.advisor.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.twilio.sdk.client.TwilioCapability;
import com.twilio.sdk.client.TwilioCapability.DomainException;

@Singleton
@Path("/rest")
public class AdvisorToken
{

	public static final String ACCOUNT_SID = "ACab45f33c19bb7c80810538df10ccb061";
	public static final String AUTH_TOKEN = "b0c71987d0f1af79b7cc5d177f5ea8a9";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getCapabilityToken()
	{
		return generateCapabilityToken();
	}
	
	private String generateCapabilityToken()
	{
		String applicationSid = "AP5b2f8c0258d8978f8c449f72734c7ebb";
		TwilioCapability capability = new TwilioCapability(ACCOUNT_SID, AUTH_TOKEN);
		capability.allowClientOutgoing(applicationSid);
		String token = null;

		try
		{
			token = capability.generateToken(60);
		}
		catch (DomainException e)
		{
			e.printStackTrace();
		}
		return token;
	}
}
