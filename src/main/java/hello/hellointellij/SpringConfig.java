package hello.hellointellij;

import hello.hellointellij.aop.TimeTraceAop;
import hello.hellointellij.repository.*;
import hello.hellointellij.service.MemberService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//스프링 빈으로 직접 등록 / 정형화 되지 않거나 상황에 따라 구현 클래스를 변경하는 경우... //정형화된 경우 컴포넌트 스캔방식사용.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    private EntityManager entityManager;
//
//    public SpringConfig(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    //    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService(){

//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /**
     * AOP 스프링 빈으로 직접 등록하여 관리해도 하는 방식도 좋을듯?
     * */
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource); //개방-폐쇄 원칙(OCP, Open-Closed Principle)
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(entityManager);
//
//    }


}
