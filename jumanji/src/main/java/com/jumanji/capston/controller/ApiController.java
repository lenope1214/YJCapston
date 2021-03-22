package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;

//    @Autowired
//    private PrincipalDetailsService userDetailService;

    @Transactional // 트랜잭션화 시켜서 오류발생시 롤백이 되도록
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        User userEntity = userService.insert(user);
        if (userEntity == null) return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        return new ResponseEntity<>(userEntity, HttpStatus.BAD_REQUEST);
        // 얘는 좀 더 세부화 시켜서 리턴해줍시다...!!! 는 너무 어렵고~
    }

    @Transactional(readOnly = true) // 트랜잭션이긴 한데 읽기 전용으로 속도 업 !
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User _user) throws Exception {
        System.out.println("/api/v1/login 요청");
        final User user = userService.findById(_user.getId());
        if (userService.checkPW(_user, user.getPassword())) {
            System.out.println("비밀번호 체크 성공!");
            final String token = jwtTokenUtil.generateToken(user.getId());
            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable String id) throws Exception {
        System.out.println("APIcon user/{id} 진입.");
        final User userEntity = userService.findById(id);
        System.out.println("로긘 유저 id : " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("찾을 유저 id : " + id + "\ngetUser : " + userEntity.getId());

        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(userEntity.getId())){
            if(userEntity == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }


        return new ResponseEntity<>("is not match login id <-> request id.", HttpStatus.FORBIDDEN);
    }



    @Transactional
    @GetMapping("/userDelAll")
    public ResponseEntity<?> userDelAll() {
        return new ResponseEntity<>(userService.deleteAll(), HttpStatus.OK);
    }



    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userList")
    public ResponseEntity<?> getUserList() {
        List<User> userList;
        userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList")
    public ResponseEntity<?> getShopList(){
        List<Shop> shopList = shopService.findAll();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/regShop")
    public ResponseEntity<?> insertShop(@RequestBody Shop shop){
        if(shopService.insert(shop) != null)return new ResponseEntity<>(shop, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //spring security 설정 .loginProcessingUrl("/login") 으로 처리
//    @Transactional(readOnly = true)
//    @PostMapping("/login")
//    public String login(@ModelAttribute User user) {
//        System.out.println(">> login");
//        System.out.println("m.toString() : " + user.toString() + "\n"
//                + "m.getId() : " + user.getId() + "\n"
//                + "m.getPw() : " + user.getPw() + "\n"
////              + "m.getName() : " + user.getName() + "\n"
////              + "m.getPhone() : " + user.getPhone() + "\n"
////              + "m.getRole() : " + user.getRole()
//        );
////        return userRepository.findById(user.getId())
////                .orElseThrow(()-> new MemberNotFoundException(user.getId()));
//        return "login";
//    }
}




