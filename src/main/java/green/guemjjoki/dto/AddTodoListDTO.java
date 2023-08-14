package green.guemjjoki.dto;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.repository.TodoBoardRepository;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
public class AddTodoListDTO {


    private Long no;
    private String title;
    private String content;

    public TodoBoard toEntity(){
        return TodoBoard.builder()
                .todoNo(no)
                .title(title)
                .content(content)
                .build();
    }
}
