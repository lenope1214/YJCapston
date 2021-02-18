package com.codesample.mymug.data;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // int. autoincrement
    private int menuid;
    @ManyToOne(targetEntity=Crew.class)
    @JoinColumn(name="crew")
    private Crew crew;
    @Column(nullable=false,length=100)
    private String name;
    @Column
    private int price;
}