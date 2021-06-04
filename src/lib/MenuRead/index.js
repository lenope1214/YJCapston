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

export const putMenuPopular = (menuId,shopId) => {
    return apiDefault().patch(`shops/${shopId}/menus/${menuId}/popular` ,{},{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
export const putMenuSale = (menuId,shopId) => {
    return apiDefault().patch(`shops/${shopId}/menus/${menuId}/sale` ,{},{
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
    shopId,
) => {
    return apiDefault().patch("/menus"
        , {           
            price: menuPrice,
            intro: menuIntro,
            menuId: menuId,
            duration: duration,
            shopId:shopId,
        }, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
