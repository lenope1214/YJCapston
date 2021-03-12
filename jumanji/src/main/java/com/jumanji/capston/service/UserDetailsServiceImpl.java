//package com.jumanji.capston.Service;
//
//import com.jumanji.capston.data.User;
//import com.jumanji.capston.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> dbuser = userRepository.findById(username);
//        if(dbuser.isEmpty()) {
//            throw new UsernameNotFoundException("Invalid username");
//        }
//        // UserDetails는 Collect<? extends GrantedAuthority> 를 반환하는데
//        // List<GrantedAuthority> 에서 List가 Collect 의 자식이므로 가능.
//        List<GrantedAuthority> auths = new ArrayList<>();
//        auths.add(new SimpleGrantedAuthority("QUERY"));
////        if(dbuser.get().getAuth().equals("admin"))
//        if(dbuser.get().getRole().equals("admin")) // admin
//            auths.add(new SimpleGrantedAuthority("WRITE"));
//        UserDetails ud = org.springframework.security.core.userdetails.User
//                .withUsername(dbuser.get().getId())
//                .password(dbuser.get().getPw())
//                .authorities(auths)
//                .roles(dbuser.get().getRole()) // String 형이길래 "" 붙였는데 되려나?
//                .build();
//        return ud;
//    }
//}