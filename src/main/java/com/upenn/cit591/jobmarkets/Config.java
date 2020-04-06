package com.upenn.cit591.jobmarkets;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Application Configuration
 */
public class Config {
	public static String runtimeLocation = "";
	public static String jobsDataFile;

	public Config() {
		Config.init();
	}
	
	/**
	 * initialize configuration of this application. 
	 */
	public static void init() {
//		URL location = Config.class.getProtectionDomain().getCodeSource().getLocation();
//		Config.runtimeLocation = location.getFile().replaceAll("%20", " ");
//		Config.jobsDataFile = Config.runtimeLocation+"data/jobs data.csv";
//		Config.jobsDataFile = Config.runtimeLocation+"./jobs data.csv";
		try {
			Config.jobsDataFile = Config.getPath("jobs data.csv");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("jobs data file=" + Config.jobsDataFile);
	}

	private static String getPath(String fileName) throws UnsupportedEncodingException {

		String path = Config.class.getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");

		fullPath = pathArr[0];

		String reponsePath = "";

		// to read a file from webcontent

		reponsePath = new File(fullPath).getPath() + File.separatorChar + fileName;

		return reponsePath;

	}

}
