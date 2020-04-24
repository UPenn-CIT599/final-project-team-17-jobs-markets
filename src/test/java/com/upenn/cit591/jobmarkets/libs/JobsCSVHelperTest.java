/**
 * 
 */
package com.upenn.cit591.jobmarkets.libs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhongliu
 *
 */
public class JobsCSVHelperTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * check weather JobsCSVHelper can properly parse salary info from given string
	 */
	public void testSalaryParse() {
		JobsCSVHelper helper = new JobsCSVHelper();
		String salaryRange = "100000-128000";
		assertEquals("testing parse salary low number.",100000,helper.getSalaryMin(salaryRange),0);
		assertEquals("testing parse salary high number.",128000,helper.getSalaryMax(salaryRange),0);
	}
	
	
	@Test
	/**
	 * check weather JobsCSVHelper can properly parse skills from given string
	 */
	public void testSkillsParse() {
		JobsCSVHelper helper = new JobsCSVHelper();
		String skills = "skill1; skill2; skill3";
		assertEquals(3,helper.getSkills(skills).length);
	}

}
