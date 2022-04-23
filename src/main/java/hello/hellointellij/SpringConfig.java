package hello.hellointellij;

import hello.hellointellij.repository.MemberRepository;
import hello.hellointellij.repository.MemoryMemberRepository;
import hello.hellointellij.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈으로 직접 등록 / 정형화 되지 않거나 상황에 따라 구현 클래스를 변경하는 경우... //정형화된 경우 컴포넌트 스캔방식사용.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
