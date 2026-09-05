package com.example.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("""
           SELECT t FROM Todo t
           WHERE t.user = :user
           AND (:keyword IS NULL OR
                LOWER(t.task) LIKE LOWER(CONCAT('%', :keyword, '%')))
           AND (:completed IS NULL OR t.completed = :completed)
           """)
    Page<Todo> findTodos(
            @Param("user") User user,
            @Param("keyword") String keyword,
            @Param("completed") Boolean completed,
            Pageable pageable
    );

    Optional<Todo> findByIdAndUser(Long id, User user);

    long countByCompleted(boolean completed);

    void deleteByUser(User user);
}