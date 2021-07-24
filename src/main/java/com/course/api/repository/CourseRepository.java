package com.course.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.course.api.domain.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
}
