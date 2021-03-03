package com.jumanji.capston.Controller;

import com.jumanji.capston.data.Shop;
import com.jumanji.capston.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/rest")
public class WebController {

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/shoplist")
    public String shoplist(Model model){

        model.addAttribute("shopList", shopRepository.findAll());
        return "shoplist";
    }

    @GetMapping("/addshop")
    public String addshop(Model model){

        return "addshop";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id")String id){
//        ShopDto shopDto= shopRepository.getPost(id);
        model.addAttribute("shop", shopRepository.findById(id));
        return "detail";
    }
}
