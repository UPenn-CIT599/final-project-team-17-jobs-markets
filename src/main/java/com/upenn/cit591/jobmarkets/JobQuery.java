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
	public ArrayList<Job> jobs(String title, String state, String companyName){
		HashMap<String, String> query = new HashMap<>();
		if(title!=null&&title!="") {
			query.put(Job.QUERY_KEY_TITLE, title);
		}
		if(state!=null&&state!="") {
			query.put(Job.QUERY_KEY_LOCATION_STATE, state);
		}
		if(companyName!=null&&companyName!="") {
			query.put(Job.QUERY_KEY_COMPANY, companyName);
		}
		if(query.size()==0) {//return all jobs
			return this.jobs();
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
	public ArrayList<Job> jobs(String title){
		HashMap<String, String> query = new HashMap<>();
		if(title!=null) {
			query.put(Job.QUERY_KEY_TITLE, title);
		}
		
//		if(salaryMin!=Job.SALARY_UNSPECIFIED && salaryMin>=0) {
//			query.put(Job.QUERY_KEY_SALARY_MIN, String.valueOf(salaryMin));
//		}
//		
//		if(salaryMax!=Job.SALARY_UNSPECIFIED && salaryMax>=0) {
//			query.put(Job.QUERY_KEY_SALARY_MAX, String.valueOf(salaryMax));
//		}
		
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
	public ArrayList<Job> jobsBySkills(String skills){
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
	
	/**
	 * 
	 * @return a HashMap that includes a list of job titles with count. 
	 */
	public HashMap<String,Integer> jobCountByTitle(){
		return this.jobs.jobCountByTitle();
	}
	
	/**
	 * 
	 * @return a HashMap that includes a list of companies with openning job count. 
	 */
	public HashMap<String,Integer> jobCountByCompany(){
		return this.jobs.jobCountByCompany();
	}
	
	/**
	 * 
	 * @return a HashMap that includes a list of states with openning job count. 
	 */
	public HashMap<String,Integer> jobCountByStates(){
		return this.jobs.jobCountByStates();
	}
	
	/**
	 * 
	 * @return a HashMap that includes a list of job required skills with openning job count. 
	 */
	public HashMap<String,Integer> jobCountBySkills(){
		return this.jobs.jobCountBySkills();
	}

	
	public static void main(String[] args) {
		JobQuery jq = new JobQuery();
//		ArrayList<Job> jobs = jq.jobs() ;
//		ArrayList<Job> jobs = jq.jobs(null, "CA", "");
//		ArrayList<Job> jobs = jq.jobs(null, "FL", "Voyager");
//		ArrayList<Job> jobs = jq.jobs("Data Scientist", "FL", "Voyager");
		ArrayList<Job> jobs = jq.jobs("Data Scientist", "FL", "");

		System.out.println(jobs.size()+" jobs found.");
		int i=0;
		for(Job j:jobs) {
			i++;
			System.out.println(j.toString());
		}

	}

}
