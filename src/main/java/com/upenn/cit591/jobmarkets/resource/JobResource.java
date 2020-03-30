package com.upenn.cit591.jobmarkets.resource;

import com.upenn.cit591.jobmarkets.domain.*;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jobs")
public class JobResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobs() {
        Map<String, Job> jobs = new HashMap<>();
        Job job1 = new Job("first job title",new Date()); 
        Job job2 = new Job("second job title",new Date()); 
        jobs.put("first job", job1);
        jobs.put("second job", job2);
        return Response.status(200).entity(jobs).build();
    }
}
