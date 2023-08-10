package green.guemjjoki.entitiy;

import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private String member_id;
	
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
	
//	@OneToMany(mappedBy = writer)
//	private TodoBoard todoBoard;
}