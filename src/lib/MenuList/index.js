import { apiDefault } from "../client";

export const getMenuList = (shopId) => {
    return apiDefault().get(`/menuList/${shopId}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};

export const removeMenues = (
    id
) => {
    return apiDefault().delete("/menu"
        , {
            data: { menuId: id },
        }, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};


