import { apiDefault } from "../client";
const shopId = "022344278";

export const getMenuList = () => {
    return apiDefault().get(`/menuList/${shopId}`
    , {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};


