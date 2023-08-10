package green.guemjjoki.controller;

import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.service.TodoBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
