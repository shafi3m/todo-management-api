package com.example.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    private Long id;

    @NotBlank(message = "Task cannot be empty")
    @Size(min = 3, max = 100, message = "Task must be between 3 and 100 characters")
    private String task;

    private boolean completed;
}