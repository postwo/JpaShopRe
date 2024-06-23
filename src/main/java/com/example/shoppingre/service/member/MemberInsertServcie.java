package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.entity.member.Member;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInsertServcie {

    private  final MemberRepository memberRepository;
    private  final PasswordEncoder passwordEncoder;

    //회원가입
    public void memberInsert(MemberDTO memberDTO){
        Member mem = Member.createMember(memberDTO,passwordEncoder);
        memberRepository.save(mem);
    }



}
