package green.guemjjoki.repository;

import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("test")
    @Transactional
    @Rollback(value = false)
    void name () throws Exception{
        //given
        Optional<Member> byId = memberRepository.findById("testUser");
        Member member = byId.get();
        System.out.println(member.getMemberNo());
    }



}