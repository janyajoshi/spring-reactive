package com.reactive.student.controller;

import com.reactive.student.model.Students;
import com.reactive.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class GraphQlController {

    @Autowired
    private StudentService studentService;

    @QueryMapping
    public Flux<Students> getStudents() {
        return studentService.findAll();
    }

    @QueryMapping
    public Flux<Students> getStudentBy(
            @Argument(name = "id") Long id,
            @Argument(name = "status") Integer status,
            @Argument(name = "name") String name) {
        return studentService.findBy(id, status, name);
    }

    @MutationMapping
    public Mono<Boolean> updateStatus(
            @Argument(name = "id") Integer id,
            @Argument(name = "status") Integer status) {
        return studentService.updateStatus(id, status);
    }
}
