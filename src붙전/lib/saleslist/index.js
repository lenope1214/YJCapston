import { apiDefault } from "../client";

export const getshoporder = (shopId) => {
    console.log(shopId);
    const abc = 123;
    return apiDefault().get(`/orders/list/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
