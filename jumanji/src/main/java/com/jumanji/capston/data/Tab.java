package com.jumanji.capston.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name="tables") @NoArgsConstructor @ToString
public class Tab implements Serializable {

//    @EmbeddedId
//    private TabId id;
    @Id
    @Column(length = 12)
    private String id;

    @Column(name = "seat_qty", length = 2)
    private int seatQty; // 좌석수
    @Column(name = "qr_code")
    private String qrCode;
    @Column
    private char using;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;


    @Builder
    public Tab(String tabId, String qrCode, int seatQty){
        this.id = tabId;
        this.qrCode = qrCode;
        this.seatQty = seatQty;
        this.using = 'N';
    }

    public void update(Request request) {
        if(request.getSeatQty()!=0)this.seatQty = request.getSeatQty();
        if(request.getQrCode()!=null)this.qrCode = request.getQrCode();
        if(request.getUsing() != ' ')this.using = request.getUsing();
    }

    public void setOrder(Order order){
        this.order = order;
    }

    @Getter @AllArgsConstructor @NoArgsConstructor
    public static class Request{
        private String tabId;
        private String shopId;
        private int no; // 테이블 번호
        private String qrCode; //code url
        private int seatQty; // 의자 수? 좌석 수?
        private String orderId;
        private char using;
    }

    @Getter
    public static class Response{
        private String tabId;
        private String shopId;
        private String no;
        private int seatQty;
        private char using;
        private String qrCode;
        private Long orderId;

        public Response(Tab tab){
            this.tabId = tab.getId();
            this.shopId = tab.getId().substring(0, 10);
            this.no = tab.getId().substring(10);
            this.seatQty = tab.getSeatQty();
            this.using = tab.getUsing();
            this.qrCode = tab.getQrCode();
            if(tab.getOrder() != null)this.orderId = tab.getOrder().getId().getTime();
        }
    }

}
//
//@Getter
//@Setter
//@EqualsAndHashCode
//@Embeddable
//public class TabId implements Serializable{
//
//    @Column(length = 2, name="tab_id")
//    private int id ; // 좌석번호
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="shop_id")
//    private Shop shop_id; // 매장번호
//
//    public TabId(){}
//}