package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.io.Serializable;


@Entity (name = "order_menu_options")
@NoArgsConstructor @AllArgsConstructor @Getter
@Builder
public class OrderMenuOption {
    @EmbeddedId
    OrderMenuOptionId id;

    @Column(length = 2)
    private int quantity;


    @Getter @NoArgsConstructor
    public static class Request{
        private String orderMenuId;
        private String optionId;
        private int quantity;
    }

    public static class Response{
        private String orderMenuId;
        private String optionId;
        private int quantity;
        public Response(OrderMenuOption omo){
            System.out.println("omo.getId().getOrderMenu().getId() : " + omo.getId().getOrderMenu().getId());
            System.out.println("omo.getId().getOption().getId() : " + omo.getId().getOption().getId());
            System.out.println("omo.getQuantity() : " + omo.getQuantity());
            this.orderMenuId = omo.getId().getOrderMenu().getId();
            this.optionId = omo.getId().getOption().getId();
            this.quantity = omo.getQuantity();
        }

    }

}

