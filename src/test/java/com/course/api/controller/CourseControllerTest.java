package com.course.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.course.api.CourseApiDataApplicationTests;
import com.course.api.constants.TestConstants;
import com.course.api.service.CourseService;
import com.course.api.utils.TestUtil;

@AutoConfigureMockMvc
public class CourseControllerTest extends CourseApiDataApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CourseService courseService;

	@BeforeEach
	void Setup() {

	}

	@Test
	@DisplayName("Get All Courses")
	void getAllCourses() throws Exception {

		when(courseService.getAllCourses()).thenReturn(TestConstants.getAllCourses());

		mvc.perform(get("/courses").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Get Course")
	void getCourse() throws Exception {

		when(courseService.getCourse(TestConstants.getCourseId())).thenReturn(TestConstants.getCourse());

		mvc.perform(get("/courses/" + TestConstants.getCourseId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Add Course")
	void addCourse() throws Exception {

		when(courseService.addCourse(TestConstants.getCourse()))
				.thenReturn("Added: " + TestConstants.getCourse().toString());

		mvc.perform(post("/courses").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getCourse()))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Update Course")
	void updateCourse() throws Exception {

		when(courseService.updateCourse(TestConstants.getCourse(), TestConstants.getCourseId()))
				.thenReturn("Updated: " + TestConstants.getCourse().toString());

		mvc.perform(put("/courses/" + TestConstants.getCourseId()).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getCourse()))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Remove Course")
	void removeCourse() throws Exception {

		when(courseService.removeCourse(TestConstants.getCourseId())).thenReturn("Removed ");

		mvc.perform(delete("/courses/" + TestConstants.getCourseId()).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getCourseId()))).andExpect(status().isOk());
	}

}
