package com.course.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.course.api.domain.Topic;

public interface TopicRepository extends CrudRepository<Topic, String>{

}
