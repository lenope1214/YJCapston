package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @NoArgsConstructor @EqualsAndHashCode
@Entity
@Table(name="messages")
@IdClass(Publish.class)
public class Message implements Serializable {
//    @EmbeddedId
//    private MessageId id;
    @Id
    @JsonIgnore
    @JoinColumn(updatable = false)
    @ManyToOne
    private Publish publish;

    @Column(length = 250)
    private String content;//내용

    public class Request{

    }

}


