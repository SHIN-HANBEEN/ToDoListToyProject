package green.guemjjoki.service;

import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.repository.TodoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoBoardService {

    private final TodoBoardRepository todoBoardRepository;

    public TodoBoard boardSave(AddTodoListDTO addTodoListDTO){
        return todoBoardRepository.save(addTodoListDTO.toEntity());
    }

    public List<TodoBoard> getTodoList(){
        return todoBoardRepository.findAll();
    }

}
