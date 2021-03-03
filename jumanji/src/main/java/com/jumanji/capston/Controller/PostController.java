package com.jumanji.capston.Controller;

import com.jumanji.capston.data.Member;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rest")
public class PostController {
//    @Autowired
//    AccountRepository accountRepository;

    @Autowired
    ShopRepository shopRepository;

    @PostMapping("/join")
    public Member index(Member m){
        System.out.println(m);
        return m;
    }
    @PostMapping("/addshop")
    public String addshop(Model model,
        @RequestParam(name="id") String id,
        @RequestParam(name="name" ) String name,
        @RequestParam(name="intro",required=false)String intro,
        @RequestParam(name="open_time" ) Integer open,
        @RequestParam(name="close_time")Integer close,
        @RequestParam(name="address" )String addr,
        @RequestParam(name="address_detail",required=false)String addr_detail,
        @RequestParam(name="is_rs_pos",required=false)char is_rs_pos
//        @RequestParam(name="category", required = false)String category


    ){
            Shop shop = new Shop();
            shop.setId(id);
            shop.setName(name);
            shop.setIntro(intro);
            shop.setOpen_time(open);
            shop.setClose_time(close);
            shop.setAddress(addr);
            shop.setAddress_detail(addr_detail);
            shop.setIs_rs_pos(is_rs_pos);
//            shop.setCategory(category);
            shopRepository.saveAndFlush(shop);

        return "redirect:/rest/shoplist";
    }

    @PostMapping("/detail")
    public String detail(Model model,
                          @RequestParam(name="id") String id,
                          @RequestParam(name="name" ) String name,
                          @RequestParam(name="intro",required=false)String intro,
                          @RequestParam(name="open_time" ) Integer open,
                          @RequestParam(name="close_time")Integer close,
                          @RequestParam(name="address" )String addr,
                          @RequestParam(name="address_detail",required=false)String addr_detail,
                          @RequestParam(name="is_rs_pos",required=false)char is_rs_pos
//        @RequestParam(name="category", required = false)String category


    ){
        Shop shop = new Shop();
        shop.setId(id);
        shop.setName(name);
        shop.setIntro(intro);
        shop.setOpen_time(open);
        shop.setClose_time(close);
        shop.setAddress(addr);
        shop.setAddress_detail(addr_detail);
        shop.setIs_rs_pos(is_rs_pos);
//            shop.setCategory(category);
        shopRepository.saveAndFlush(shop);

        return "redirect:/rest/shoplist";
    }

//    @PostMapping("delet")

}