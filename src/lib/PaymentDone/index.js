import axios from "axios";
import { apiDefault } from "../client";

export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const requirepay = () => {
    return axios.post("https://api.iamport.kr/users/getToken", {
        imp_key: "6482381588586854",
        imp_secret:
            "c643c89b874726feb723626183ea2d93b8ca67b5ed31d6c831a04ae2d0b74b13e1ceccb324fc1888",
    });
};
