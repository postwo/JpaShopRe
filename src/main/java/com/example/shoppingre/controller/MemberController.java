package com.example.shoppingre.controller;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.service.member.MemberAutoNumService;
import com.example.shoppingre.service.member.MemberInsertServcie;
import com.example.shoppingre.service.member.MemberListServcie;
import com.example.shoppingre.service.member.MembersDeleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberInsertServcie memberInsertServcie;
    private final MemberAutoNumService memberAutoNumService;
    private final MemberListServcie memberListServcie;
    private final MembersDeleteService membersDeleteService;



    //관리자 페이지에서 보여줄거
    @GetMapping("memberList")
    public String list(Model model,
                       //처음 페이지 열릴 때는 searchWord가 없으므로 페이지 오류가 생긴다
                       // 오류를 방지 하기 위해서 필수가 아니라고 해준다
                       @RequestParam(name = "searchWord", required = false) String searchWord
                       ,  @PageableDefault(size = 10, sort = "memberNum", direction = Sort.Direction.DESC)Pageable pageable){
        Page<MemberDTO> memlist = memberListServcie.list(searchWord,pageable);
        model.addAttribute("dtos",memlist);
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
    public String regist(@Valid MemberDTO memberDTO, BindingResult result,Model model){

        // 오류가 있으면 오류 메시지를 html에 전달
        if (result.hasErrors()){
            return "member/memberForm";
        }

        try{
            if (!memberDTO.isMemberPwEqualsMemberPwCon()){ //false값을 가지고 와서 true로 변경
                //result.rejectValue(필드명,에러코드,메세지)
                result.rejectValue("memberPwCon","memberDTO.memberPwCon","비밀번호 재확인이 틀렸습니다");
                return "member/memberForm";
            }else{
                memberInsertServcie.memberInsert(memberDTO);
                //redirect 절대경로 써주기
                return "redirect:/member/memberList";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
        }
    }


    //체크박스 삭제
    @PostMapping("membersDelete")
    public String dels(@RequestParam("memDels") String memDels[]){
        membersDeleteService.MembersDel(memDels);
        return "redirect:/member/memberList";
    }

    //일반 삭제 script로 reload 하기때문에 redirect가 필요없다
    @DeleteMapping("/membersDelete/{memberNum}")
    public ResponseEntity<String> deleteMember(@PathVariable("memberNum") String memberNum) {
        try {
            membersDeleteService.MemDel(memberNum);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            //서버에서 데이터베이스 접근 중에 예외가 발생하거나, 파일 시스템 접근에 문제가 발생하는 경우 등에 INTERNAL_SERVER_ERROR를 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("멤버 삭제 실패: " + e.getMessage());
        }
    }
}
