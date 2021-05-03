import axios from "axios";
import { apiDefault } from "../client";

export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const requirepay = () => {
    return apiDefault().post("/think", {});
};
