package com.upenn.cit591.jobmarkets.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.upenn.cit591.jobmarkets.Config;
import com.upenn.cit591.jobmarkets.libs.CSVReader;
import com.upenn.cit591.jobmarkets.libs.JobsCSVHelper;
import com.upenn.cit591.jobmarkets.libs.WordPair;

/**
 * Jobs class to manage data from jobs csv data file. 
 * @author zhongliu
 *
 */
public class Jobs {
	
	ArrayList<Job> jobs = new ArrayList<>();
	HashMap<String,Company> hiringCompanies = new HashMap<>();
	
	
	public Jobs() {
		System.out.println("load job posting data from"+Config.getPostingFileName());
		this.loadJobsFromCSV(Config.getPostingFileName());
	}
	
	public Jobs(String csvFile) {
		this.loadJobsFromCSV(csvFile);
	}
	
	
	/**
	 * 
	 * @return a list of job titles with count of each
	 */
	public HashMap<String,Integer> jobCountByTitle(){
		HashMap<String,Integer> jobTypes = new HashMap<>();
		for(Job j:this.jobs) {
			int count = 1;
			if(jobTypes.get(j.getTitle())!=null) { //increase count
				count = jobTypes.get(j.getTitle()).intValue()+1;
			}
			jobTypes.put(j.getTitle(), count);
			
		}
		return jobTypes;
	}
	
	/**
	 * 
	 * @return a list of job titles with count of each 
	 */
	public HashMap<String,Integer> jobCountByStates(){
		HashMap<String,Integer> jobStates = new HashMap<>();
		for(Job j:this.jobs) {
			int count = 1;
			String state = j.getLocationState();
			if(state==null||state=="") {
				continue; 
			}
			if(jobStates.get(state)!=null) { //increase count
				count = jobStates.get(state).intValue()+1;
			}
			jobStates.put(state, count);
			
		}
		return jobStates;
	}
	
	/**
	 * 
	 * @return a list of job titles with count of each 
	 */
	public HashMap<String,Integer> jobCountByCompany(){
		HashMap<String,Integer> jobCompanies = new HashMap<>();
		for(Job j:this.jobs) {
			int count = 1;
			if(j.getCompany()==null) {
				continue;
			}
			String companyName = j.getCompany().getCompanyName().toUpperCase();
			if(jobCompanies.get(companyName)!=null) { //increase count
				count = jobCompanies.get(companyName).intValue()+1;
			}
			jobCompanies.put(companyName, count);
			
		}
		return jobCompanies;
	}
	
	
	/**
	 * 
	 * @return a list of job skills with job count of each
	 */
	public HashMap<String,Integer> jobCountBySkills(){
		HashMap<String,Integer> jobSkillMap = new HashMap<>();
		for(Job j:this.jobs) {
			String skills[] = j.getRequiredSkills();
			if(skills.length==0) {
				continue;
			}
			for(int i=0;i<skills.length;i++) {
				String skill = skills[i].trim().toUpperCase();
				int count = 1;
				if(jobSkillMap.get(skill)!=null) {
					count = jobSkillMap.get(skill).intValue()+1;
				}
				jobSkillMap.put(skill, count);
			}
			
		}
		return jobSkillMap;
	}
	
	
	/**
	 * get a list of jobs filterred by queryTerms
	 * @param queryTerms HashMap<searchKey,searchValue> search terms. 
	 * Keys are defined @Job class, values are filtering value corresponding to each key. 
	 * Relation between different key is ANDing. 
	 * @return a list of jobs filterred by queryTerms
	 */
	public ArrayList<Job> getJobsByQuery(HashMap<String,String> queryTerms){
		if(queryTerms==null||queryTerms.size()==0) {
			return this.getJobs();
		}
		ArrayList<Job> filterredJobs = new ArrayList<>();
		for(Job job:this.jobs) {
			if(this.isQueryCheckPassed(job,queryTerms)) {
				filterredJobs.add(job);
			}
		}
		return filterredJobs;
	}
	
	/**
	 * check whether a job can pass query check by queryTerms
	 * @param job
	 * @param queryTerms
	 * @return true or false
	 */
	public boolean isQueryCheckPassed(Job job, HashMap<String,String> queryTerms) {
		for(String key:queryTerms.keySet()) {
			if(key.equalsIgnoreCase(Job.QUERY_KEY_TITLE)) { //search by title
				WordPair wp = new WordPair(job.getTitle(),queryTerms.get(job.QUERY_KEY_TITLE));
				if (wp.getCommonPercent()<0.7) {
					return false;
				}
			}
				
			if(key.equalsIgnoreCase(Job.QUERY_KEY_LOCATION_STATE)) { //search by state
				if(!job.getLocationState().equalsIgnoreCase(queryTerms.get(job.QUERY_KEY_LOCATION_STATE))) {
					return false;
				}
			}
			
			if(key.equalsIgnoreCase(Job.QUERY_KEY_COMPANY)) { //search by company
				WordPair wp = new WordPair(job.getCompany().getCompanyName(),queryTerms.get(job.QUERY_KEY_COMPANY));
				if (wp.getCommonPercent()<0.7) {
					return false;
				}
			}
			
			if(key.equalsIgnoreCase(Job.QUERY_KEY_SKILLS)) { //search by title
				if(!job.isSkillsMatched(queryTerms.get(job.QUERY_KEY_SKILLS).split(","))) {
					return false;
				}
			}
			
//			double min = Job.SALARY_UNSPECIFIED;
//			double max = Job.SALARY_UNSPECIFIED;
//			if(key.equalsIgnoreCase(Job.QUERY_KEY_SALARY_MIN)) { //search by title
//				min = Double.parseDouble(queryTerms.get(job.QUERY_KEY_SALARY_MIN));
//			}
//
//			if(key.equalsIgnoreCase(Job.QUERY_KEY_SALARY_MAX)) { //search by title
//				max = Double.parseDouble(queryTerms.get(job.QUERY_KEY_SALARY_MAX));
//			}
//			
//			if(min!=Job.SALARY_UNSPECIFIED || max!=Job.SALARY_UNSPECIFIED) {
//				if(!job.isSalaryInRange(min, max)) {
//					return false;
//				}
//			}
//			
		}
		return true;
	}
	
	/**
	 * load data from csv
	 * @param csvFile
	 */
	private void loadJobsFromCSV(String csvFile) {
		CSVReader csvReader = new CSVReader(Config.getPostingFileName());
		JobsCSVHelper helper = new JobsCSVHelper();
		while(csvReader.hasNextRow()) {
			csvReader.moveToNextRow();
			String title = csvReader.getCellValue("Job title");
//			String postDateStr = csvReader.getCellValue("Job Post Date");
//			Date postDate = helper.getJobPostDate(postDateStr);
			String description = csvReader.getCellValue("Job description");
			String companyName = csvReader.getCellValue("Company");
			Company hiringCompany = this.hiringCompanies.get(companyName); 
			
			if(hiringCompany==null) {
				hiringCompany = new Company(companyName);
			}

			String location = csvReader.getCellValue("Primary Location");
			String requiredSkillsStr = csvReader.getCellValue("Required Skills");
			String[] requiredSkills = helper.getSkills(requiredSkillsStr);
			
//			String salaryRangeStr = csvReader.getCellValue("Salary Range");
			
//			double salaryMin = helper.getSalaryMin(salaryRangeStr);
//			double salaryMax = helper.getSalaryMax(salaryRangeStr);
			
//			String benchmarkSalaryStr = csvReader.getCellValue("Benchmark Salary");
//			double benchmarkSalaryMin = helper.getSalaryMin(benchmarkSalaryStr);
//			double benchmarkSalaryMax = helper.getSalaryMax(benchmarkSalaryStr);
//			
//			String benchmarkSkillsStr = csvReader.getCellValue("Benchmark Skills");
//			String[] benchmarkSkills = helper.getSkills(benchmarkSkillsStr);
			
			Job job = new Job(title);
			job.setDescription(description);
			
			job.setCompany(hiringCompany);
			job.setLocation(location);
			job.setRequiredSkills(requiredSkills);
//			job.setSalaryMin(salaryMin);
//			job.setSalaryMax(salaryMax);
			
//			JobBenchmark benchmarkJob = new JobBenchmark(title,benchmarkSalaryMin,benchmarkSalaryMax,benchmarkSkills);
//			job.setBenchmarkJob(benchmarkJob);
			
			this.jobs.add(job);
//			System.out.println("Loading Job from file="+job.toString());
//			hiringCompany.addJob(job);
			this.hiringCompanies.put(companyName, hiringCompany);
		}
	}

	/**
	 * @return the jobs
	 */
	public ArrayList<Job> getJobs() {
		return jobs;
	}

	/**
	 * @return the hiringCompanies
	 */
	public HashMap<String, Company> getHiringCompanies() {
		return hiringCompanies;
	}
	
	
	

}
