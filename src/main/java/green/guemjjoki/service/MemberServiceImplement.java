package green.guemjjoki.service;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.dto.RegisterRequestMemberDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.repository.MemberRepository;
import green.guemjjoki.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;


@Service
@RequiredArgsConstructor
public class MemberServiceImplement implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Page<MemberDTO> getMemberList(int page) {
        int pageNum = (page == 0) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("rank"));
        Page<Member> entityPage = memberRepository.findAll(pageable);
        return entityPage.map(this::entityToDto);
    }

    @Override
        public Member RegisterCheckID(String memberID) {
        return memberRepository.findById(memberID).orElse(null);
    }

    @Override
    @Transactional  // DB관련된 작업 진행할 때 / 오류발생 시 모든 작업 원복
    public Member register(RegisterRequestMemberDTO registerRequestMemberDTO) {
        Member memberEntity = registerRequestMemberDTO.toEntity();
        memberEntity.setPassword(new BCryptPasswordEncoder().encode(memberEntity.getPassword()));
        return memberRepository.save(memberEntity);
    }

    @Override
    @Transactional // DB와 관련된 작업을 할 때 사용 / 오류발생 시 모든 작업 원복
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validateResult = new HashMap<>();
        // 유효성 검사에 실패한 필드목록을 받음
        for(FieldError fieldError : errors.getFieldErrors()){
            // 에러가 발생한 필드를 해당 형식으로 변환
            String validKeyName = String.format("vaild_%d",fieldError.getField());
            // 키값과 RegisterRequestMemberDTO에서 각 필드별로 저장한 메시지 출력
            validateResult.put(validKeyName, fieldError.getDefaultMessage());
        }
        return validateResult;
    }


}

//    @Override
//    public MemberDTO read(String memberID) {
//        Optional<Member> result = memberRepository.findById(memberID);
//        return result.map(this::entityToDto).orElse(null);
////        if (result.isPresent()) {
////            return entityToDto(result.get());
////        } else {
////            return null;
////        } 를 함수형 스타일로 변경
//    }
//
//    @Override
//    public boolean signUp(MemberDTO memberDTO) {
//        String id = memberDTO.getMemberId();
//        MemberDTO existingDto = read(id);
//
//        if (existingDto != null) {
//            System.out.println("사용중인 아이디입니다.");
//            return false;
//        }
//        Member member = dtoToEntity(existingDto);  // always null 고민하기.
//        memberRepository.save(member);
//        return true;
//    }


