import { apiDefault } from "../client";
export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const getReviewlist = (id) => {
    return apiDefault().get(`/reviews/${id}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const removeReviews = (
    id
) => {
    return apiDefault().delete(`/reviews/${id}`
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
    return apiDefault().get(`/menus/list/${shopId}`, {});
};

export const getshopinfo2 = (shopId) => { 
    return apiDefault().get(`/shops/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const getshopinfo = (shopId) => {
    return apiDefault().get(`/shops/${shopId}`, {
        // headers: {
        //     Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        // },
    });
};

export const cartNumber = (shopId) => {
    return apiDefault().post(
        "/orders",
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
export const postMark = (shopId) => {
    return apiDefault().post(`/marks`,
    {
        shopId:shopId
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const deleteMark = (shopId) => {
    return apiDefault().delete(`/marks/${shopId}`,
    {
    
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};