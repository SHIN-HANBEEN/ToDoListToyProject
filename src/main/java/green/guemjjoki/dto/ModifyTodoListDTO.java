package green.guemjjoki.dto;

import green.guemjjoki.entitiy.TodoBoard;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ModifyTodoListDTO {

    private String title;
    private String content;

}
