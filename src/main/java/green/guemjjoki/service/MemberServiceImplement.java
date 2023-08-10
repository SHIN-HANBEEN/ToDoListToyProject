package green.guemjjoki.service;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class MemberServiceImplement implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Page<MemberDTO> getMemberList(int page) {
        int pageNum = (page==0) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNum,10, Sort.by("rank"));
        Page<Member> entityPage = memberRepository.findAll(pageable);
        Page<MemberDTO> dtoPage = entityPage.map(entity->entityToDto(entity));
        return dtoPage;
    }

    @Override
    public MemberDTO read(String memberID) {
        Optional<Member> result = memberRepository.findById(memberID);
        return result.map(this::entityToDto).orElse(null);
//        if (result.isPresent()) {
//            return entityToDto(result.get());
//        } else {
//            return null;
//        } 를 함수형 스타일로 변경
    }

    @Override
    public boolean signUp(MemberDTO memberDTO) {
        String id = memberDTO.getMemberId();
        MemberDTO existingDto = read(id);

        if (existingDto != null) {
            System.out.println("사용중인 아이디입니다.");
            return false;
        }
        Member member = dtoToEntity(existingDto);  // always null 고민하기.
        memberRepository.save(member);
        return true;
    }

}
