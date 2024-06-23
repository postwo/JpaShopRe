package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAutoNumService {

    private final MemberRepository memberRepository;

    //번호 자동부여
    @Transactional(readOnly = true)
    public MemberDTO autoNum() {
        String mem = memberRepository.autoNumber();
        MemberDTO dto = new MemberDTO();
        dto.setMemberNum(mem);
        return dto;
    }
}
