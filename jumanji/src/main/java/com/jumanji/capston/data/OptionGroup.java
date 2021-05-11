package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Builder
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

    public static class Request{

    }

    public static class Response{

    }
}
