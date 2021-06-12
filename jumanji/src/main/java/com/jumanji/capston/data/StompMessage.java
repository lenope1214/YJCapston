package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString @Getter
@Entity
@Table(name = "messages")
public class StompMessage {
    @Id
    private BigDecimal id;
    private String type;
    private String message;
    private String roomId;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shop shopId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userId;
}
