package com.upenn.cit591.jobmarkets;

import static org.junit.Assert.*;

import org.junit.Test;

import com.upenn.cit591.jobmarkets.domain.Job;

public class JobTest {
	
	@Test
	/**
	 * make sure correct city can be returned from location field. 
	 */
	public void testLocationCity() {
		Job job = new Job("Job Title");
		job.setLocation("Miami | FL");
		
		assertEquals("MIAMI", job.getLocationCity());
	}
	
	@Test
	/**
	 * make sure correct state can be returned from location field. 
	 */
	public void testLocationState() {
		Job job = new Job("Job Title");
		job.setLocation("Miami | FL");
		
		assertEquals("FL", job.getLocationState());
	}
	
	@Test
	/**
	 * skill matching -  one hit 
	 */
	public void testSkillMatchingHit() {
		Job job = new Job("Job Title");
		job.setRequiredSkills(new String[]{"java", "machine learning", "c#"});
		assertEquals(true, job.isSkillsMatched(new String[] {"java"}));
	}
	
	@Test
	/**
	 * skill matching -  not hit 
	 */
	public void testSkillMatchingNotHit() {
		Job job = new Job("Job Title");
		job.setRequiredSkills(new String[]{"java", "machine learning", "c#"});
		assertEquals(false, job.isSkillsMatched(new String[] {"python"}));
	}
	
	@Test
	/**
	 * skill matching -  multiple skills  input 
	 */
	public void testSkillMatchingMultipleInput() {
		Job job = new Job("Job Title");
		job.setRequiredSkills(new String[]{"java", "machine learning", "c#"});
		assertEquals(true, job.isSkillsMatched(new String[] {"python","java"}));
	}

}
