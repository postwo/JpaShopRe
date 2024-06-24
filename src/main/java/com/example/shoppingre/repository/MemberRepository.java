package com.example.shoppingre.repository;

import com.example.shoppingre.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //번호 자동부여
    @Query(value = "SELECT CONCAT('mem', COALESCE(MAX(CAST(SUBSTRING(m.member_num, 4) AS UNSIGNED)), 100000) + 1) " +
            "FROM member m " +
            "WHERE m.member_num LIKE 'mem%'", nativeQuery = true)
    String autoNumber();

    //이메일 중복 확인
    // 이메일이 있을수도 있고 없을수도 있어서 optional을 사용
    Optional<Member> findByMemberEmail(String memberEmail);

    //회원정보검색
    @Query("SELECT m FROM Member m WHERE " +
            "(COALESCE(:searchWord, '') = '' OR m.memberName LIKE %:searchWord% OR m.memberNum Like %:searchWord% OR m.memberEmail LIKE %:searchWord%)")
    Page<Member> findAllBySearchWord(@Param("searchWord") String searchWord, Pageable pageable);
}
