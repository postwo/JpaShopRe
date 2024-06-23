package com.example.shoppingre.controller;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.service.member.MemberInsertServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberInsertServcie memberInsertServcie;


    //관리자 페이지에서 보여줄거
    @GetMapping("memberList")
    public String list(){
        return "member/memberList";
    }

    //관리자페이지에서 회원등록
    @GetMapping("memberRegist")
    public String form(){
        return "member/memberForm";
    }

    //회원가입
    @PostMapping("memberRegist")
    public String regist(MemberDTO memberDTO){
        memberInsertServcie.memberInsert(memberDTO);

        //redirect 절대경로 써주기
        return "redirect:/member/memberList";
    }
}
