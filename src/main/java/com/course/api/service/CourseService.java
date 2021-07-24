package com.course.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.api.domain.Course;
import com.course.api.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses() {

		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	public Course getCourse(String id) {
		Optional<Course> course = courseRepository.findById(id);
		if (course.isPresent()) {
			return course.get();
		}
		return null;
	}

	public String addCourse(Course course) {
		courseRepository.save(course);
		return "/Added: " + course;
	}

	public String updateCourse(Course course, String id) {

		if (courseRepository.findById(id).isPresent()) {
			courseRepository.save(course);
			return "Updated: " + course;
		}
		return "No Courses found to Update";
	}

	public String removeCourse(String id) {
		courseRepository.deleteById(id);
		return "Removed ";
	}

}
