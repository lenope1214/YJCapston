import { apiDefault } from "../client";
const shopId = "0223446783";

export const getMenuList = () => {
    return apiDefault().get(`shop/${shopId}/menuList`
    , {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};



