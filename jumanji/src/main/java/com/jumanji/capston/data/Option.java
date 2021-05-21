package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity(name = "options")
@NoArgsConstructor @AllArgsConstructor
public class Option {
    @Id
    @Column(length = 18)
    private String id;
    @Column(length = 60)
    private String name;
    @Column(length = 5)
    private int price;
    @Column(length = 2)
    private int max;

    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private OptionGroup optionGroup;

    @NoArgsConstructor @Getter
    public static class Request{
        private String name;
        private int price;
        private int max;
        private String optionGroupId;
        private int no;
    }

    @AllArgsConstructor @Getter
    public static class Response{
        private String optionId;
        private String shopId;
        private String name;
        private int price;
        private int optionMax;
        private String optionGroupId;

        public Response(Option option){
            this.optionId = option.getId();
//            this.shopId = option.getOptionGroup().getMenu().getShop().getId(); 얘는 하려면 컬럼 추가하는게 나을듯. 조인이 너무 많음
            this.name = option.getName();
            this.price = option.getPrice();
            this.optionMax = option.getMax();
            this.optionGroupId = option.getOptionGroup().getId();
        }
    }
}
