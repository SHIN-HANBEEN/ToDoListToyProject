package green.guemjjoki.dto;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddTodoListDTO {

    private Long no;
    private Member writer;
    private String title;
    private String content;


    public TodoBoard toEntity(){
        return TodoBoard.builder()
                .todoNo(no)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
    }
}
