package green.guemjjoki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import green.guemjjoki.entitiy.Member;


public interface MemberRepository extends JpaRepository<Member, String>{

}
