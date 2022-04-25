package hello.hellointellij;

import hello.hellointellij.repository.JdbcMemberRepository;
import hello.hellointellij.repository.JdbcTemplateMemberRepository;
import hello.hellointellij.repository.MemberRepository;
import hello.hellointellij.repository.MemoryMemberRepository;
import hello.hellointellij.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//스프링 빈으로 직접 등록 / 정형화 되지 않거나 상황에 따라 구현 클래스를 변경하는 경우... //정형화된 경우 컴포넌트 스캔방식사용.
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); //개방-폐쇄 원칙(OCP, Open-Closed Principle)
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
