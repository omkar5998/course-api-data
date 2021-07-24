package com.course.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Topic {

	@Id
	private String topicId;
	private String topicName;
	private String topicDesc;
	
	@ManyToOne
	private Course course;

	public Topic() {

	}

	public Topic(String topicId, String topicName, String topicDesc) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.topicDesc = topicDesc;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDesc=" + topicDesc + "]";
	}

}
