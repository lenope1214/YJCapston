import { apiDefault } from "../client";
import axios from "axios";

export const getMyInfo = () => {
    return apiDefault().get("/users", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const getShopInfo = (shopid) => {
    return apiDefault().get(`/shops/${shopid}`, {});
};
