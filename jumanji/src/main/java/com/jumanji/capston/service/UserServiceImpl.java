package com.jumanji.capston.service;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.Auth.ForbiddenException;
import com.jumanji.capston.service.exception.Auth.UnauthorizedException;
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
    public User get(String id) {
        isPresent(id);
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<User> getList(String authorization) {
        String loginId = getMyId(authorization);
        isPresent(loginId);
        User user = userRepository.findById(loginId).get();
        System.out.println("로그인 아이디의 권한 : " + user.getRole());
        isAuth(user.getRole(), "ADMIN");


        return userRepository.findAll();
    }

    @Transactional
    public User post(User.Request user) {
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
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public User patch(String authorization, User.Request request) {
        String loginId = getMyId(authorization);
        isPresent(loginId);

        User loginUser = userRepository.findById(loginId).get();
        updateInfo(loginUser, request);
        return loginUser;
    }

    @Transactional
    public void delete(String authorization) {
        String loginId = getMyId(authorization);
        isPresent(loginId);

        User user = get(loginId);
        user.setIsWdrw('Y');
        userRepository.save(user);
    }

    @Transactional
    public ResponseEntity<?> login(User.Request request) {
        isPresent(request.getId());
        User userEntity = userRepository.findById(request.getId()).get();
//         아이디 오류 후에 아이디, 비번 오류 통합.. 현재는 있는지 확인하기 위해 이렇게 둠.
        checkPW(request, userEntity.getPassword());
        isWdrw(request.getId());
        final String access_token = jwtTokenUtil.generateToken(userEntity.getId());
        JwtResponse jwtResponse = new JwtResponse(access_token, userEntity.getRole());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public boolean validationId(String id) {
        return userRepository.findById(id).isEmpty();
    }

    public void checkPW(User.Request _user, String encodedPassword) {
        String rawPassword = _user.getPassword();
        System.out.println("rawPw : " + rawPassword);
        System.out.println("encPw : " + encodedPassword);
        if (!bCryptPasswordEncoder.matches(rawPassword, encodedPassword))
            throw new PasswordMissMatchException();
    }

    public void updateInfo(User user, User.Request newData) {
        System.out.println("Update User in");
        if (newData.getEmail() != null) user.setEmail(newData.getEmail());
        if (newData.getAddress() != null) user.setAddress(newData.getAddress());
        if (newData.getAddressDetail() != null) user.setAddressDetail(newData.getAddressDetail());
        if (newData.getPassword() != null) user.setPassword(bCryptPasswordEncoder.encode(newData.getPassword()));
        userRepository.save(user);
    }

    public boolean isEmpty(String id) {
        if (userRepository.findById(id).isEmpty()) return true;
        throw new UserHasExistException();
    }


    public boolean isPresent(String id) {
        if (userRepository.findById(id).isPresent()) return true;
        throw new UserNotFoundException(id);
    }

    public void isWdrw(String id) {
        if (userRepository.findById(id).get().getIsWdrw() == 'Y')
            throw new UserNotFoundException();
    }

    /**
     * role args ex) "ADMIN"  "OWNER"  "USER"
     **/
    public void isAuth(String userRole, String role) {
        if(!authCheck(userRole, role))throw new ForbiddenException();
    }

    private boolean authCheck(String userRole, String role){
        if (userRole.equals("ROLE_ADMIN")) return true; // 어드민은 무조건 가능.
        if (userRole.equals("ROLE_OWNER")) return !role.equals("ADMIN"); // OWNER는 어드민 권한만 아니면 다 가능.
        if (userRole.equals("ROLE_USER")) return role.equals("USER"); // 유저는 유저만 가능.
        return false;
    }

}












