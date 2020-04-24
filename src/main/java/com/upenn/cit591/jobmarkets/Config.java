package com.upenn.cit591.jobmarkets;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Application Configuration
 * Refer to: https://stackoverflow.com/questions/36463310/how-to-read-a-file-in-aws-lambda-function-written-in-java
 */
public class Config {
//	public static String runtimeLocation = "";
	private String jobsPostingFileName = "JobPostingData.csv";
	private String jobsPostingFileNameWithPath;
	public void Config() {
		this.init();
	}
	
	private void init() {
		ClassLoader classLoader = getClass().getClassLoader();
       this.jobsPostingFileNameWithPath = classLoader.getResource(this.jobsPostingFileName).getFile();
	}
	
	
	
	/**
	 * @return the jobsPostingFileNameWithPath
	 */
	public String getJobsPostingFileNameWithPath() {
		if(this.jobsPostingFileNameWithPath==null) {
			this.init();
		}
		return this.jobsPostingFileNameWithPath;
	}

	/**
	 * 
	 * @return configuration file path
	 */
	public static String getPostingFileName() {
//		return "/Users/zhongliu/code/jobmarkets/jobmarkets/src/main/resources/JobPostingData.csv";
		Config config = new Config();
		return config.getJobsPostingFileNameWithPath();
	}

}
