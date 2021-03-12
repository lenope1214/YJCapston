package com.jumanji.capston.Service;

import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;


@RequiredArgsConstructor
@Service
public class userService {
    private final UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User findById(String id){
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id를 확인해주세요!!!"));
    }

    @Transactional
    public User insert(User user){
        User userEntity = user;
        System.out.println("join\nm.toString() : " + userEntity.toString() + "\n" +
                "m.getId() : " + userEntity.getId() + "\n" +
                "m.getPw() : " + userEntity.getPassword() + "\n" +
                "m.getName() : " + userEntity.getName() + "\n" +
                "m.getPhone() : " + userEntity.getPhone() + "\n" +
                "m.getRole() : " + userEntity.getRole()
        );
        // 현재 비밀번호를 받는 족족 그대로 넣고있기 때문에 시큐리티에 걸려 로그인 불가능.
        // 비밀번호를 암호화 해서 넣어줘야 함.
        String rawPassword = userEntity.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        if(userRepository.findById(user.getId()).isEmpty())
            return userRepository.save(userEntity);
        else{
            System.out.println("이미 있는 아이디. 회원가입 불가.");
        }
        return null;
    }

//    @Transactional
//    public User update(String id, User user){
//        User userEntity = userRepository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("Id를 확인해주세요!!!")); // 영속화 스프링 내부에 지정.
//        // 데이터 변환 후 저장
//        userRepository.save()
//        return userRepository.save(user);
//    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public String delete(String id){
        userRepository.deleteById(id);
        return "ok"; // 삭제가 잘 되면 ok 반환.
    }

    public String deleteAll() {
        userRepository.deleteAll();
        return "ok";
    }
}












