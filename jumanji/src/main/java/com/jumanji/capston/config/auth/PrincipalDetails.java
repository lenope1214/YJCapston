package com.jumanji.capston.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어 줍니다. ( Security ContextHolder )
// 오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 user정보가 있어야 됨.
// User 오브젝트타입 -> UserDeatils 타입 객체

import com.jumanji.capston.data.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Security Session -> Authentication -> UserDetails

// 시큐리티 세션은 Authentication이 담기고
// Authentication 안에는 UserDetails 와 OAuth2User가 담길 수 있는데
// PrincipalDetails를 UserDetails, OAuth2User를 상속받음으로써
// PrincipalDetails 하나를 가지고 내부, 외부 로그인을 모두 처리 가능하게함.
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user; // 컴포지션

    public PrincipalDetails(User user){
        this.user = user;
    }



    // 해당 유저의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // user.getRole()을 리턴할 수 없어서 Collect를 만들어서 리턴
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println(user.getRole());
                return user.getRole();
            }
        });
        return collect;
    }

    // 유저 패스워드 리턴
    @Override
    public String getPassword() {
        return user.getPw();
    }

    // 유저 이름 리턴
    @Override
    public String getUsername() {
        return user.getName();
    }

    // false : 계정 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // false : 계정 잠금
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // false : 비밀번호 변경 후 일정기간 지났을때 false를 리턴
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // false : 휴면계정
    @Override
    public boolean isEnabled() {
        // ex
        // 현재시간 - 마지막 로그인 시간 => 1년 초과시 false 리턴
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }


    @Override
    public String getName() {
        return null;
    }
}
