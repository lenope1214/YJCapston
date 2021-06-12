package yju.wdb.finalterm.dto;

import java.time.LocalDateTime;

public class FriendDTO {
    private Long id;

    private String name;
    private  int birthYear;
    private String gender;
    private String phone;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public FriendDTO(){

    }

    public FriendDTO(String name, int birthYear, String gender, String phone) {
        this.name= name;
        this.birthYear= birthYear;
        this.gender= gender;
        this.phone= phone;
        this.regDate= regDate;
        this.modDate= modDate;
    }

    public FriendDTO(Long id, String name, int birthYear, String gender, String phone, LocalDateTime reDate, LocalDateTime modDate) {
    }

    public Long getId() {
        return id;
    }

    public void setId(String Id) {
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
