package green.guemjjoki.entitiy;

import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member{
	
	@Id
	@Column(length=15)
	private String memberId;
	
	@Column(length=20,nullable = false)
	private String password;
	
	@Column(length=40)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;  // 성별 구분 Enum 사용
	
	@Column(length=20,nullable = false)
	private String name;	
	
	@Column(length=100)
	private String address;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)  // 회원 및 관리자 구분 Enum 사용
	private Rank rank;
	
	@OneToMany(mappedBy = "writer")
	private List<TodoBoard> todoBoard;
	//oneToMany 1 : N 관게니까 게시물 글은 여러개라 List Type 이여야겠죠~
}