package com.course.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.api.domain.Course;
import com.course.api.domain.Topic;
import com.course.api.repository.CourseRepository;
import com.course.api.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	CourseRepository courseRepository;

	public List<Topic> getAllTopics() {

		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent()) {
			return topic.get();
		}
		return null;
	}

	public String addTopic(Topic topic) {
		topicRepository.save(topic);
		return "Added: " + topic;
	}

	public String addCourseToTopic(String topicId, String courseId) {

		Optional<Topic> optionalTopic = topicRepository.findById(topicId);
		if (optionalTopic.isPresent()) {
			Optional<Course> optionalCourse = courseRepository.findById(courseId);
			if (optionalCourse.isPresent()) {
				Topic topic = optionalTopic.get();
				topic.setCourse(optionalCourse.get());
				topicRepository.save(topic);
				return "Added: " + topic.getCourse();
			}
			return "No Course Present";
		}
		return "No Topic Present";
	}

	public String updateTopic(Topic topic, String id) {

		if (topicRepository.findById(id).isPresent()) {
			topicRepository.save(topic);
			return "Updated: " + topic;
		}
		return "No Topics found to Update";
	}

	public String removeTopic(String id) {
		topicRepository.deleteById(id);
		return "Removed ";
	}

}
