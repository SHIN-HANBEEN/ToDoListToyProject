package green.guemjjoki.entitiy;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class TodoBoard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_no")
    private Long todoNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(length = 20 , nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

    @Builder
    public TodoBoard(Long todoNo, String title, String content) {
        this.todoNo = todoNo;
        this.title = title;
        this.content = content;
    }

    /**
     * 연관관계 편의 메서드 (Member에도 todoBoard에 member추가시, todoBoard 객체를 추가해줌.)
     * @param writer
     */
//    public void setWriter(Member writer) {
//        this.writer = writer;
//        writer.getTodoBoard().add(this);
//    }

    //1차 캐시를 비교해 값이 변경되면 영속성 컨텍스트에서 변경감지를해 플러시할 때 Upate 쿼리가 날라감~
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
