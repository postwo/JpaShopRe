package com.example.shoppingre.dto;


import com.example.shoppingre.entity.member.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true) //callSuper = true ==부모 클래스의 toString 메서드를 포함하겠다는 의미
@Getter
@Setter
public class MemberDTO{

    // html에 전달할 오류 메세지를 command에서 설정해준다.
    // String자료형에서는 @NotEmpty, @NotBlank를 사용한다.

    private Long id;
    private String memberNum;

    // 비밀번호는 패턴을 사용해야 한다.영문자와 특수문자 그리고 숫자를 사용하고 8글자 이상
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#!@$%^&*-+?~]).{8,}$",
            message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
    private String memberPw;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String memberPwCon;
    @NotBlank(message = "이름을 입력해주세요")
    private String memberName;
    @NotBlank(message = "주소를 입력하여 주세요.")
    private String memberAddr;
    @NotBlank(message = "상세주소를 입력하여 주세요")
    private String memberAddrDetail; //detail이다
    @NotBlank(message = "우편번호를 입력하여 주세요")
    private String memberPost;
    @NotBlank(message = "연락처을 입력하여 주세요.")
    private String memberPhone;
    @NotEmpty(message = "이메일을 입력하여 주세요.")
    private String memberEmail; //아이디 대신 사용

    //문자형 날짜를 자동형변환하기 위한 패턴이 필요..
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // String이 아닌 모든 자료형은 @NotNull을 사용한다.
    @NotNull(message="생년월일을 입력해주세요.")
    private Date memberBirth;

    private LocalDateTime regDate;

    private Integer point;

    //비밀번호와 비밀번호확인이 같은지 확인 하기 위한 메서드
    public boolean isMemberPwEqualsMemberPwCon() {
        return memberPw.equals(memberPwCon);
    }


    //데이터베이스에서 받은값을 서비스 컨트롤러에서 사용하기위해
    public static MemberDTO createMemberDTO(Member member){

        MemberDTO memberdto = MemberDTO.builder()
                .memberNum(member.getMemberNum())
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberAddr(member.getMemberAddr())
                .memberAddrDetail(member.getMemberAddrDetail())
                .memberBirth(member.getMemberBirth())
                .memberName(member.getMemberName())
                .memberPost(member.getMemberPost())
                .memberPhone(member.getMemberPhone())
                .regDate(member.getRegDate())
                .build();

        return memberdto;
    }



}
