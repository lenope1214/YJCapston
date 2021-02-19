//package com.codesample.mymug.controller;
//
//import com.codesample.mymug.data.Menu;
//import com.codesample.mymug.data.User;
//import com.codesample.mymug.repository.UserRepository;
//import com.codesample.mymug.service.MenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.codesample.mymug.data.Point;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//public class ApiController {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private MenuService menuService;
//
//
//    @GetMapping("/mypoint/{userid}")
//    public Point getPoint(@PathVariable("userid") String userid) {
//        Optional<User> user = userRepository.findById(userid);
//        Point point;
//        if (user.isPresent()) {
//            point = new Point(userid, user.get().getPoint());
//        }else{
//            point=new Point(userid, 3000);
//        }
//        return point;
//    }
//    @PostMapping("/adduser")
//    public User addUser(@RequestBody User user) {
//// 나중에 DB코드 추
//        return user;
//    }
//    @GetMapping("/users")
//    public List<User> getUsers(){
//        return userRepository.findAll(); // -> select * from user;
//    }
//
//    @GetMapping("/menus")
//    public List<Menu> getMenus(){
//        return menuService.findAllMenu();
//    }
//
//}