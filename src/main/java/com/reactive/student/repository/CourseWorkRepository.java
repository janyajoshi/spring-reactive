package com.reactive.student.repository;

import com.reactive.student.model.CourseWork;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CourseWorkRepository extends ReactiveCrudRepository<CourseWork, Long> {
    Mono<Void> deleteByStudentID(Long studentID);
}
