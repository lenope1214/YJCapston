import { apiDefault } from "../client";

export const getShopOrder = (
    shopId
) => {
    return apiDefault().get(`/shop/${shopId}/pos`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};