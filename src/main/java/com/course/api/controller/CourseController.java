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

import com.course.api.domain.Course;
import com.course.api.service.CourseService;

/**
 * Course Controller
 * 
 * @author OMKAR
 *
 */

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(method = RequestMethod.GET, value = "/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/courses/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable String id) {
		return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/courses")
	public ResponseEntity<String> addCourse(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.addCourse(course), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/courses/{id}")
	public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable String id) {
		return new ResponseEntity<>(courseService.updateCourse(course, id), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "courses/{id}")
	public ResponseEntity<String> removeCourse(@PathVariable String id) {
		return new ResponseEntity<>(courseService.removeCourse(id), HttpStatus.OK);
	}
}
