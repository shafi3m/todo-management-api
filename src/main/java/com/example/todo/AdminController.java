package com.example.todo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {

        List<UserDTO> users = service.getAllUsers();

        ApiResponse<List<UserDTO>> response =
                new ApiResponse<>(
                        true,
                        "Users fetched successfully",
                        users
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<List<TodoDTO>>> getAllTodos() {

        List<TodoDTO> todos = service.getAllTodos();

        ApiResponse<List<TodoDTO>> response =
                new ApiResponse<>(
                        true,
                        "All todos fetched successfully",
                        todos
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTodo(
            @PathVariable Long id) {

        boolean deleted = service.deleteTodo(id);

        if (!deleted) {

            ApiResponse<Void> response =
                    new ApiResponse<>(
                            false,
                            "Todo not found",
                            null
                    );

            return ResponseEntity
                    .status(404)
                    .body(response);
        }

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Todo deleted successfully by admin",
                        null
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<DashboardDTO>> getDashboard() {

        DashboardDTO dashboard =
                service.getDashboard();

        ApiResponse<DashboardDTO> response =
                new ApiResponse<>(
                        true,
                        "Dashboard data fetched successfully",
                        dashboard
                );

        return ResponseEntity.ok(response);
    }
}