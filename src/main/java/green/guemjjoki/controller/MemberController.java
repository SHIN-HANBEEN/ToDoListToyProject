package green.guemjjoki.controller;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
// 생성자 주입 애노테이션
// @RequestMapping("/member") 는 회원의 등급에 따라 접근을 다르게 주기 위해서 중간 경로를 지웁니다.
public class MemberController {

    private final MemberService memberService;

    @GetMapping("member/list")
    public String list(@RequestParam(defaultValue = "0") int page, Model model){
    Page<MemberDTO> listPage = memberService.getMemberList(page);
    model.addAttribute("listPage", listPage);
    return "member/list";
    }

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }


}
