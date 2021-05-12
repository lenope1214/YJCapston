package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder @AllArgsConstructor
@Entity(name = "option_groups") @NoArgsConstructor // entity has to no args constructor
public class OptionGroup {
    @Id
    @Column(length = 18)
    private String id;
    @Column(length = 60)
    private String name;
    @Column(length = 2)
    private int min;
    @Column(length = 2)
    private int max;

    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Menu menu;

    @Transient
    @JoinColumn
    @OneToMany(fetch = FetchType.LAZY)
    private List<Option> optionList;

    @NoArgsConstructor @Getter
    public static class Request{
//        private String optionGroupId;
        private String name;
        private int min;
        private int max;
        private String menuId;
        private String shopId;
    }

    @AllArgsConstructor @Getter // getter 없으니까 반환할때 값 못불러와서 실패.
    public static class Response{
        private String optionGroupId;
        private String optionGroupName;
        private int optionGroupMin;
        private int optionGroupMax;
        private String menuId;

        public Response(OptionGroup og){
            this.optionGroupId = og.getId();
            this.optionGroupName = og.getName();
            this.optionGroupMin = og.getMin();
            this.optionGroupMax = og.getMax();
            this.menuId = og.getMenu().getId();
        }
    }
}
