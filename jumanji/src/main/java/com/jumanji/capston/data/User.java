package com.jumanji.capston.data;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.Ignore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="users")
public class User implements Serializable {
    @Id
    private String id; //아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
    private String address; // 주소
    @Column(name = "address_detail")
    private String addressDetail;
    private Date birthday; // 생년월일
    private String phone; // 전화번호
    @Column(name = "is_wdrw")
    private char isWdrw; // 탈퇴여부
    private String role; // 권한   u, o, a
    @DateTimeFormat(pattern = "yyyyMMdd")
    @Column(name = "sign_date")
    private Date signDate; // 가입날짜
    @Column(name = "vip_level")
    private int level; // 등급
    private int point; // 포인트
    private String provider; // 소셜
    @Column(name = "provider_id")
    private String providerId; // 해당 소셜에서의 아이디(primary key)
    @Column(name = "device_token")
    private String deviceToken;

//    @Builder(builderMethodName = "updateInfo")
//    public User(String email, String address, String addressDetail){
//        this.email = email;
//        this.address = address;
//        this.addressDetail = addressDetail;
//    }

    public String encodeId(){
        return new BCryptPasswordEncoder().encode(this.id);
    }
    @Builder(builderMethodName = "createUser")
    public User(String id, String password, String address, String addressDetail, String name, Date birthday, String role, String email, Date sign_date, String provider, String provider_id, String phone, String deviceToken) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.addressDetail = addressDetail;
        this.role = role;
        this.signDate = sign_date;
        this.provider = provider;
        this.birthday = birthday;
        this.providerId = provider_id;
        this.phone = phone;
        this.point = 0;
        this.level = 1;
        this.deviceToken = deviceToken;
    }

    @Getter @Setter
    public static class Request {
        private String id; //아이디
        private String password; // 비밀번호
        private String name; // 이름
        private String email; // 이메일
        private String address; // 주소
        private String addressDetail; // 상세주소
        private Date birthday; // 생년월일
        private String phone; // 전화번호
        private String role; // 권한   ROLE_USER, ROLE_OWNER, ROLE_ADMIN
        private String deviceToken;
    }

    @Getter @NoArgsConstructor
    public static class Response{
        private String id;
        private String name;
        private String email;
        private String address;
        private String addressDetail;
        private String birthday; // 생년월일
        private String phone; // 전화번호
        private String role; // 권한   user, owner, admin
        private String signDate; // 가입날짜
        private int level; // 등급
        private int point; // 포인트
        private String deviceToken;

        public Response(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.address = user.getAddress();
            this.addressDetail = user.getAddressDetail();
            if(user.birthday != null)this.birthday = DateOperator.dateToYYYYMMDD(user.getBirthday(), true);
            if(user.email != null)this.email = user.getEmail();
            this.phone = user.phone;
            this.role = user.getRole();
            this.level = user.getLevel();
            this.point = user.getPoint();
            this.signDate = DateOperator.dateToYYYYMMDD(user.getSignDate(), true);
            this.deviceToken = user.getDeviceToken();
        }
    }

    @Getter
    public static class MyInfo{
        private Response user = null;
        private List<Order.Response> orderList;

        public MyInfo(User user, List<Order> orderList){
            this.user = new Response(user);
            List<Order.Response> orderResponseList = new ArrayList<>();
            for(Order o : orderList){
                orderResponseList.add(new Order.Response(o));
            }
            this.orderList = orderResponseList;
        }
    }
}




//Test 용. Column 어노테이션 없어도 테이블에 추가 되는가?
// Column 어노테이션이 없어도 잘 됨.
