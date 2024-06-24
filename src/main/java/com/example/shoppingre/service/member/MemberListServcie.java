package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.entity.member.Member;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberListServcie {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<MemberDTO> list(String searchWord, Pageable pageable) {
    Page<Member> list = memberRepository.findAllBySearchWord(searchWord,pageable);

    return list.map(MemberDTO::createMemberDTO);
    }
}
