import axios from "axios";
import {apiDefault} from "../client";

export const getmyShop = () => {
    return apiDefault().get("/users/shops",{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const removeShops = (
    shopId
) => {
    return apiDefault().delete(`/shops/${shopId}`
        , {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

