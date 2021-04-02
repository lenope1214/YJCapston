import MenuRead from "../../components/MenuRead/MenuRead";
import { apiDefault } from "../client";

export const getMenuRead = async (shopId) => {
    return apiDefault().get(`/menu/${shopId}`
    , {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const putMenuRead = (
    menuName,
    menuPrice,
    menuIntro,
    menuId,
    ) => {
    return apiDefault().patch("/menu"
    , {
        name: menuName,
        price: menuPrice,
        intro: menuIntro,
        menuId: menuId,
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
