package com.example.shoppingre.controller;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.service.member.MemberAutoNumService;
import com.example.shoppingre.service.member.MemberInsertServcie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberInsertServcie memberInsertServcie;
    private final MemberAutoNumService memberAutoNumService;



    //관리자 페이지에서 보여줄거
    @GetMapping("memberList")
    public String list(){
        return "member/memberList";
    }

    //관리자페이지에서 회원등록
    @GetMapping("memberRegist")
    public String form(Model model){
        //회원번호를 불러오도록 한다
        MemberDTO dto = memberAutoNumService.autoNum();
        model.addAttribute("memberDTO",dto);
        return "member/memberForm";
    }

    //회원가입
    @PostMapping("memberRegist")
    public String regist(@Valid MemberDTO memberDTO, BindingResult result){

        // 오류가 있으면 오류 메시지를 html에 전달
        if (result.hasErrors()){
            return "member/memberForm";
        }

        if (!memberDTO.isMemberPwEqualsMemberPwCon()){ //false값을 가지고 와서 true로 변경
            //result.rejectValue(필드명,에러코드,메세지)
            result.rejectValue("memberPwCon","memberDTO.memberPwCon","비밀번호 재확인이 틀렸습니다");
            return "member/memberForm";
        }else{
            memberInsertServcie.memberInsert(memberDTO);
            //redirect 절대경로 써주기
            return "redirect:/member/memberList";
        }


    }
}
