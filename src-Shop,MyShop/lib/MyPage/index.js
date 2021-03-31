import axios from "axios";
import { apiDefault } from "../client";

export const getMyInfo = () => {
    return apiDefault().get("/user", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });
};

const instance = axios.create({
    baseURL: "https://api.hnpwa.com",
    timeout: 1000,
});

export const password = () => {
    return;
};

export const putMypage = (pw) => {
    return apiDefault().put(
        "/user",
        {
            password: pw,
        },
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );
};
