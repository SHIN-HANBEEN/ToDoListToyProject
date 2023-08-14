package green.guemjjoki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import green.guemjjoki.dto.AddTodoListDTO;
import green.guemjjoki.dto.ModifyTodoListDTO;
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


import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import javax.print.DocFlavor;
import java.nio.charset.StandardCharsets;
import java.util.List;


@SpringBootTest(properties = "spring.autoconfigure." +
        "exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
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

    @Test
    @Transactional
    @DisplayName("createTodoList(): todoList 컨트롤러을 이용해  글 생성에 성공하기")
    void test1() throws Exception{
        //given
        Member member = memberRepository.findById("testUser").get();
        String url = "/api/todolist";
        String title = "thirdTitle";
        String content = "thirdContent";
        AddTodoListDTO userRequest = AddTodoListDTO.builder()
                .content(content)
                .title(title)
                .build();

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

        assertThat(todoBoardList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("getTodoList(): get요청으로 글 목록 2개이상의 글조회에 성공하기")
    @Transactional
    void test2() throws Exception{
        //given
        String url = "/api/todolist";
        String title1 = "testBoard1";
        String title2 = "testBoard2";

        //when
        ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        //then
        List<TodoBoard> allTodos = todoBoardRepository.findAll();
        MvcResult mvcResult = result.andReturn(); // 요청 수행 및 결과 반환

        // 로그로 응답 JSON 출력
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("응답 JSON: " + responseBody);

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title1))
                .andExpect(jsonPath("$[1].title").value(title2));
    }

    @Test
    @Transactional
    @DisplayName("getDetailView() : 게시물 단건 조회에 성공하기")
    void test3() throws Exception{
        //given
        final String uri = "/api/todolist/{no}";
        final String title = "testBoard1";
        final String content = "HelloWorld1";
        TodoBoard todoBoard = todoBoardRepository.findById(2L).get();

        //when
        final ResultActions result = mockMvc.perform(get(uri, todoBoard.getTodoNo()));
        MvcResult mvcResult = result.andReturn(); // 요청 수행 및 결과 반환

        // 로그로 응답 JSON 출력
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("응답 JSON: " + responseBody);

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }


    @Test
    @DisplayName("updateDetailView() : 게시물 수정에 성공하기")
    void test4() throws Exception{
        //given

        final String url = "/api/todolist/{no}";
        final String newTitle = "수정된 제목";
        final String newContent = "수정된 내용";
        ModifyTodoListDTO modifyTodoListDTO = ModifyTodoListDTO.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        //when
        ResultActions result = mockMvc.perform(put(url, 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modifyTodoListDTO)));

        //then
        result.andExpect(status().isOk());
        TodoBoard modifiedBoard = todoBoardRepository.findById(2L).get();

        assertThat(modifiedBoard.getContent()).isEqualTo(newContent);
        assertThat(modifiedBoard.getTitle()).isEqualTo(newTitle);

    }


}