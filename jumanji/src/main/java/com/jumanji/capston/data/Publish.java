package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @NoArgsConstructor
public class Publish implements Serializable {
    @Id
    @Column(length = 8)
    private String id;

    @JsonIgnore
    @JoinColumn(updatable = false)
    @ManyToOne
    private User user;

    @JsonIgnore
    @JoinColumn(updatable = false)
    @ManyToOne
    private Shop shop;

}
