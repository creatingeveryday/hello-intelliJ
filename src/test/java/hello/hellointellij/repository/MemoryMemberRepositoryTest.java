package hello.hellointellij.repository;

import hello.hellointellij.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;  // 더 간편하게 사용할 수 있는 라이브러리

// 실무에서는 테스트 코드 없이 협업하여 개발 불가능.

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("hahaha");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member,result);
        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("hahaha1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hahaha2");
        repository.save(member2);

        //when
        Member result = repository.findById("hahaha1").get();
        repository.findById("hahaha1").get();

        //then
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("hahaha1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("hahaha2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
