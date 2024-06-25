package com.reactive.student.controller;

import com.reactive.student.model.GeneralResponse;
import com.reactive.student.model.Students;
import com.reactive.student.repository.CourseWorkRepository;
import com.reactive.student.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("students")
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private CourseWorkRepository courseWorkRepository;

    @GetMapping("/byId/{studentID}")
    public Mono<ResponseEntity<Students>> getStudent(@PathVariable Long studentID) {

        return studentsRepository.findById(studentID).map(student -> new ResponseEntity<>(student, HttpStatus.OK));
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<Students>> addStudent(@RequestBody Students newStudent) {
        newStudent.setRegisteredOn(System.currentTimeMillis());
        newStudent.setStatus(1);
        return studentsRepository.save(newStudent).map(student -> new ResponseEntity<>(student, HttpStatus.CREATED));
    }

    @PutMapping("/update/{id}")
    Mono<ResponseEntity<GeneralResponse<Students>>> updateStudent(@PathVariable Long id, @RequestBody Students newStudentData) {
        return studentsRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Student with ID " + id + " not found")))
                .flatMap(foundStudent -> {
                    foundStudent.setName(newStudentData.getName());
                    return studentsRepository.save(foundStudent);
                }).map(student -> {
                    HashMap<String, Students> data = new HashMap<>();
                    data.put("student", student);
                    return new ResponseEntity<>(
                            GeneralResponse.<Students>builder()
                                    .success(true)
                                    .message("Student updated successfully")
                                    .data(data)
                                    .build(),
                            HttpStatus.ACCEPTED
                    );
                }).onErrorResume(e -> Mono.just(
                        new ResponseEntity<>(
                                GeneralResponse.<Students>builder().success(false).message(e.getMessage()).build(),
                                HttpStatus.NOT_FOUND)));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    Mono<ResponseEntity<GeneralResponse<Students>>> deleteStudent(@PathVariable Long id) {
        return studentsRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception(String.format("Student with ID %d not found", id))))
                .flatMap(foundStudent -> courseWorkRepository.deleteByStudentID(id)
                        .then(studentsRepository.deleteById(id))
                        .thenReturn(foundStudent))
                .map(deletedStudent -> new ResponseEntity<>(
                        GeneralResponse.<Students>builder()
                                .success(true)
                                .message("Student deleted successfully")
                                .data(Collections.singletonMap("student", deletedStudent))
                                .build(),
                        HttpStatus.ACCEPTED
                ))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(
                        GeneralResponse.<Students>builder()
                                .success(false)
                                .message(e.getMessage())
                                .build(),
                        HttpStatus.NOT_FOUND)));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_NDJSON_VALUE)
    Flux<Students> getStudents(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Long limit,
            @RequestParam Map<String, String> filterParams
    ) {
        String status = filterParams.getOrDefault("status", null);
        String name = filterParams.getOrDefault("name", null);
        if (name != null) {
            name = "%" + name + "%";
        }
        long offset = (page - 1) * limit;
        return studentsRepository.findAllByStatusAndName(offset, limit, status, name)
                .delayElements(Duration.ofMillis(100L));
    }
}
