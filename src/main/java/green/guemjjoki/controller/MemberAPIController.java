package green.guemjjoki.controller;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.dto.RegisterRequestMemberDTO;
import green.guemjjoki.dto.UserSessionDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {
    private final MemberService memberService;

    @PostMapping("/api/register")
    public ResponseEntity<Member> register(@RequestBody RegisterRequestMemberDTO dto){
        Member registermember = memberService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registermember);
    }
}
