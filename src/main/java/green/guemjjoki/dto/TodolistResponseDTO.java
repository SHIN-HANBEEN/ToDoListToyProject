package green.guemjjoki.dto;

import green.guemjjoki.entitiy.TodoBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
public class TodolistResponseDTO {

    private Long no;
    private String title;

    public TodolistResponseDTO(TodoBoard todoBoard) {
        this.no = todoBoard.getTodoNo();
        this.title = todoBoard.getTitle();
    }
}
