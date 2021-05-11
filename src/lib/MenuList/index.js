import { apiDefault } from "../client";

export const getMenuList = (shopId) => {
    return apiDefault().get(`/menus/list/${shopId}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};

export const removeMenues = (
    id
) => {
    return apiDefault().delete(`/menus/${id}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};


