package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String sortBy) {

        List<Todo> todos = todoService.getAllTodos();

        // Filter by completed status if requested
        if (completed != null) {
            todos = todos.stream()
                    .filter(todo -> todo.isCompleted() == completed)
                    .toList();
        }

        // Sort if requested
        if ("title".equals(sortBy)) {
            todos = todos.stream()
                    .sorted((a, b) -> a.getTitle().compareTo(b.getTitle()))
                    .toList();
        } else if ("id".equals(sortBy)) {
            todos = todos.stream()
                    .sorted((a, b) -> a.getId().compareTo(b.getId()))
                    .toList();
        }

        return todos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoRequest request) {
        Todo todo = todoService.createTodo(request.getTitle(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo todo = todoService.updateTodo(id, updatedTodo);
        if (todo != null) {
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}