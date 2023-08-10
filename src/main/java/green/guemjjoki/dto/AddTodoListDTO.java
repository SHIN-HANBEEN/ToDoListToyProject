package green.guemjjoki.dto;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddTodoListDTO {

    private Member writer;
    private String title;
    private String content;

    public TodoBoard toEntity(){
        return TodoBoard.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();
    }
}
