package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.entity.member.Member;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberDTO memberDetail(String memberNum){
       Member mem = memberRepository.findByMemberNum(memberNum);
       return MemberDTO.createMemberDTO(mem);
    }
}
