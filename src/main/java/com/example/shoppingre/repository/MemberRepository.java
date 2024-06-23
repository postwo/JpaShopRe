package com.example.shoppingre.repository;

import com.example.shoppingre.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {


}
