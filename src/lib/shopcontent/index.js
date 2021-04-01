import { apiDefault } from "../client";

export const postLogin = (id, pw) => {
    return apiDefault().post("/login", {
        id,
        password: pw,
    });
};

export const getshopid = (shopId) => {
    return apiDefault().get(`/menu/${shopId}`, {});
};
