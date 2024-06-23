package com.example.shoppingre.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    //들어온 사람이 수정  생성을 할거기 때문에 이렇게 만들어준다
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //누가 로그인하고 있는지 알수 있다
        String userId="";
        if (authentication != null){
            userId =  authentication.getName();
        }

        return Optional.of(userId);
    }
}