package green.guemjjoki;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import green.guemjjoki.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void test1() {
		Member member = Member.builder()
				.memberNo("userA")
				.password("1234")
				.gender(Gender.FEMALE)
				.name("테스트")
				.rank(Rank.ROLE_ADMIN)
				.build();	
		
		Member saveMember = memberRepository.save(member);
		Assertions.assertThat(member).isSameAs(saveMember);
	}
	
	
	
}
