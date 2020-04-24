package com.upenn.cit591.jobmarkets.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.upenn.cit591.jobmarkets.libs.WordPair;

/**
 * Job class
 * @author zhongliu
 *
 */
public class Job {
	
	public static String QUERY_KEY_TITLE = "TITLE";
	public static String QUERY_KEY_LOCATION_STATE = "LOCATION_STATE";
	public static String QUERY_KEY_SKILLS = "REQ_SKILLS";
	public static String QUERY_KEY_COMPANY = "COMPANY";
//	public static String QUERY_KEY_SALARY_MIN = "SALARY_MIN";
//	public static String QUERY_KEY_SALARY_MAX = "SALARY_MAX";
	
	public static int SALARY_UNSPECIFIED = -1;

	
	private String title;
//	private Date postDate;
	private String description;
	private Company company;
	private String location; //Primary Location. Format: City | State. E.g. Miami | FL
	private String locationCity="";
	private String locationState="";
	private String[] requiredSkills;
//	private String[] optionalSkills;
//	private double salaryMin;
//	private double salaryMax;
	
	private JobBenchmark benchmarkJob = null;
	
	
	
	public Job(String title) {
		this.title = title;
//		this.postDate = postDate;
	}
	
	/**
	 * given skills, to check whether matching to this job's requirement. 
	 * @param skills
	 * @return true - matched, false - not match
	 */
	public boolean isSkillsMatched(String[] skills) {
		//we treat this as matching, as long as there is one skill given matching to job's required skills. 
		for(int i=0;i<skills.length;i++) {
			for(int j=0;j<this.requiredSkills.length;j++) {
				WordPair wp = new WordPair(skills[i],this.requiredSkills[j]);
				if (wp.getCommonPercent()>=0.7) {
					return true;
				}
			}
		}
		return  false;
	}
	
	/**
	 * to check whether this job can offer salary w/in given range by min and max
	 * @param min
	 * @param max
	 * @return true if given range is w/in this jon's range, otherwise, false. 
	 */
//	public boolean isSalaryInRange(double min, double max) {
//		if (min!=this.SALARY_UNSPECIFIED) {
//			if(this.salaryMin<min && this.salaryMax<min){
//				return false;
//			}
//		}
//		if (max!=this.SALARY_UNSPECIFIED) {
//			if(this.salaryMin>max && this.salaryMax>max) {
//				return false;
//			}
//		}
//		return true;
//	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the postDate
	 */
//	public Date getPostDate() {
//		return postDate;
//	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * 
	 * @return city
	 */
	public String getLocationCity() {
		return this.locationCity;
	}
	
	/**
	 * 
	 * @return state
	 */
	public String getLocationState() {
		return this.locationState;
	}
	

	/**
	 * @param location the location to set, Format: City | State. E.g. Miami | FL
	 */
	public void setLocation(String location) {
		this.location = location;
		if(location!=null) {
			String[] loc = location.split("\\|");
			this.locationCity = loc[0].trim().toUpperCase();
			if(loc.length>=2) {
				this.locationState = loc[1].trim().toUpperCase();
			}
		}
	}

	/**
	 * @return the requiredSkills
	 */
	public String[] getRequiredSkills() {
		return requiredSkills;
	}

	/**
	 * @param requiredSkills the requiredSkills to set
	 */
	public void setRequiredSkills(String[] requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	/**
	 * @return the optionalSkills
	 */
//	public String[] getOptionalSkills() {
//		return optionalSkills;
//	}

	/**
	 * @param optionalSkills the optionalSkills to set
	 */
//	public void setOptionalSkills(String[] optionalSkills) {
//		this.optionalSkills = optionalSkills;
//	}

	/**
	 * @return the salaryMin
	 */
//	public double getSalaryMin() {
//		return salaryMin;
//	}

	/**
	 * @param salaryMin the salaryMin to set
	 */
//	public void setSalaryMin(double salaryMin) {
//		this.salaryMin = salaryMin;
//	}

	/**
	 * @return the salaryMax
	 */
//	public double getSalaryMax() {
//		return salaryMax;
//	}

	/**
	 * @param salaryMax the salaryMax to set
	 */
//	public void setSalaryMax(double salaryMax) {
//		this.salaryMax = salaryMax;
//	}

	/**
	 * @return the benchmarkJob
	 */
	public JobBenchmark getBenchmarkJob() {
		return benchmarkJob;
	}

	/**
	 * @param benchmarkJob the benchmarkJob to set
	 */
	public void setBenchmarkJob(JobBenchmark benchmarkJob) {
		this.benchmarkJob = benchmarkJob;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Job [title=" + title + ", description=" + description + ", company=" + company + ", location="
				+ location + ", locationCity=" + locationCity + ", locationState=" + locationState + ", requiredSkills="
				+ Arrays.toString(requiredSkills) + ", benchmarkJob=" + benchmarkJob + "]";
	}
	
	

	/**
	 * @param postDate the postDate to set
	 */
//	public void setPostDate(Date postDate) {
//		this.postDate = postDate;
//	}	

}
