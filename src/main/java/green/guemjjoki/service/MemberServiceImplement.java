package green.guemjjoki.service;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@
public class MemberServiceImplement implements MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Override
    public Page<MemberDTO> getMemberList() {
        return null;
    }
}
