package green.guemjjoki.dto;

import green.guemjjoki.entitiy.TodoBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodolistDetailResponseDTO {
    private Long no;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public TodolistDetailResponseDTO(TodoBoard todoBoard) {
        this.no = todoBoard.getTodoNo();
        this.title = todoBoard.getTitle();
        this.content = todoBoard.getContent();
        this.regDate = todoBoard.getRegDate();
        this.modDate = todoBoard.getModDate();
    }
}
