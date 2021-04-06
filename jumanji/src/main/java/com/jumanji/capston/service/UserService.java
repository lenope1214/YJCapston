package com.jumanji.capston.service;

import com.jumanji.capston.controller.exception.UserException.UserNotFoundException;
import com.jumanji.capston.data.Request.UserDto;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("error-0001", "해당 유저를 찾을 수 없습니다."));
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
    public User insert(UserDto user) {
        // 현재 비밀번호를 받는 족족 그대로 넣고있기 때문에 시큐리티에 걸려 로그인 불가능.
        // 비밀번호를 암호화 해서 넣어줘야 함.i
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        User userEntity = User.createUser()
                .id(user.getId())
                .password(encPassword)
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .sign_date(new Date())
                .address(user.getAddress())
                .addressDetail(user.getAddressDetail())
                .role(user.getRole())
                .provider("jumin")
                .provider_id(null)
//                .level(0)
                .build();
        if (userRepository.findById(user.getId()).isEmpty())
            return userRepository.save(userEntity);
        else {
            System.out.println("이미 있는 아이디. 회원가입 불가.");
            return null;
        }
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
    public User updateUser(User oldData, User.Request newData) {
        System.out.println("Update User in");
        oldData.setAddress(newData.getAddress());
        oldData.setAddressDetail(newData.getAddressDetail());
        oldData.setPassword(bCryptPasswordEncoder.encode(newData.getPassword()));
        return userRepository.save(oldData);
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











