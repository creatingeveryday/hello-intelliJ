package hello.hellointellij.service;

import hello.hellointellij.domain.Member;
import hello.hellointellij.repository.MemberRepository;
import hello.hellointellij.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 비즈니스로직을 다루는 서비스 계층에서는 비즈니스 처리에 맞는 용어를 선정하여 사용하는 경향. / repo에 쓰는 용어는 저장소에 어울리는 용어를 선정.

public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member){

        validateDuplicate(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicate(Member member) {
        memberRepository.findById(member.getName())
             .ifPresent(memberInfo -> {
                 throw new IllegalStateException("이미존재하는 회원입니다.");
             });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
