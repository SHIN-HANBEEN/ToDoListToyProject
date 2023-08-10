package green.guemjjoki.controller;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
    MemberService memberService;
@GetMapping("/list")
    public void list(@RequestParam(defaultValue = "0")int page, Model model){
    Page<MemberDTO> listPage = memberService.getMemberList(page);
    model.addAttribute("listPage", listPage);
}

}
