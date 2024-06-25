package com.reactive.student.service;

import com.reactive.student.model.Students;
import com.reactive.student.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    public Flux<Students> findAll() {
        return studentsRepository.findAll();
    }

    public Flux<Students> findBy(Long id, Integer status, String name) {
        return studentsRepository.findBy(id, status, name);
    }

    public Mono<Boolean> updateStatus(Integer id, Integer status) {
        return studentsRepository.findById(id.longValue())
                .switchIfEmpty(Mono.error(new Exception("Student with ID " + id + " not found")))
                .flatMap(foundStudent -> {
                    foundStudent.setStatus(status);
                    return studentsRepository.save(foundStudent);
                })
                .map(student -> Boolean.TRUE)
                .onErrorResume(e -> Mono.just(Boolean.FALSE));
    }
}
