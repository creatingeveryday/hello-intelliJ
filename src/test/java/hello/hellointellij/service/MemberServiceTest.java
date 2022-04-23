package hello.hellointellij.service;

import hello.hellointellij.domain.Member;

import hello.hellointellij.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


// 테스트 코드는 실제 빌드 될때 포함되지 않는 코드이므로 직관적으로 한글로 작성하기도 함.
// 테스트로 예외 처리 중요.

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    // DI방식으로 변경 / 같은 라포지토리 인스턴스를 사용하여 테스트
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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