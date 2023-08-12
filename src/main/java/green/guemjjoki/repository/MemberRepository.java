package green.guemjjoki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import green.guemjjoki.entitiy.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String>{ }
