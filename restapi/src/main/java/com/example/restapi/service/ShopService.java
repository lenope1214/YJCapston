package com.example.restapi.service;

import com.example.restapi.model.Member;
import com.example.restapi.model.Shop;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.server.PathParam;
import java.util.Map;

public interface ShopService {
    void register(@RequestBody Map<String, String> map);

    default Shop dtoToEntity(@RequestBody Map<String, String> map) {
        System.out.println("service@@@@ : " + map);

        Member member = Member.builder().id(map.get("member_id")).build();

        Shop shop = Shop.builder()
                .id(map.get("id"))
                .name(map.get("name"))
                .intro(map.get("intro"))
                .open(Integer.parseInt(map.get("open")))
                .close(Integer.parseInt(map.get("close")))
                .address(map.get("address"))
                .category(map.get("category"))
                .is_re_pos(map.get("is_re_pos").charAt(0))
                .member_id(member)
                .build();

        return shop;
    }
}
