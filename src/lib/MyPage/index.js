import { apiDefault } from "../client";

export const getMyInfo = () => {
    return apiDefault().get("/user", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const putMypage = (pw) => {
    return apiDefault().put(
        "/user",
        {
            password: pw,
        },
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem(
                    "access_token"
                )}`,
            },
        }
    );
};
