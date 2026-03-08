package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo createTodo(String title, String description) {
        Todo todo = new Todo(title, description);
        return repository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return repository.findById(id);
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existing = repository.findById(id);
        if (existing.isPresent()) {
            Todo todo = existing.get();
            if (updatedTodo.getTitle() != null) {
                todo.setTitle(updatedTodo.getTitle());
            }
            if (updatedTodo.getDescription() != null) {
                todo.setDescription(updatedTodo.getDescription());
            }
            todo.setCompleted(updatedTodo.isCompleted());
            return repository.save(todo);
        }
        return null;
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}