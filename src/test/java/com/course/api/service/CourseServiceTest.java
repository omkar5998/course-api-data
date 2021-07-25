package com.course.api.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.course.api.constants.TestConstants;
import com.course.api.domain.Course;
import com.course.api.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

	@Mock
	private CourseRepository courseRepository;

	@Mock
	EntityManager entityManager;

	private CourseService courseService;

	@BeforeEach
	void setup() {
		courseService = new CourseService(courseRepository);
	}

	@AfterEach
	void tearDown() {

	}

	@Test
	@DisplayName("Get All Courses")
	void getAllCourses() {
		given(courseRepository.findAll()).willReturn(TestConstants.getAllCourses());

		List<Course> courses = courseService.getAllCourses();

		assertEquals(TestConstants.getAllCourses().toString(), courses.toString());
	}

	@Test
	@DisplayName("Get Course")
	void getCourse() {

		Optional<Course> optionalCourse = Optional.of(TestConstants.getCourse());
		given(courseRepository.findById(TestConstants.getCourseId())).willReturn(optionalCourse);

		Course course = courseService.getCourse(TestConstants.getCourseId());

		assertEquals(TestConstants.getCourse().toString(), course.toString());
	}

	@Test
	@DisplayName("Add Course")
	void addCourse() {

		when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(i -> i.getArguments()[0]);

		String courseAddedMessage = courseService.addCourse(TestConstants.getCourse());

		assertEquals("Added: " + TestConstants.getCourse().toString(), courseAddedMessage);
	}

	@Test
	@DisplayName("Update Course")
	void updateCourse() {

		Optional<Course> optionalCourse = Optional.of(TestConstants.getCourse());
		given(courseRepository.findById(TestConstants.getCourseId())).willReturn(optionalCourse);
		when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(i -> i.getArguments()[0]);

		String courseUpdatedMessage = courseService.updateCourse(TestConstants.getCourse(),
				TestConstants.getCourseId());

		assertEquals("Updated: " + TestConstants.getCourse().toString(), courseUpdatedMessage);
	}

	@Test
	@DisplayName("Remove Course")
	void removeCourse() {

		String courseDeletedMessage = courseService.removeCourse(TestConstants.getCourseId());
		verify(courseRepository, times(1)).deleteById(TestConstants.getCourseId());
		assertEquals("Removed ", courseDeletedMessage);
	}

}
