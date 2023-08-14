package green.guemjjoki.service;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.entitiy.Member;
import org.springframework.data.domain.Page;




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

    MemberDTO read(String memberID);

    Member register(MemberDTO memberDTO);
}
