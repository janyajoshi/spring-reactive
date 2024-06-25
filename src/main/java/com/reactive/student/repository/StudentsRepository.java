package com.reactive.student.repository;

import com.reactive.student.model.Students;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentsRepository extends ReactiveCrudRepository<Students, Long> {
    @Query(value = """
            SELECT * FROM students
            WHERE
            (`status`=:status OR :status is null) AND
            (`name` LIKE :name OR :name is null)
            LIMIT :limit OFFSET :offset
            """)
    Flux<Students> findAllByStatusAndName(Long offset, Long limit, String status, String name);

    @Query(value = """
            SELECT * FROM students
            WHERE (:id is null or id = :id)
            AND (:status is null or status = :status)
            AND (:name is null or name like CONCAT('%', :name, '%'))
            """)
    Flux<Students> findBy(
            @Param("id") Long id,
            @Param("status") Integer status,
            @Param("name") String name);
}
