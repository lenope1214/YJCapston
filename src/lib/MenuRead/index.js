import { apiDefault } from "../client";

export const getMenuRead = (
    menuId
) => {
    return apiDefault().get(`/menus/${menuId}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};

export const putMenuPopular = (menuId) => {
    return apiDefault().patch(`/menus/${menuId}/popular` ,{},{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
export const putMenuSale = (menuId) => {
    return apiDefault().patch(`/menus/${menuId}/sale` ,{},{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const putMenuRead = (
    menuPrice,
    menuIntro,
    menuId,
    duration,
) => {
    return apiDefault().patch("/menu"
        , {
            price: menuPrice,
            intro: menuIntro,
            menuId: menuId,
            duration: duration,
        }, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
