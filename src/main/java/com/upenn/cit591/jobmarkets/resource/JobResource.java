package com.upenn.cit591.jobmarkets.resource;

import com.upenn.cit591.jobmarkets.JobQuery;
import com.upenn.cit591.jobmarkets.domain.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/jobs")
public class JobResource {
    
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobs() {
    	ArrayList<Job> jobs = new ArrayList<>();
    	Job job1 = new Job("First Job");
    	job1.setCompany(new Company("hiring company 1"));
    	job1.setRequiredSkills(new String[]{"java", "c++", "communication"});
    	job1.setLocation("New York");
    	job1.setDescription("this is job description");
//    	job1.setSalaryMin(110000);
//    	job1.setSalaryMax(1500000);
    	

    	Job job2 = new Job("Second Job");
    	job2.setCompany(new Company("hiring company 2"));
    	job2.setRequiredSkills(new String[]{"everything", "machine learning", "you name it"});
    	job2.setLocation("New Jersey");
    	job2.setDescription("this is job description");
//    	job2.setSalaryMin(130900);
//    	job2.setSalaryMax(2000000);
    	
    	jobs.add(job1);
    	jobs.add(job2);
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobs).build();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response all() {
    	JobQuery query = new JobQuery();
    	ArrayList<Job> jobs = query.jobs();
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobs).build();
    }
    
    
    @Path("/query/state/{state}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response queryJobs(@DefaultValue("NY") @PathParam("state") String state) {
    	//[/jobs/query?title=TITLE&company=COMPANY&state=STATE, E.g. /jobs/query?title=&company=&state=NY
    	//refer to https://stackoverflow.com/questions/21980244/using-single-jersey-rest-class-for-multiple-path
    	//https://docs.oracle.com/cd/E19798-01/821-1841/gilru/index.html
    	//https://mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/
    	//https://docs.oracle.com/cd/E19776-01/820-4867/ghrst/index.html
    	
//       System.err.println("State="+state);
        
    	JobQuery query = new JobQuery();
    	ArrayList<Job> jobs = query.jobs(null, state, null);
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobs).build();
    }
    
    
    
//    @Path("/query")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response queryJobs(@Context UriInfo ui) {
//    	//[/jobs/query?title=TITLE&company=COMPANY&state=STATE, E.g. /jobs/query?title=&company=&state=NY
//    	//refer to https://stackoverflow.com/questions/21980244/using-single-jersey-rest-class-for-multiple-path
//    	//https://docs.oracle.com/cd/E19798-01/821-1841/gilru/index.html
//    	//https://mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/
//    	//https://docs.oracle.com/cd/E19776-01/820-4867/ghrst/index.html
//    	
//        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
//       
//        
//        System.out.println("getPathParameters"+ ui.getPathParameters().toString());
//
//        System.err.println("queryParams="+ queryParams.toString());
//        
//        Object[] keys = queryParams.keySet().toArray();
//        Object[] values = queryParams.values().toArray();
//        for(int i=0;i<keys.length;i++){
//        	System.out.println(keys[i]+"="+values[i]);
//        }
//
//        String title = null;
//        if(queryParams.get("title")!=null) {
//        	title = queryParams.get("title").get(0);
//        }
//        
//        String company = null;
//        if(queryParams.get("company")!=null) {
//        	title = queryParams.get("company").get(0);
//        }
//        
//        String state = null;
//        if(queryParams.get("state")!=null) {
//        	title = queryParams.get("state").get(0);
//        }
//        
//    	JobQuery query = new JobQuery();
//    	ArrayList<Job> jobs = query.jobs(title, state, company);
//        return Response.status(200).entity(jobs).build();
//    }
    
    
    @GET
    @Path("/titles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobTypes() {
    	JobQuery query = new JobQuery();
    	HashMap<String,Integer> jobTypes = query.jobCountByTitle();
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobTypes).build();
    }
    
    
    @GET
    @Path("/states")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobStates() {
    	JobQuery query = new JobQuery();
    	HashMap<String,Integer> jobStates = query.jobCountByStates();
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobStates).build();
    }
    
    @GET
    @Path("/skills")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobSkills() {
    	JobQuery query = new JobQuery();
    	HashMap<String,Integer> jobSkills = query.jobCountBySkills();
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobSkills).build();
    }
    
    
    @GET
    @Path("/companies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response jobCompanies() {
    	JobQuery query = new JobQuery();
    	HashMap<String,Integer> jobCompanies = query.jobCountByCompany();
        return Response.status(200)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        		.entity(jobCompanies).build();
    }
    
    
}
