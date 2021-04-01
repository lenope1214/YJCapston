import { apiDefault } from "../client";

export const postLogin = (img) => {
    return apiDefault().post("/uploadTest01", { img });
};
