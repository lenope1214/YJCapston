package com.jumanji.capston.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name="tables") @NoArgsConstructor
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

    @Builder
    public Tab(String tabId, String qrCode, int seatQty){
        this.id = tabId;
        this.qrCode = qrCode;
        this.seatQty = seatQty;
        this.using = 'N';
    }

    public void update(Request request) {
        if(request.getNo()!= request.getChangeNo())this.id = request.getShopId() + String.format("%02d", request.getChangeNo());
        if(request.getSeatQty()!=0)this.seatQty = request.getSeatQty();
        if(request.getQrCode()!=null)this.qrCode = request.getQrCode();
    }

    @Getter @AllArgsConstructor @NoArgsConstructor
    public static class Request{
        private String tabId;
        private String shopId;
        private int no; // 테이블 번호
        private int changeNo; // 바꿀 테이블 번호
        private String qrCode; //code url
        private int seatQty; // 의자 수? 좌석 수?
    }

    @Getter
    public static class Response{
        private String tabId;
        private String shopId;
        private String no;
        private int seatQty;
        private char using;
        private String qrCode;

        public Response(Tab tab){
            this.tabId = tab.getId();
            this.shopId = tab.getId().substring(0, 10);
            this.no = tab.getId().substring(10);
            this.seatQty = tab.getSeatQty();
            this.using = tab.getUsing();
            this.qrCode = tab.getQrCode();
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