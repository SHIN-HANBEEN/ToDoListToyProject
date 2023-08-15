package green.guemjjoki.service;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.dto.RegisterRequestMemberDTO;
import green.guemjjoki.entitiy.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.validation.Errors;

import java.util.Map;


public interface MemberService {
    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .memberNo(dto.getMemberNo())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .name(dto.getName())
                .address(dto.getAddress())
                .rank(dto.getRank())
                .build();

        return member;
    }
    default MemberDTO entityToDto(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .memberNo(member.getMemberNo())
                .password(member.getPassword())
                .email(member.getEmail())
                .gender(member.getGender())
                .name(member.getName())
                .address(member.getAddress())
                .rank(member.getRank())
                .build();
        return memberDTO;
    }


    /**
     * 회원 목록을 보여주는 메서드.
     * 아 오늘 당근 거래가야된다.
     * @param page
     * @return 회원정보를 반환하는 dto
     *
     */
    Page<MemberDTO> getMemberList(int page);

    /**
     * 회원가입에서 중복회원을 체킹하는 메소드
     * @param memberID : 입력한 아이디값
     * @return  Member(Entity) / 컨트롤러에서 값 존재 유무체킹으로 확인
     */

    @Transactional
    Member RegisterIDCheck(String memberID);

    /**
     *
     * @param registerRequestMemberDTO (요청한 입력값)
     * @return Member(Entity로 변환 후 저장)
     */

    Member register(RegisterRequestMemberDTO registerRequestMemberDTO);

    /**
     * 유효성 검사 핸들링
     * @param errors
     * @return  Map(Key : valid_{dto 필드명} / message : dto에서 작성한 메세지)
     */

    // Errors : 데유효성 검증 과정에서 발생한 에러를 수집하고 관리하기 위해 사용되는 인터페이스
    Map<String,String> validateHandling(Errors errors);
}
