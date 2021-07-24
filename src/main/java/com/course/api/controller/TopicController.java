package com.course.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.course.api.domain.Topic;
import com.course.api.service.TopicService;



/**
 * Topic Controller
 * 
 * @author OMKAR
 *
 */
@RestController
public class TopicController {

	@Autowired
	private TopicService courseService;

	@RequestMapping(method = RequestMethod.GET, value = "/topics")
	public ResponseEntity<List<Topic>> getAllTopics() {
		return new ResponseEntity<>(courseService.getAllTopics(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/topics/{id}")
	public ResponseEntity<Topic> getTopic(@PathVariable String id) {
		return new ResponseEntity<>(courseService.getTopic(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public ResponseEntity<String> addTopic(@RequestBody Topic topic) {
		return new ResponseEntity<>(courseService.addTopic(topic), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses/{courseId}")
	public ResponseEntity<String> addCourseToTopic(@PathVariable String topicId, @PathVariable String courseId) {
		return new ResponseEntity<>(courseService.addCourseToTopic(topicId, courseId), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public ResponseEntity<String> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		return new ResponseEntity<>(courseService.updateTopic(topic, id), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "topics/{id}")
	public ResponseEntity<String> removeTopic(@PathVariable String id) {
		return new ResponseEntity<>(courseService.removeTopic(id), HttpStatus.OK);
	}
}
