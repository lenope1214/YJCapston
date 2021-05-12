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
    private int choiceNum;


    public static class Request{

    }

    public static class Response{

    }

}

