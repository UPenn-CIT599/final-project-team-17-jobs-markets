package com.upenn.cit591.jobmarkets.resource;


import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response ping() {
        Map<String, String> pong = new HashMap<>();
        pong.put("pong", "Hello, World! Here we go, this is final CIT 591 project... Yeah :)");
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(pong)
        		.build();
    }
}
