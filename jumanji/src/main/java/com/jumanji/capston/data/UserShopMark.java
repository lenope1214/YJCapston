package com.jumanji.capston.data;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_shop_marks")
@Getter @NoArgsConstructor @ToString
public class UserShopMark {
    @EmbeddedId
    private UserShopMarkId id;

    public UserShopMark(User user, Shop shop){
        this.id = new UserShopMarkId(user, shop);
    }

    @Getter @NoArgsConstructor
    public static class Request {
        private String shopId;
    }

    @Getter @AllArgsConstructor
    public static class Response{
        private User.Response user;
        private Shop.Response shop;
        private List<Shop.Response> shopList = new ArrayList<>();

        public Response(UserShopMark usm){
            this.user = new User.Response(usm.getId().getUser());
            this.shop = new Shop.Response(usm.getId().getShop());
        }
        public Response(List<UserShopMark> usmList ){
            this.user = new User.Response(usmList.get(0).getId().getUser());
            for(UserShopMark usm : usmList){
                this.shopList.add(new Shop.Response(usm.getId().getShop()));
            }
        }
    }


}
