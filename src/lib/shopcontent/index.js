import { apiDefault } from "../client";

export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
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
