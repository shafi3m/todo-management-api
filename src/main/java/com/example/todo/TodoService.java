package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;


    // Get task by ID
    public TodoDTO getTaskById(Long id) {

        User user = getLoggedInUser();

        Todo todo = repository
                .findByIdAndUser(id, user)
                .orElse(null);

        if (todo == null) {
            return null;
        }

        return convertToDTO(todo);
    }


    // Add task
    public TodoDTO saveTask(TodoDTO dto) {

        User user = getLoggedInUser();

        Todo todo = new Todo();

        todo.setTask(dto.getTask());
        todo.setCompleted(dto.isCompleted());
        todo.setUser(user);

        Todo savedTodo = repository.save(todo);

        return convertToDTO(savedTodo);
    }


    // Update task
    public TodoDTO updateTask(Long id, TodoDTO dto) {

        User user = getLoggedInUser();

        Todo existing = repository
                .findByIdAndUser(id, user)
                .orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setTask(dto.getTask());
        existing.setCompleted(dto.isCompleted());

        Todo updatedTodo = repository.save(existing);

        return convertToDTO(updatedTodo);
    }


    // Delete task
    public boolean deleteTask(Long id) {

        User user = getLoggedInUser();

        Todo todo = repository
                .findByIdAndUser(id, user)
                .orElse(null);

        if (todo == null) {
            return false;
        }

        repository.delete(todo);

        return true;
    }


    // Get all tasks
    public Page<TodoDTO> getTodos(
            String keyword,
            Boolean completed,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        User user = getLoggedInUser();

        Page<Todo> todoPage =
                repository.findTodos(
                        user,
                        keyword,
                        completed,
                        pageable
                );

        return todoPage.map(this::convertToDTO);
    }


    // Get currently logged-in user
    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return (User) authentication.getPrincipal();
    }


    // Convert Entity → DTO
    private TodoDTO convertToDTO(Todo todo) {

        TodoDTO dto = new TodoDTO();

        dto.setId(todo.getId());
        dto.setTask(todo.getTask());
        dto.setCompleted(todo.isCompleted());

        return dto;
    }
}