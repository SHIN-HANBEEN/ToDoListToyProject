package green.guemjjoki.dto;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import lombok.Getter;

import java.io.Serializable;

@Getter
// 인증객체를 세션에 저장하는 클래스 / MemberEntity를 직렬화하여 저장
public class UserSessionDTO implements Serializable {
    private String memberNo;
    private String password;
    private String email;
    private Gender gender;
    private String name;
    private String address;
    private Rank rank;

    public UserSessionDTO(Member member){
        this.memberNo = member.getMemberNo();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.gender = member.getGender();
        this.name = member.getAddress();
        this.address = member.getAddress();
        this.rank = member.getRank();
    }

}
