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
import com.course.api.service.TopicService;
import com.course.api.utils.TestUtil;

@AutoConfigureMockMvc
public class TopicControllerTest extends CourseApiDataApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TopicService topicService;

	@BeforeEach
	void Setup() {

	}

	@Test
	@DisplayName("Get All Topics")
	void getAllTopics() throws Exception {

		when(topicService.getAllTopics()).thenReturn(TestConstants.getAllTopics());

		mvc.perform(get("/topics").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Get Topic")
	void getTopic() throws Exception {

		when(topicService.getTopic(TestConstants.getTopicId())).thenReturn(TestConstants.getTopic());

		mvc.perform(get("/topics/" + TestConstants.getTopicId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Add Topic")
	void addTopic() throws Exception {

		when(topicService.addTopic(TestConstants.getTopic()))
				.thenReturn("Added: " + TestConstants.getTopic().toString());

		mvc.perform(post("/topics").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getTopic()))).andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Add Course To Topic")
	void addCourseToTopic() throws Exception {

		when(topicService.addCourseToTopic( TestConstants.getCourseId(), TestConstants.getCourseId()))
				.thenReturn("Added: " + TestConstants.getCourse().toString());

		mvc.perform(post("/topics/{topicId}/courses/{courseId}","1","1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Update Topic")
	void updateTopic() throws Exception {

		when(topicService.updateTopic(TestConstants.getTopic(), TestConstants.getTopicId()))
				.thenReturn("Updated: " + TestConstants.getTopic().toString());

		mvc.perform(put("/topics/" + TestConstants.getTopicId()).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getCourse()))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Remove Topic")
	void removeTopic() throws Exception {

		when(topicService.removeTopic(TestConstants.getTopicId())).thenReturn("Removed ");

		mvc.perform(delete("/topics/" + TestConstants.getCourseId()).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(TestConstants.getTopicId()))).andExpect(status().isOk());
	}

}
