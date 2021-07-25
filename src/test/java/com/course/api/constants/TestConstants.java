package com.course.api.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.course.api.domain.Course;
import com.course.api.domain.Topic;

public class TestConstants {

	public static List<Course> getAllCourses() {
		Course course = new Course("1", "Telusko Learning", "Java Course");
		return new ArrayList<>(Arrays.asList(course));
	}

	public static String getCourseId() {
		return "1";
	}

	public static Course getCourse() {
		return new Course("1", "Telusko Learning", "Java Course");
	}

	public static List<Topic> getAllTopics() {
		Topic topic = new Topic("1", "Java", "Java Learning");
		return new ArrayList<>(Arrays.asList(topic));
	}

	public static Topic getTopic() {
		return new Topic("1", "Java", "Java Learning");
	}

	public static String getTopicId() {
		return "1";
	}
	
	public static Topic getTopicWithCourse() {
		
		Topic topic = new Topic("1", "Java", "Java Learning");
		topic.setCourse(getCourse());
		return topic;
	}
}
