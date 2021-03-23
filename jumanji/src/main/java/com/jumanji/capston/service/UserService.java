package com.jumanji.capston.service;

import com.jumanji.capston.DTO.PutUserDTO;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User findById(String id) {
        if( userRepository.findById(id).isPresent()){
            return  userRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Transactional
    public boolean checkPW(User _user, String encodedPassword) {
        String rawPassword = _user.getPassword();
        System.out.println("encodedPassword : " + encodedPassword);
        System.out.println("rawPassword : " + rawPassword);

        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
        //                                  입력받은 pw  ,  암호화된 pw
    }

    @Transactional
    public User insert(User user) {
        System.out.println("join\nm.toString() : " + user.toString() + "\n" +
                        "m.getId() : " + user.getId() + "\n" +
                        "m.getPw() : " + user.getPassword() + "\n" +
                        "m.getName() : " + user.getName() + "\n" +
                        "m.getPhone() : " + user.getPhone() + "\n" +
                        "m.getRole() : " + user.getRole() + "\n"
//                + "m.getSign_date() : " + userEntity.getSignDate()
        );
        // 현재 비밀번호를 받는 족족 그대로 넣고있기 때문에 시큐리티에 걸려 로그인 불가능.
        // 비밀번호를 암호화 해서 넣어줘야 함.i
        user.setSignDate(new Date());
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        if (userRepository.findById(user.getId()).isEmpty())
            return userRepository.save(user);
        else {
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
    public User updateUser(PutUserDTO putUserDTO) {
        System.out.println("Update User in");
        User user = userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new IllegalArgumentException("수정할 아이디가 잘못됨."));
        String rawPassword = putUserDTO.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
//        if(putUserDTO.getUser().getId() != null)System.out.println("아이디 : " + putUserDTO.getUser().getId());
//        if(putUserDTO.getUser().getName() != null)System.out.println("이름 : " + putUserDTO.getUser().getName());
//        if(putUserDTO.getUser().getAddress() != null)System.out.println("주소 : " + putUserDTO.getUser().getAddress());
//        if(putUserDTO.getUser().getAddress_detail() != null)System.out.println("상세주소 : " + putUserDTO.getUser().getAddress_detail());
//        if(putUserDTO.getUser().getPhone() != null)System.out.println("폰 : " + putUserDTO.getUser().getPhone());
//        if(putUserDTO.getUser().getBirthday() != null)System.out.println("생일 : " + putUserDTO.getUser().getBirthday());
//        if(putUserDTO.getUser().getEmail() != null)System.out.println("이메일 : " + putUserDTO.getUser().getEmail());
//        System.out.println("탍뢰여부 : " + putUserDTO.getUser().getIsWdrw());
//        if(putUserDTO.getUser().getLevel() != null)System.out.println("레벨 : " + putUserDTO.getUser().getLevel());
//        System.out.println("포인트 : " + putUserDTO.getUser().getPoint());
//        if(putUserDTO.getUser().getRole() != null)System.out.println("권한 : " + putUserDTO.getUser().getRole());
//        if(putUserDTO.getUser().getSignDate() != null)System.out.println("가입일자 : " + putUserDTO.getUser().getSignDate());
        System.out.println("Update User out");
        return userRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public String delete(String id) {
        userRepository.deleteById(id);
        return "ok"; // 삭제가 잘 되면 ok 반환.
    }

    public String deleteAll() {
        userRepository.deleteAll();
        return "ok";
    }
}












