package com.upenn.cit591.jobmarkets.domain;

import java.util.ArrayList;

/**
 * Hiring company class
 * @author zhongliu
 *
 */
public class Company {

	private String companyName;
	private ArrayList<Job> jobs = new ArrayList<>();
	
	public Company(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the jobs
	 */
	public ArrayList<Job> getJobs() {
		return jobs;
	}
	
	/**
	 * add job to the company
	 * @param job
	 */
	public void addJob(Job job) {
		this.jobs.add(job);
	}

}
