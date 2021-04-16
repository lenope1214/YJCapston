package com.jumanji.capston.service;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.Auth.ForbiddenException;
import com.jumanji.capston.service.exception.UserException.PasswordMissMatchException;
import com.jumanji.capston.service.exception.UserException.UserHasExistException;
import com.jumanji.capston.service.exception.UserException.UserNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.UserService;
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
public class UserServiceImpl implements UserService, BasicService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    ShopServiceImpl shopService;

    @Transactional
    public String getMyId(String authorization) {
        return jwtTokenUtil.getUsername(authorization);
    }

    @Transactional
    public User getUserInfo(String id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public ResponseEntity<?> findById(String id) {
        if (userRepository.findById(id).isPresent())
            return new ResponseEntity<>(new User.Response(userRepository.findById(id).get()), HttpStatus.OK);
        throw new UserNotFoundException(); // ApiExceptionHandler가 잡아채서 반환해줌.
//        return new ResponseEntity<>(new BasicException.CodeMessage(new UserNotFoundException()), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> get(String userId) {
        isPresent(userId);
        User user = userRepository.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getList(String authorization) {
        String loginId = getMyId(authorization);
        isPresent(loginId);
        User user = userRepository.findById(loginId).get();
        System.out.println("로그인 아이디의 권한 : " + user.getRole());
        if (isAuth(user.getRole(), "ADMIN")) {
            throw new ForbiddenException();
        }
        List<User.Response> userList = new ArrayList<>();
        for (User _user : userRepository.findAll()) {
            userList.add(new User.Response(_user));
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> post(User.Request user) {
        isEmpty(user.getId());
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
                .provider("jumin") /** 얘는 추후에 변경해야함. **/
                .provider_id(null)
//                .level(0)
                .build();
        return new ResponseEntity<>(new User.Response(userRepository.save(userEntity)), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> patch(String authorization, User.Request request) {
        String loginId = getMyId(authorization);
        isPresent(loginId);

        User loginUser = userRepository.findById(loginId).get();
        updateInfo(loginUser, request);
        return new ResponseEntity<>(new User.Response(loginUser), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> delete(String id) {
        isPresent(id);
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 삭제가 잘 되면 ok 반환.
    }

    @Transactional
    public ResponseEntity<?> login(User.Request request) {
        isPresent(request.getId());
        User userEntity = userRepository.findById(request.getId()).get();
//         아이디 오류 후에 아이디, 비번 오류 통합.. 현재는 있는지 확인하기 위해 이렇게 둠.
        checkPW(request, userEntity.getPassword());
        final String access_token = jwtTokenUtil.generateToken(userEntity.getId());
        JwtResponse jwtResponse = new JwtResponse(access_token, userEntity.getRole());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> validationId(String id) {
        if (userRepository.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void checkPW(User.Request _user, String encodedPassword) {
        String rawPassword = _user.getPassword();
        System.out.println("rawPw : " + rawPassword);
        System.out.println("encPw : " + encodedPassword);
        if (!bCryptPasswordEncoder.matches(rawPassword, encodedPassword))
            throw new PasswordMissMatchException();
    }

    public void updateInfo(User oldData, User.Request newData) {
        System.out.println("Update User in");
        if (newData.getAddress() != null) oldData.setAddress(newData.getAddress());
        if (newData.getAddressDetail() != null) oldData.setAddressDetail(newData.getAddressDetail());
        if (newData.getPassword() != null) oldData.setPassword(bCryptPasswordEncoder.encode(newData.getPassword()));
        userRepository.save(oldData);
    }

    public boolean isEmpty(String id) {
        if (userRepository.findById(id).isEmpty()) return true;
        throw new UserHasExistException();
    }


    public boolean isPresent(String id) {
        if (userRepository.findById(id).isPresent()) return true;
        throw new UserNotFoundException(id);
    }

    /**
     * role args ex) "ADMIN"  "OWNER"  "USER"
     **/
    public boolean isAuth(String userRole, String role) {
        if (userRole.equals("ROLE_ADMIN")) return true;
        if (userRole.equals("ROLE_OWNER")) return !role.equals("ADMIN");
        if (userRole.equals("ROLE_USER")) return role.equals("USER");
        return false; // 이게 될리 없음. 여기까지 왔다는건 잘못된 값이 넘어온것...
    }
}












