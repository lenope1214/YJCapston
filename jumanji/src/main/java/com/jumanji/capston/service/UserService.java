package com.jumanji.capston.service;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.controller.exception.UserException.UserNotFoundException;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    ShopService shopService;

    @Transactional
    public String getMyId(String authorization) {
        return jwtTokenUtil.getUsername(authorization);
    }

    @Transactional
    public User getMyInfo(String id){
        return userRepository.findById(id).get();
    }
    @Transactional
    public ResponseEntity<?> findById(String id) {
        if (userRepository.findById(id).isPresent())
            return new ResponseEntity<>(new User.Response(userRepository.findById(id).get()), HttpStatus.OK);
        throw new UserNotFoundException(); // ApiExceptionHandler가 잡아채서 반환해줌.
//        return new ResponseEntity<>(new BasicException.CodeMessage(new UserNotFoundException()), HttpStatus.NOT_FOUND);
    }

    @Transactional
    public boolean checkPW(User.Request _user, String encodedPassword) {
        String rawPassword = _user.getPassword();
        System.out.println("rawPw : " + rawPassword);
        System.out.println("encPw : " + encodedPassword);
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
        //                                  입력받은 pw  ,  암호화된 pw
    }

    @Transactional
    public ResponseEntity<?> insert(User.Request user) {
        if (userRepository.findById(user.getId()).isEmpty()) {
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
            return new ResponseEntity<>(new User.Response(userRepository.save(userEntity)), HttpStatus.CREATED);
        } else {
            System.out.println("이미 있는 아이디. 회원가입 불가.");
            return new ResponseEntity<>(new ApiErrorResponse("0003"), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> patchUser(String authorization, User.Request request) {
        String loginId = getMyId(authorization);
        User loginUser = null;
        if (userRepository.findById(loginId).isPresent()){
            loginUser = userRepository.findById(loginId).get();
            updateUser(loginUser, request);
            return new ResponseEntity<>(new User.Response(loginUser), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiErrorResponse("0001"), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public User updateUser(User oldData, User.Request newData) {
        System.out.println("Update User in");
        if(newData.getAddress()!= null)oldData.setAddress(newData.getAddress());
        if(newData.getAddressDetail()!= null)oldData.setAddressDetail(newData.getAddressDetail());
        if(newData.getPassword()!= null)oldData.setPassword(bCryptPasswordEncoder.encode(newData.getPassword()));
        return userRepository.save(oldData);
    }

    @Transactional
    public ResponseEntity<?> findAll(String authorization) {
        String loginId = getMyId(authorization);
        isPresent(loginId);
        System.out.println("로그인 아이디의 권한 : " + userRepository.findById(loginId).get().getRole());
        if (!userRepository.findById(loginId).get().getRole().equals("ROLE_ADMIN")) {
            return new ResponseEntity<>(new ApiErrorResponse("0000"), HttpStatus.FORBIDDEN);
        }
        List<User.Response> userList = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            userList.add(new User.Response(user));
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @Transactional
    public String delete(String id) {
        userRepository.deleteById(id);
        return "ok"; // 삭제가 잘 되면 ok 반환.
    }

//    public ResponseEntity<?> deleteAll(String authorization) {
//        if()
//        userRepository.deleteAll();
//        return ;
//    }


    public ResponseEntity<?> login(User.Request request) {
        isPresent(request.getId());
        User userEntity = userRepository.findById(request.getId()).get();
//         아이디 오류 후에 아이디, 비번 오류 통합.. 현재는 있는지 확인하기 위해 이렇게 둠.
        if (checkPW(request, userEntity.getPassword())) {
            final String access_token = jwtTokenUtil.generateToken(userEntity.getId());
            JwtResponse jwtResponse = new JwtResponse(access_token, userEntity.getRole());
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } else { // 비밀번호 오류
            return new ResponseEntity<>(new ApiErrorResponse("error-0002", "faild login"), HttpStatus.BAD_REQUEST);
        }
    }



    private boolean isEmpty(String id) {
        if (userRepository.findById(id).isEmpty()) return true;
        throw new UserNotFoundException();
    }


    public boolean isPresent(String id){
        if(userRepository.findById(id).isPresent())return true;
        throw new UserNotFoundException(id);
    }

    public ResponseEntity<?> validationId(String id) {
        if(userRepository.findById(id).isEmpty())return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}












