package com.upenn.cit591.jobmarkets.domain;

import java.util.Date;

public class Job {
	private String title;
	private Date postDate;
	private String description;
	
	public Job(String title,Date postDate) {
		this.title = title;
		this.postDate = postDate;
	}

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
	public Date getPostDate() {
		return postDate;
	}
	
	

}
