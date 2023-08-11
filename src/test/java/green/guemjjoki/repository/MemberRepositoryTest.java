package green.guemjjoki.repository;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("test")
    @Rollback(value = false)
    void name () throws Exception{
        //given
        Member user = Member.builder()
                .name("a")
                .memberId("a")
                .email("aa")
                .address("a")
                .rank(Rank.ROLE_USER)
                .gender(Gender.MALE)
                .password("a")
                .build();

        //when
        memberRepository.save(user);

        //then
    }



}