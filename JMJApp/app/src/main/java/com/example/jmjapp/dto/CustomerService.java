package com.example.jmjapp.dto;

public class CustomerService {
    int iv_servicemenu;
    int iv_centerdown;
    String tv_service_name;
    String tv_expand_service;

    public CustomerService(int iv_servicemenu, int iv_centerdown, String tv_service_name, String tv_expand_service) {
        this.iv_servicemenu = iv_servicemenu;
        this.iv_centerdown = iv_centerdown;
        this.tv_service_name = tv_service_name;
        this.tv_expand_service = tv_expand_service;
    }

    public int getIv_servicemenu() {
        return iv_servicemenu;
    }

    public void setIv_servicemenu(int iv_servicemenu) {
        this.iv_servicemenu = iv_servicemenu;
    }

    public int getIv_centerdown() {
        return iv_centerdown;
    }

    public void setIv_centerdown(int iv_centerdown) {
        this.iv_centerdown = iv_centerdown;
    }

    public String getTv_service_name() {
        return tv_service_name;
    }

    public void setTv_service_name(String tv_service_name) {
        this.tv_service_name = tv_service_name;
    }

    public String getTv_expand_service() {
        return tv_expand_service;
    }

    public void setTv_expand_service(String tv_expand_service) {
        this.tv_expand_service = tv_expand_service;
    }
}
