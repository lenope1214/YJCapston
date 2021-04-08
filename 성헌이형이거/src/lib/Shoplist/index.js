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

export const getShoplistkorean = () => {
    return apiDefault().get(`shopList/한식`, {});
};

export const getShoplistchinese = () => {
    return apiDefault().get(`shopList/중식`, {});
};

export const getShoplistjapan = () => {
    return apiDefault().get(`shopList/일식`, {});
};

export const getShoplistforign = () => {
    return apiDefault().get(`shopList/양식`, {});
};

export const getShoplistdrink = () => {
    return apiDefault().get(`shopList/술집`, {});
};

export const getShoplistboon = () => {
    return apiDefault().get(`shopList/분식`, {});
};

export const getShoplistmeat = () => {
    return apiDefault().get(`shopList/고기`, {});
};

export const getShoplistjjimtang = () => {
    return apiDefault().get(`shopList/찜.탕`, {});
};

export const getShoplistcafe = () => {
    return apiDefault().get(`shopList/카페디저트`, {});
};

export const getShoplistfastfood = () => {
    return apiDefault().get(`shopList/패스트푸드`, {});
};
