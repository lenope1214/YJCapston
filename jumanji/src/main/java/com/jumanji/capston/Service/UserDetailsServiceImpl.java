package com.jumanji.capston.Service;

import com.jumanji.capston.data.Account;
import com.jumanji.capston.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> dbuser = accountRepository.findById(username);
        if(dbuser.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username");
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("QUERY"));
        if(dbuser.get().getAuth().equals("admin"))
                auths.add(new SimpleGrantedAuthority("WRITE"));
        UserDetails ud = User
                .withUsername(dbuser.get().getId())
                .password(dbuser.get().getPw())
                .authorities(auths)
                .roles(dbuser.get().getAuth())
                .build();
        return ud;
    }
}