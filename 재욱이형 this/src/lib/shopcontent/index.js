import { apiDefault } from "../client";

export const postLogin = (id, pw) => {
    return apiDefault().post("/login", {
        id,
        password: pw,
    });
};

export const getShoplist = () => {
    return apiDefault().get("/shopList", {});
};

export const getshopmenu = (shopId) => {
    return apiDefault().get(`/menuList/${shopId}`, {});
};

export const getshopinfo = (shopId) => {
    return apiDefault().get(`/shop/${shopId}`, {});
};
