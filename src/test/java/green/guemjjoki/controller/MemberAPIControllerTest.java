package green.guemjjoki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.repository.TodoBoardRepository;
import green.guemjjoki.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc // 실제 HTTP 요청과 응답을 시뮬레이션하여 컨트롤러의 동작을 테스트할 수 있게 해주는 애노테이션
public class MemberAPIControllerTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TodoBoardRepository todoBoardRepository;

    @Autowired
    protected MockMvc mockMvc; // mock을 통해 http 요청을 테스트에서 보낼 수 있음

    @Autowired
    protected WebApplicationContext context; // mock 을 세팅해주는 객체

    @Autowired
    protected ObjectMapper objectMapper; // 객체를  JSON 으로 직렬화, JSON을 객체로 역직렬화 매핑해주는 매퍼

    @BeforeEach //각 단위 테스트가 실행하기전마다 실행되는 annotation
    public void setMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .build();
        //mock 생성
    }
    @AfterEach
    public void deleteRepository(){
        todoBoardRepository.deleteAll();
        memberRepository.deleteAll();
        //다음 테스트에 영향을 주지 않기위해 db 전부 삭제

    }
//    @Test
//    @DisplayName("createMemberList(): member 컨트롤러를 이용해 멤머 목록 확인하기")
//    void memberListTest() throws Exception {
//        String memberNo = "user1";
//    }
    @Test
    @Transactional
    @DisplayName("register : member컨트롤러 이용하여 신규회원 등록하기")
    void test01() throws Exception{
    //given
        String url = "/api/register";  // 요청할 url 경로
        String memberNo = "test";
        String password = "1234";
        Gender gender = Gender.MALE;
        Rank rank = Rank.ROLE_ADMIN;
        final MemberDTO user = MemberDTO.builder().memberNo(memberNo).password(password).gender(gender).rank(rank).build();
        String requestBody = objectMapper.writeValueAsString(user);  // json으로 직렬화
    //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    //then
        result.andExpect(status().isCreated());
    }

}
