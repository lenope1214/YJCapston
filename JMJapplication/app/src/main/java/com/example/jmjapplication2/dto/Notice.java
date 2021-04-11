package com.example.jmjapplication2.dto;

public class Notice {
    private int iv_noticemenu;
    private int iv_noticedown;
    private String tv_notice_name;
    private String tv_notice_intro;

    public Notice(int iv_noticemenu, int iv_noticedown, String tv_notice_name, String tv_notice_intro) {
        this.iv_noticemenu = iv_noticemenu;
        this.iv_noticedown = iv_noticedown;
        this.tv_notice_name = tv_notice_name;
        this.tv_notice_intro = tv_notice_intro;
    }

    public int getIv_noticemenu() {
        return iv_noticemenu;
    }

    public void setIv_noticemenu(int iv_noticemenu) {
        this.iv_noticemenu = iv_noticemenu;
    }

    public int getIv_noticedown() {
        return iv_noticedown;
    }

    public void setIv_noticedown(int iv_noticedown) {
        this.iv_noticedown = iv_noticedown;
    }

    public String getTv_notice_name() {
        return tv_notice_name;
    }

    public void setTv_notice_name(String tv_notice_name) {
        this.tv_notice_name = tv_notice_name;
    }

    public String getTv_notice_intro() {
        return tv_notice_intro;
    }

    public void setTv_notice_intro(String tv_notice_intro) {
        this.tv_notice_intro = tv_notice_intro;
    }
}
