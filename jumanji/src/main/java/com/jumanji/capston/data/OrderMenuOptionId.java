package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class OrderMenuOptionId implements Serializable {
    @JoinColumn(name = "order_menu_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    private OrderMenu orderMenu;

    @JoinColumn(name = "option_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    private Option option;
}