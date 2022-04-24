package hello.hellointellij.service;

import hello.hellointellij.domain.Member;
import hello.hellointellij.repository.MemberRepository;
import hello.hellointellij.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


// 테스트 코드는 실제 빌드 될때 포함되지 않는 코드이므로 직관적으로 한글로 작성하기도 함.
// 테스트로 예외 처리 중요.

// 스프링,DB 통합 테스트
// 테스트는 언제나 반복할 수 있어야한다. 그렇게 만들어야 한다.
// @Transactional 붙어서 테스트 끝난 후 롤백되는 것을 이용

//스프링 컨테이너 필요 없이 단위테스트만으로 테스트를 할 수 있게 훈련을 해야한다. 그래야 좋은 테스트일 확률이 높다. 속도도 빠르다.
//단위 테스트 설계를 잘해서 잘 만들자.

@SpringBootTest    //스프링컨테이너와 테스트를 함께 실행
@Transactional  // 테스트 케이스에서는 테스트 시작 전 트랜잭션 시작하고 테스트 완료 후에 롤백한다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        //try-catch 를 쓰는 것보다 간편.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}