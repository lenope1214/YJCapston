import { apiDefault } from "../client";

export const getMenuRead = async (shopId) => {
    return apiDefault().get(`/menu/${shopId}`
    , {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

