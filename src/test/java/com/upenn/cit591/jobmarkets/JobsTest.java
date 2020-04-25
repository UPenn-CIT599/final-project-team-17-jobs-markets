package com.upenn.cit591.jobmarkets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.upenn.cit591.jobmarkets.domain.Company;
import com.upenn.cit591.jobmarkets.domain.Job;
import com.upenn.cit591.jobmarkets.domain.Jobs;

public class JobsTest {
	
	private Jobs jobs = null;

	@Before
	public void setUp() throws Exception {
		ArrayList<Job> jobsArray = new ArrayList<>();
    	Job job1 = new Job("Software Engineer");
    	job1.setCompany(new Company("hiring company 1"));
    	job1.setRequiredSkills(new String[]{"java", "c++", "communication"});
    	job1.setLocation("NYC|NY");
    	job1.setDescription("this is job description");
//    	job1.setSalaryMin(110000);
//    	job1.setSalaryMax(1500000);
    	

    	Job job2 = new Job("Sr. Software Engineer");
    	job2.setCompany(new Company("hiring company 2"));
    	job2.setRequiredSkills(new String[]{"software engineering", "machine learning"});
    	job2.setLocation("Miami | FL");
    	job2.setDescription("this is job description");
    	
    	Job job3 = new Job("Test Engineer");
    	job3.setCompany(new Company("hiring company 3"));
    	job3.setRequiredSkills(new String[]{"java", "machine learning", "c#"});
    	job3.setLocation("Brooklyn | NY");
    	job3.setDescription("this is job description");
    	
    	jobsArray.add(job1);
    	jobsArray.add(job2);
    	jobsArray.add(job3);
    	
    	this.jobs = new Jobs(jobsArray);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	/**
	 * test # of skills from testing data
	 */
	public void testTotalSkills() {
		assertEquals(6, this.jobs.jobCountBySkills().size());
	}
	
	
	@Test
	/**
	 * test # of states from testing data
	 */
	public void testTotalStates() {
		assertEquals(2, this.jobs.jobCountByStates().size());
	}
	
	
	@Test
	/**
	 * test # of companies from testing data
	 */
	public void testTotalCompanies() {
		assertEquals(3, this.jobs.jobCountByCompany().size());
	}

	@Test
	/**
	 * test # of jobs is correct
	 */
	public void testAllJobsCount() {
		assertEquals(3, this.jobs.getJobs().size());
	}
	
	@Test
	/**
	 * test job search by title
	 */
	public void testSearchByTitle() {
		HashMap<String, String> query = new HashMap<>();
		query.put(Job.QUERY_KEY_TITLE, "Test Engineer");
		//only one Test Engineer
		assertEquals(1, this.jobs.getJobsByQuery(query).size());
	}
	
	@Test
	/**
	 * test job search by title (matching by similarity)
	 */
	public void testSearchBySimilarTitle() {
		HashMap<String, String> query = new HashMap<>();
		query.put(Job.QUERY_KEY_TITLE, "Software Engineer");
		//should return 2, including 1 "Software Engineer" and 1 "Sr. Software Engineer"
		assertEquals(2, this.jobs.getJobsByQuery(query).size());
	}
	
	@Test
	/**
	 * test job search by title (matching by similarity)
	 */
	public void testSearchByState() {
		HashMap<String, String> query = new HashMap<>();
		query.put(Job.QUERY_KEY_LOCATION_STATE, "NY");
		//2 jobs from NY state
		assertEquals(2, this.jobs.getJobsByQuery(query).size());
	}
	
	
//	@Test
	/**
	 * test job search by title (matching by similarity)
	 */
	public void testSearchByCompany() {
		HashMap<String, String> query = new HashMap<>();
		query.put(Job.QUERY_KEY_COMPANY, "hiring company 1");
		//1 job1 from the hiring company 1
		assertEquals(1, this.jobs.getJobsByQuery(query).size());
	}

}
