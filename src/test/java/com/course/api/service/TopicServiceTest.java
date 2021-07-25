package com.course.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.api.constants.TestConstants;
import com.course.api.domain.Topic;
import com.course.api.repository.CourseRepository;
import com.course.api.repository.TopicRepository;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

	@Mock
	private TopicRepository topicRepository;

	@Mock
	private CourseRepository courseRepository;

	@Mock
	EntityManager entityManager;

	private TopicService topicService;

	@BeforeEach
	void setup() {
		topicService = new TopicService(topicRepository, courseRepository);
	}

	@AfterEach
	void tearDown() {

	}

	@Test
	@DisplayName("Get All Topics")
	void getAllTopics() {
		given(topicRepository.findAll()).willReturn(TestConstants.getAllTopics());

		List<Topic> courses = topicService.getAllTopics();

		assertEquals(TestConstants.getAllTopics().toString(), courses.toString());
	}

	@Test
	@DisplayName("Get Topic")
	void getTopic() {

		Optional<Topic> optionalTopic = Optional.of(TestConstants.getTopic());
		given(topicRepository.findById(TestConstants.getTopicId())).willReturn(optionalTopic);

		Topic topic = topicService.getTopic(TestConstants.getTopicId());

		assertEquals(TestConstants.getTopic().toString(), topic.toString());
	}

	@Test
	@DisplayName("Add Topic")
	void addTopic() {

		when(topicRepository.save(Mockito.any(Topic.class))).thenAnswer(i -> i.getArguments()[0]);

		String topicAddedMessage = topicService.addTopic(TestConstants.getTopic());

		assertEquals("Added: " + TestConstants.getTopic().toString(), topicAddedMessage);
	}

	@Test
	@DisplayName("Add Course to Topic")
	void addCourseToTopic() {

		String courseAddedMessage = topicService.addTopic(TestConstants.getTopic());

		assertEquals("Added: " + TestConstants.getTopicWithCourse().toString(), courseAddedMessage);
	}

	@Test
	@DisplayName("Update Topic")
	void updateTopic() {

		Optional<Topic> optionalCourse = Optional.of(TestConstants.getTopic());
		given(topicRepository.findById(TestConstants.getTopicId())).willReturn(optionalCourse);
		when(topicRepository.save(Mockito.any(Topic.class))).thenAnswer(i -> i.getArguments()[0]);

		String topicUpdatedMessage = topicService.updateTopic(TestConstants.getTopic(), TestConstants.getTopicId());

		assertEquals("Updated: " + TestConstants.getTopic().toString(), topicUpdatedMessage);
	}

	@Test
	@DisplayName("Remove Topic")
	void removeTopic() {

		String topicDeletedMessage = topicService.removeTopic(TestConstants.getTopicId());
		verify(topicRepository, times(1)).deleteById(TestConstants.getTopicId());
		assertEquals("Removed ", topicDeletedMessage);
	}

}
