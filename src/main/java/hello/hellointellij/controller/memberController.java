package hello.hellointellij.controller;

import hello.hellointellij.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class memberController {

    private final MemberService memberService;

    @Autowired
    public memberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
