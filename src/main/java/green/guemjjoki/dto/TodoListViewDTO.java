package green.guemjjoki.dto;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 글목록을 보여주는 data를 가지고있는 DTO
 */

@Getter
public class TodoListViewDTO {

    private Member writer;
    private String title;
    private LocalDateTime modDate;

    public TodoListViewDTO(TodoBoard todoBoard){
            this.writer = todoBoard.getWriter().;
            this.title = todoBoard.getTitle();
            this.modDate = todoBoard.getModDate();
    }
}
