package green.guemjjoki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc // 실제 HTTP 요청과 응답을 시뮬레이션하여 컨트롤러의 동작을 테스트할 수 있게 해주는 애노테이션
public class MemberAPIControllerTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    protected MockMvc mockMvc; // mock을 통해 http 요청을 테스트에서 보낼 수 있음

    @Autowired
    protected WebApplicationContext context; // mock 을 세팅해주는 객체

    @Autowired
    protected ObjectMapper objectMapper; // 객체를  JSON 으로 직렬화, JSON을 객체로 역직렬화 매핑해주는 매퍼

    @BeforeEach // 각 단위 테스트가 실행하기 전마다 실행되는 annotation
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); // mock 생성
        memberRepository.deleteAll();
        // 다음 테스트에 영향을 주지 않기 위해 db 전부 삭제
    }

    @Test
    @DisplayName("createMemberList(): member 컨트롤러를 이용해 멤머 목록 확인하기")
    void memberListTest() throws Exception {
        String memberNo = "user1";
    }
    @Test
    @DisplayName("register : member컨트롤러 이용하여 신규회원 등록하기")
    void test01() throws Exception{

    }

}
