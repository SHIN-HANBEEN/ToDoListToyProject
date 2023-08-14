package green.guemjjoki.dto;

import green.guemjjoki.entitiy.TodoBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyTodoListDTO {

    private String title;
    private String content;
    public ModifyTodoListDTO(TodoBoard todoBoard){
        this.title = todoBoard.getTitle();
        this.content = todoBoard.getContent();
    }
}
