package green.guemjjoki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.entitiy.entityEnum.Gender;
import green.guemjjoki.entitiy.entityEnum.Rank;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.repository.TodoBoardRepository;
import green.guemjjoki.service.TodoBoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc //실제 HTTP 요청과 응답을 시뮬레이션하여 컨트롤러의 동작을 테스트할 수 있게 해주는 annotation
class TodoBoardAPIControllerTest {

    @Autowired
    TodoBoardService todoBoardService;
    @Autowired
    TodoBoardRepository todoBoardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    protected MockMvc mockMvc; //mock을 통해 http요청을 테스트에서 보낼 수 있음.
    @Autowired
    protected WebApplicationContext context; //mock을 세팅해주는 객체
    @Autowired
    protected ObjectMapper objectMapper; //객체를 JSON으로 직렬화, JSON을 객체로 역직렬화 매핑해주는 매퍼

    @BeforeEach //각 단위 테스트가 실행하기전마다 실행되는 annotation
    public void setMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //mock 생성




    }
    @AfterEach
    public void deleteRepository(){
        todoBoardRepository.deleteAll();
        memberRepository.deleteAll();
        //다음 테스트에 영향을 주지 않기위해 db 전부 삭제

    }

    @Test
    @DisplayName("createTodoList(): todoList 컨트롤러을 이용해  글 생성에 성공하기")
    void test1() throws Exception{
        //given
        String userId = "userA";
        String userName = "kim";
        String userPw = "1234";
        String userEmail = "abc@naver.com";
        Member userA = Member.builder()
                .memberNo(userId)
                .name(userName)
                .password(userPw)
                .email(userEmail)
                .gender(Gender.MALE)
                .rank(Rank.ROLE_USER)
                .build();
        memberRepository.save(userA);

        String url = "/api/todolist";
        String title = "abc";
        String content = "test1";
        final AddTodoListDTO userRequest = new AddTodoListDTO(userA,title,content);

        String requestBody = objectMapper.writeValueAsString(userRequest);
        //객체를 JSON으로 직렬화 한다. (Post로 오는 요청은 JSON으로 오기때문에 직렬화 해줘야함.)

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        //mock을 통해 JSON를 보냄.

        //then
        result.andExpect(status().isCreated());

        List<TodoBoard> todoBoardList = todoBoardRepository.findAll();

        assertThat(todoBoardList.size()).isEqualTo(1);
        assertThat(todoBoardList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("getTodoList(): get요청으로 글 목록 조회시 2개이상 조회완료에 성공하기")
    void test2() throws Exception{
        //given
        String userId = "userA";
        String userName = "kim";
        String userPw = "1234";
        String userEmail = "abc@naver.com";
        Member userA = Member.builder()
                .memberId(userId)
                .name(userName)
                .password(userPw)
                .email(userEmail)
                .gender(Gender.MALE)
                .rank(Rank.ROLE_USER)
                .build();
        memberRepository.save(userA);



        String url = "/api/todolist";
        String title1 = "abc";
        String content1 = "test1";
        String title2 = "def";
        String content2 = "test2";
        final AddTodoListDTO userRequest1 = new AddTodoListDTO(userA,title1,content1);
        final AddTodoListDTO userRequest2 = new AddTodoListDTO(userA,title2,content2);
        todoBoardService.boardSave(userRequest1);
        todoBoardService.boardSave(userRequest2);
        //when
        ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        //then
        List<TodoBoard> allTodos = todoBoardRepository.findAll();

        assertThat(allTodos.size()).isEqualTo(2);

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content1))
                .andExpect(jsonPath("$[1].content").value(content2));
    }
}