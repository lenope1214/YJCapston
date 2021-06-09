import { apiDefault } from "../client";

export const getShopOrder = (
    shopId
) => {
    return apiDefault().get(`/shops/${shopId}/pos`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const getShopInfo = (shopId) => {
    return apiDefault().get(`/shops/${shopId}`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};