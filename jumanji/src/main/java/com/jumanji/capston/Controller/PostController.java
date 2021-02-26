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
        @RequestParam(name="name") String name,
        @RequestParam(name="intro")String intro,
        @RequestParam(name="open") String open,
        @RequestParam(name="close")String close,
        @RequestParam(name="address")String address,
        @RequestParam(name="is_re_pos")char is_re_pos,
        @RequestParam(name="category", required = false)String category


    ){
            Shop shop = new Shop();
            shop.setId(id);
            shop.setName(name);
            shop.setIntro(intro);
            shop.setOpen(open);
            shop.setClose(close);
            shop.setAddress(address);
            shop.setIs_re_pos(is_re_pos);
            shop.setCategory(category);
            shopRepository.saveAndFlush(shop);

        return "redirect:/rest/shoplist";
    }



}