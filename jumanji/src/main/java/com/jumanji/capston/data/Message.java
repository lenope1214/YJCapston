package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="messages")
public class Message {
    @EmbeddedId
    private MessageId id;
    @Column(length = 250)
    private String content;//내용

}


