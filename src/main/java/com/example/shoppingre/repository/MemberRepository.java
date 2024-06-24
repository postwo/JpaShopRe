package com.example.shoppingre.repository;

import com.example.shoppingre.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {

    //번호 자동부여
    @Query(value = "SELECT CONCAT('mem', COALESCE(MAX(CAST(SUBSTRING(m.member_num, 4) AS UNSIGNED)), 100000) + 1) " +
            "FROM member m " +
            "WHERE m.member_num LIKE 'mem%'", nativeQuery = true)
    String autoNumber();

    //이메일 중복 확인
    // 이메일이 있을수도 있고 없을수도 있어서 optional을 사용
    Optional<Member> findByMemberEmail(String memberEmail);


}
