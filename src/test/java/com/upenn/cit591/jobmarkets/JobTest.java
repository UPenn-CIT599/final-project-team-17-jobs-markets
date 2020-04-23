package com.upenn.cit591.jobmarkets;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.upenn.cit591.jobmarkets.domain.Job;;

public class JobTest {
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLocationCity() {
		Job job = new Job("Job Title");
		job.setLocation("Miami | FL");
		
		assertEquals("MIAMI", job.getLocationCity());
	}
	
	@Test
	public void testLocationState() {
		Job job = new Job("Job Title");
		job.setLocation("Miami | FL");
		
		assertEquals("FL", job.getLocationState());
	}

}
