package com.upenn.cit591.jobmarkets;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.upenn.cit591.jobmarkets.domain.Company;
import com.upenn.cit591.jobmarkets.domain.Job;
import com.upenn.cit591.jobmarkets.domain.Jobs;

/**
 * Job Query interface 
 * @author zhongliu
 *
 */
public class JobQuery {

	private Jobs jobs = null;
	public JobQuery() {
		Config.init();
		this.jobs = new Jobs();
	}
	
	/**
	 * get all jobs
	 * @return
	 */
	public ArrayList<Job> jobs(){
		return jobs.getJobs();
	}
	
	/**
	 * get a list of jobs by specific title
	 * @param title
	 * @param location
	 * @return
	 */
	public ArrayList<Job> jobs(String title, String location, String companyName){
		HashMap<String, String> query = new HashMap<>();
		if(title!=null) {
			query.put(Job.QUERY_KEY_TITLE, title);
		}
		if(location!=null) {
			query.put(Job.QUERY_KEY_LOCATION, location);
		}
		if(companyName!=null) {
			query.put(Job.QUERY_KEY_COMPANY, companyName);
		}
		if(query.size()==0) {
			return null;
		}
		return jobs.getJobsByQuery(query);
	}
	
	/**
	 * get a list of jobs by title and salary range
	 * @param title
	 * @param salaryMin
	 * @param salaryMax
	 * @return
	 */
	public ArrayList<Job> jobs(String title, double salaryMin, double salaryMax){
		HashMap<String, String> query = new HashMap<>();
		if(title!=null) {
			query.put(Job.QUERY_KEY_TITLE, title);
		}
		
		if(salaryMin!=Job.SALARY_UNSPECIFIED && salaryMin>=0) {
			query.put(Job.QUERY_KEY_SALARY_MIN, String.valueOf(salaryMin));
		}
		
		if(salaryMax!=Job.SALARY_UNSPECIFIED && salaryMax>=0) {
			query.put(Job.QUERY_KEY_SALARY_MAX, String.valueOf(salaryMax));
		}
		
		if(query.size()==0) {
			return null;
		}
		
		return jobs.getJobsByQuery(query);
	}
	
	/**
	 * return a list of jobs that match to input skills
	 * @param skills, if multiple skills, use "," to separate. 
	 * @return
	 */
	public ArrayList<Job> jobs(String skills){
		if(skills==null) {
			return null;
		}
		HashMap<String, String> query = new HashMap<>();
		query.put(Job.QUERY_KEY_SKILLS, skills);
		return jobs.getJobsByQuery(query);
	}
	
	/**
	 * get a list of hiring companies
	 * @return
	 */
	public Company[] hiringCompanies(){
		return (Company[])this.jobs.getHiringCompanies().values().toArray();
	}

	
	public static void main(String[] args) {
		JobQuery jq = new JobQuery();
		ArrayList<Job> jobs = jq.jobs() ;
		for(Job j:jobs) {
			System.out.println("Job Title="+j.getTitle());
		}

	}

}
