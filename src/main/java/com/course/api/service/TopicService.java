package com.course.api.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course.api.domain.Topic;
import com.course.api.repository.CourseRepository;
import com.course.api.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	CourseRepository courseRepository;

	public TopicService(TopicRepository topicRepository, CourseRepository courseRepository) {
		super();
		this.topicRepository = topicRepository;
		this.courseRepository = courseRepository;
	}

	public List<Topic> getAllTopics() {

		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		return topicRepository.findById(id).get();

	}

	public String addTopic(Topic topic) {
		topicRepository.save(topic);
		return "Added: " + topic;
	}

	public String addCourseToTopic(String topicId, String courseId) {

		Topic topic = topicRepository.findById(topicId).get();
		topic.setCourse(courseRepository.findById(courseId).get());
		topicRepository.save(topic);
		return "Added: " + topic.getCourse();
	}

	public String updateTopic(Topic topic, String id) {

		if (topicRepository.findById(id).get().getTopicId() == topic.getTopicId()) {
			topicRepository.save(topic);
		}
		return "Updated: " + topic;
	}

	public String removeTopic(String id) {
		topicRepository.deleteById(id);
		return "Removed ";
	}

}
