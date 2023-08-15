package green.guemjjoki.controller;

import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.dto.ModifyTodoListDTO;
import green.guemjjoki.dto.TodoListDetailViewDTO;
import green.guemjjoki.dto.TodoListViewDTO;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.service.TodoBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/api/todolist/{no}")
    public ResponseEntity<TodoListDetailViewDTO> getDetailView(@PathVariable Long no){
        TodoBoard detailView = todoBoardService.getDetailView(no);
        return ResponseEntity.ok().body(new TodoListDetailViewDTO(detailView));
    }

    @PutMapping(value = "/api/todolist/{no}")
    public ResponseEntity<TodoBoard> updateDetailView(@PathVariable Long no, @RequestBody ModifyTodoListDTO dto){
        TodoBoard todoBoard = todoBoardService.UpdateTodoList(no, dto);
        return ResponseEntity.ok().body(todoBoard);
    }

    @DeleteMapping("/api/todolist/{no}")
    public ResponseEntity deleteTodolistView(@PathVariable Long no){
        todoBoardService.deleteTodolist(no);
        return ResponseEntity.ok().build();
    }



}
