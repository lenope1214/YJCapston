import MenuRead from "../../components/MenuRead/MenuRead";
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
    // menuName,
    menuPrice,
    menuIntro,
    menuId,
    duration,
) => {
    return apiDefault().patch("/menu"
        , {
            // name: menuName,
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
