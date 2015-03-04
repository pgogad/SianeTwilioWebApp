package com.advisor.rest;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class Ping
{
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ping")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doPing()
	{
		return "pong";
	}
}
