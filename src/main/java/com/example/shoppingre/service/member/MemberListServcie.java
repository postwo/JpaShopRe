package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.entity.member.Member;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberListServcie {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDTO> list() {
    List<Member> list = memberRepository.findAll();
    List<MemberDTO> memlist = list.stream().map(MemberDTO::createMemberDTO)
            .collect(Collectors.toList());
    return memlist;
    }
}
