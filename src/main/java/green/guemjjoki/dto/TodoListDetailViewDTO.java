package green.guemjjoki.dto;

import green.guemjjoki.entitiy.TodoBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoListDetailViewDTO {

    private Long no;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public TodoListDetailViewDTO(TodoBoard todoBoard) {
        this.no = todoBoard.getTodoNo();
        this.writer = todoBoard.getWriter().getMemberNo();
        this.title = todoBoard.getTitle();
        this.content = todoBoard.getContent();
        this.regDate = todoBoard.getRegDate();
        this.modDate = todoBoard.getModDate();
    }
}
