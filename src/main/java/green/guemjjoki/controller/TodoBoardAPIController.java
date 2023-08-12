package green.guemjjoki.controller;

import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.dto.TodoListViewDTO;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.service.TodoBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TodoBoardAPIController {

    private final TodoBoardService todoBoardService;

    @PostMapping("/api/todolist")
    public ResponseEntity<TodoBoard> createTodoList(@RequestBody AddTodoListDTO todoListDTO){
        TodoBoard savedTodo = todoBoardService.boardSave(todoListDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTodo);
    }

    @GetMapping("/api/todolist")
    public ResponseEntity<List<TodoListViewDTO>> getTodoList(){
        List<TodoListViewDTO> todoList = todoBoardService.getTodoList().stream().map(TodoListViewDTO::new).toList();
        return ResponseEntity.ok().body(todoList);
    }

}
