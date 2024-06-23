package com.example.shoppingre.service.member;

import com.example.shoppingre.dto.MemberDTO;
import com.example.shoppingre.entity.member.Member;
import com.example.shoppingre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberInsertServcie {

    private  final MemberRepository memberRepository;
    private  final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public void memberInsert(MemberDTO memberDTO){
        Member mem = Member.createMember(memberDTO,passwordEncoder);
        validateDuplicateMember(mem);
        memberRepository.save(mem);
    }

    //가입한적이 있는지 확인(중복검사)
    @Transactional(readOnly = true)
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember.isPresent()) {
            System.out.println(findMember.get().getMemberEmail());
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
