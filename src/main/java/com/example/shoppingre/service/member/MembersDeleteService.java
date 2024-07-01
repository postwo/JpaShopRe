package com.example.shoppingre.service.member;

import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MembersDeleteService {

    private final MemberRepository memberRepository;

    //체크박스 삭제
    @Transactional
    public void MembersDel(String[] memDels){
       memberRepository.deleteByMemberNumIn(Arrays.asList(memDels));
    }

    //일반 삭제
    @Transactional
    public void MemDel(String memberNum) {
        memberRepository.deleteByMemberNum(memberNum);
    }
}
