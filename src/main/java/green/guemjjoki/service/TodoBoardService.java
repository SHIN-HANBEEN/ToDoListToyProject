package green.guemjjoki.service;

import green.guemjjoki.dto.AddTodoListDTO;

import green.guemjjoki.dto.ModifyTodoListDTO;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.repository.TodoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

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

    public TodoBoard getDetailView(Long no){
        return todoBoardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException(" 잘못된 게시글 번호입니다. notFound : " + no));
    }
    @Transactional
    public TodoBoard UpdateTodoList(Long no, ModifyTodoListDTO dto){
        TodoBoard todoBoard = todoBoardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 번호입니다. notFound : " + no));

        todoBoard.update(dto.getTitle(), dto.getContent());
        return todoBoard;
    }

    @Transactional
    public void deleteTodolist(Long no){
        TodoBoard todoBoard = todoBoardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 번호입니다. notFound : "));
        todoBoardRepository.delete(todoBoard);
    }

}
