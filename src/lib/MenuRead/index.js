import { apiDefault } from "../client";

export const getMenuRead = (
    menuId
) => {
    return apiDefault().get(`/menu/${menuId}`
        , {
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
