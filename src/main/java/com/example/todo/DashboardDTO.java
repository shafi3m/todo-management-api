package com.example.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private long totalUsers;
    private long totalTodos;
    private long completedTodos;
    private long pendingTodos;
}