package com.advisor.rest;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class LoginValidation
{

	@Path("/dologin/{username}/{password}")
	@Consumes(MediaType.TEXT_XML)
	@GET
	public String doLoggingIn( @PathParam("username") String email, @PathParam("password") String password )
	{

		return "Done";
	}
}
