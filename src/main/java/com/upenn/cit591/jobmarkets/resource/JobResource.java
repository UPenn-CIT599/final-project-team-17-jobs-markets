package com.upenn.cit591.jobmarkets.resource;

import com.upenn.cit591.jobmarkets.JobQuery;
import com.upenn.cit591.jobmarkets.domain.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jobs")
public class JobResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobs() {
    	ArrayList<Job> jobs = new ArrayList<>();
    	Job job1 = new Job("First Job",new Date());
    	job1.setCompany(new Company("hiring company 1"));
    	job1.setRequiredSkills(new String[]{"java", "c++", "communication"});
    	job1.setLocation("New York");
    	job1.setDescription("this is job description");
    	job1.setSalaryMin(110000);
    	job1.setSalaryMax(1500000);
    	

    	Job job2 = new Job("Second Job",new Date());
    	job2.setCompany(new Company("hiring company 2"));
    	job2.setRequiredSkills(new String[]{"everything", "machine learning", "you name it"});
    	job2.setLocation("New Jersey");
    	job2.setDescription("this is job description");
    	job2.setSalaryMin(130900);
    	job2.setSalaryMax(2000000);
    	
    	jobs.add(job1);
    	jobs.add(job2);
        return Response.status(200).entity(jobs).build();
    }
    
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobs(@QueryParam("from") String title,
    		@QueryParam("company") String companyName) {
    	//[/job/query?title=DDDD&companyName=XXX
    	//refer to https://stackoverflow.com/questions/21980244/using-single-jersey-rest-class-for-multiple-path
    	//https://docs.oracle.com/cd/E19798-01/821-1841/gilru/index.html
    	//https://mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/
    	JobQuery query = new JobQuery();
    	ArrayList<Job> jobs = query.jobs();
        return Response.status(200).entity(jobs).build();
    }
}
