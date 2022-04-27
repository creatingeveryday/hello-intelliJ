package hello.hellointellij.repository;

import hello.hellointellij.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data JPA는 JPA 기술을 좀 더 편리하게 사용하기 위한 라이브러리.. 동적 쿼리는 Querydsl 라이브러리를 주로 사용하고, (나중에 배워야겠다. )
// Jpa나 Querydsl로 해결하기 어려운 로직은 네이티브쿼리로 해결하거나 혹은 JdbcTemplate을 사용한다.

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
