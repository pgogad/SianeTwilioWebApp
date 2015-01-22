package com.advisor.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Singleton
@Path("/rates")
public class AdvisorRates
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRates()
	{
		return "5.00";
	}
}
