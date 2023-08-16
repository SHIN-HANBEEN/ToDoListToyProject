package green.guemjjoki;

import green.guemjjoki.dto.MemberDTO;
import green.guemjjoki.entitiy.Member;
import green.guemjjoki.service.MemberServiceImplement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class GuemjjokiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuemjjokiApplication.class, args);
	}

}
