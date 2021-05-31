package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "optionGroup",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> optionList = new ArrayList<>();

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
        private String ogName;
        private int ogMin;
        private int ogMax;
        private String menuId;
        private List<Option> optionList;

        public Response(OptionGroup og){
            this.optionGroupId = og.getId();
            this.ogName = og.getName();
            this.ogMin = og.getMin();
            this.ogMax = og.getMax();
            this.menuId = og.getMenu().getId();
            this.optionList = og.getOptionList();
        }
    }
}
