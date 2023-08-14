package green.guemjjoki.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member{
	
	@Id
	@Column(length=15)
	private String memberNo;
	
	@Column(nullable = false, length = 200)
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
	private List<TodoBoard> todoBoard = new ArrayList<>();



	@Builder
	public Member(String memberNo, String password, String email, Gender gender, String name, String address, Rank rank) {
		this.memberNo = memberNo;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.name = name;
		this.address = address;
		this.rank = rank;
	}
}