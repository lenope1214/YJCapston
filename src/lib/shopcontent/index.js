import { apiDefault } from "../client";
import React from "react";
export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const getReviewlist = (id) => {
    return apiDefault().get(`/review/${id}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const removeReviews = (
    id
) => {
    return apiDefault().delete(`/review/${id}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
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

export const cartNumber = (shopId) => {
    return apiDefault().post(
        "/order",
        {
            shopId: shopId,
        },
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem(
                    "access_token"
                )}`,
            },
        }
    );
};
