package com.example.todo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@SecurityRequirement(name = "bearerAuth")
public class TodoController {

    @Autowired
    private TodoService service;

    // Get all tasks

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TodoDTO>>> getTodos(

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) Boolean completed,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        // Validate page
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        // Validate size
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100"
            );
        }

        // Validate direction
        if (!direction.equalsIgnoreCase("asc")
                && !direction.equalsIgnoreCase("desc")) {

            throw new IllegalArgumentException(
                    "Direction must be 'asc' or 'desc'"
            );
        }

        // Validate sort field
        if (!sortBy.equals("id")
                && !sortBy.equals("task")
                && !sortBy.equals("completed")) {

            throw new IllegalArgumentException(
                    "sortBy must be id, task, or completed"
            );
        }

        Page<TodoDTO> todos = service.getTodos(
                keyword,
                completed,
                page,
                size,
                sortBy,
                direction
        );

        ApiResponse<Page<TodoDTO>> response =
                new ApiResponse<>(
                        true,
                        "Todos fetched successfully",
                        todos
                );

        return ResponseEntity.ok(response);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoDTO>> getTaskById(
            @PathVariable Long id) {

        TodoDTO todo = service.getTaskById(id);

        if (todo != null) {

            ApiResponse<TodoDTO> response =
                    new ApiResponse<>(
                            true,
                            "Todo fetched successfully",
                            todo
                    );

            return ResponseEntity.ok(response);
        }

        ApiResponse<TodoDTO> response =
                new ApiResponse<>(
                        false,
                        "Todo not found",
                        null
                );

        return ResponseEntity.status(404).body(response);
    }

    // Add task
    @PostMapping("/new")
    public ResponseEntity<ApiResponse<TodoDTO>> saveTask(
            @Valid @RequestBody TodoDTO dto) {

        TodoDTO savedTodo = service.saveTask(dto);

        ApiResponse<TodoDTO> response =
                new ApiResponse<>(
                        true,
                        "Todo created successfully",
                        savedTodo
                );

        return ResponseEntity.status(201).body(response);
    }

    // Update task
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TodoDTO>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TodoDTO dto) {

        TodoDTO updatedTodo = service.updateTask(id, dto);

        if (updatedTodo != null) {

            ApiResponse<TodoDTO> response =
                    new ApiResponse<>(
                            true,
                            "Todo updated successfully",
                            updatedTodo
                    );

            return ResponseEntity.ok(response);
        }

        ApiResponse<TodoDTO> response =
                new ApiResponse<>(
                        false,
                        "Todo not found",
                        null
                );

        return ResponseEntity.status(404).body(response);
    }

    // Delete task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(
            @PathVariable Long id) {

        boolean deleted = service.deleteTask(id);

        if (!deleted) {

            ApiResponse<Void> response =
                    new ApiResponse<>(
                            false,
                            "Todo not found",
                            null
                    );

            return ResponseEntity.status(404).body(response);
        }

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Todo deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }


}