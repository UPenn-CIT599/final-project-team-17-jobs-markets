package com.upenn.cit591.jobmarkets.domain;

import java.util.ArrayList;

/**
 * industry benchmark of a specific job. 
 * @author zhongliu
 *
 */
public class JobBenchmark {

	private String title;
	private double salaryMin;
	private double salaryMax;
	private String[] requiredSkills;
	private String[] optionalSkills;
	
	public JobBenchmark(String title, double salaryMin, double salaryMax, String[] requiredSkills) {
		this.title = title;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.requiredSkills = requiredSkills;
	}

	/**
	 * @return the optionalSkills
	 */
	public String[] getOptionalSkills() {
		return optionalSkills;
	}

	/**
	 * @param optionalSkills the optionalSkills to set
	 */
	public void setOptionalSkills(String[] optionalSkills) {
		this.optionalSkills = optionalSkills;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the salaryMin
	 */
	public double getSalaryMin() {
		return salaryMin;
	}

	/**
	 * @return the salaryMax
	 */
	public double getSalaryMax() {
		return salaryMax;
	}

	/**
	 * @return the requiredSkills
	 */
	public String[] getRequiredSkills() {
		return requiredSkills;
	}
	
	

}
