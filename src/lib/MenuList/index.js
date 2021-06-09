import { apiDefault } from "../client";

export const getMenuList = (shopId) => {
    return apiDefault().get(`/menus/list/${shopId}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};

export const removeMenus = (
    shopId,id
) => {
    alert("삭제완료");
    window.location.reload();
    return apiDefault().delete(`shops/${shopId}/menus/${id}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            },
        });
};


