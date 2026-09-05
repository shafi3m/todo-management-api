package com.example.todo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final UserRepository repository;
    private final TodoRepository todoRepository;

    public AdminService(
            UserRepository repository,
            TodoRepository todoRepository) {

        this.repository = repository;
        this.todoRepository = todoRepository;
    }

    public List<UserDTO> getAllUsers() {

        List<User> users = repository.findAll();
        List<UserDTO> response = new ArrayList<>();

        for (User user : users) {

            UserDTO dto = new UserDTO();

            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());

            response.add(dto);
        }

        return response;
    }

    public List<TodoDTO> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();
        List<TodoDTO> response = new ArrayList<>();

        for (Todo todo : todos) {
            response.add(convertToDTO(todo));
        }

        return response;
    }

    public boolean deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElse(null);

        if (todo == null) {
            return false;
        }

        todoRepository.delete(todo);

        return true;
    }

    @Transactional
    public void deleteUser(Long userId) {

        User currentUser = getLoggedInUser();

        if (currentUser.getId().equals(userId)) {
            throw new CannotDeleteOwnAccountException(
                    "You cannot delete your own admin account"
            );
        }

        User user = repository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        todoRepository.deleteByUser(user);

        repository.delete(user);
    }

    public DashboardDTO getDashboard() {

        long totalUsers = repository.count();
        long totalTodos = todoRepository.count();
        long completedTodos =
                todoRepository.countByCompleted(true);
        long pendingTodos =
                todoRepository.countByCompleted(false);

        return new DashboardDTO(
                totalUsers,
                totalTodos,
                completedTodos,
                pendingTodos
        );
    }

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return (User) authentication.getPrincipal();
    }

    private TodoDTO convertToDTO(Todo todo) {

        TodoDTO dto = new TodoDTO();

        dto.setId(todo.getId());
        dto.setTask(todo.getTask());
        dto.setCompleted(todo.isCompleted());

        return dto;
    }
}