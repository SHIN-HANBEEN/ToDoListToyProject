package green.guemjjoki.config;

import green.guemjjoki.dto.UserSessionDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다." + username));
        httpSession.setAttribute("user", new UserSessionDTO(member));
        return new CustomUserDetails(member);
        }
    }

