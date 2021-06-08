import axios from "axios";
import {apiDefault} from "../client";

export const getmyShop = () => {
    return apiDefault().get("/myShop",{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const id = () => {
    return;
};

