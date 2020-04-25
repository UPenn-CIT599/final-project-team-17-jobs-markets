package com.upenn.cit591.jobmarkets.libs;

import java.util.Date;

import com.upenn.cit591.jobmarkets.domain.Job;

/**
 * A helper class uses predefined format of each field to validate and transform raw data read from jobs CSV file
 * @author zhongliu
 *
 */
public class JobsCSVHelper {
	
	/**
	 * convert a given postDateString to date format
	 * @param postDateString format: mm/dd/yyyy
	 * @return date
	 */
	public Date getJobPostDate(String postDateString) {
		if(postDateString==null) {
			return null;
		}
		try {
			return new java.text.SimpleDateFormat("MM/dd/yyyy").parse(postDateString);
		}catch (Exception e) {
			System.err.println(postDateString+" is not a valid date ("+postDateString+")");
			return null;
		}
	}
	
	/**
	 * get an array of skills from given skillsString
	 * @param skillsString skillsString, using "," to saperate multiple skills
	 * @return an array of skills from given skillsString
	 */
	public String[] getSkills(String skillsString){
		if(skillsString==null) {
			return null;
		}
		return skillsString.replace(";", "~").split("~");
	}
	
	/**
	 * get low side of salary range
	 * @param salaryString format: Low-High (e.g. 5000-8000	), inclusive. USD
	 * @return low side of salary range
	 */
	public double getSalaryMin(String salaryString) {
		if(salaryString==null) {
			return Job.SALARY_UNSPECIFIED;
		}
		String[] salaryRange = salaryString.split("-");
		try {
			return Double.parseDouble(salaryRange[0]);
		}catch(Exception e) {
			System.err.println("getSalaryMin Error: "+e.getMessage());
			return Job.SALARY_UNSPECIFIED;
		}
	}
	
	/**
	 * get high side of salary range
	 * @param salaryString format: Low-High (e.g. 5000-8000	), inclusive. USD
	 * @return high side of salary range. -1: not specified
	 */
	public double getSalaryMax(String salaryString) {
		if(salaryString==null) {
			return Job.SALARY_UNSPECIFIED;
		}
		String[] salaryRange = salaryString.split("-");
		if(salaryRange.length==2) {
			try {
				return Double.parseDouble(salaryRange[1]);
			}catch(Exception e) {
				System.err.println("getSalaryMin Error: "+e.getMessage());
				return Job.SALARY_UNSPECIFIED;
			}
		}else {
			return Job.SALARY_UNSPECIFIED;
		}
	}

	public JobsCSVHelper() {
		
	}

}
