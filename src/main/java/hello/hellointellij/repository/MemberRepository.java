package hello.hellointellij.repository;

import hello.hellointellij.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findById(String name);
    List<Member> findAll();
}