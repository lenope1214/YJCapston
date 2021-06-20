import { apiDefault } from "../client";

export const getShopOrder = (shopId) => {
    return apiDefault().get(`/shops/${shopId}/pos`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const getShopInfo = (shopId) => {
    return apiDefault().get(`/shops/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const gettablelength = (shopId) => {
    return apiDefault().get(`/tables/list/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const getorderlist = (shopId) => {
    return apiDefault().get(`/orders/list/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
