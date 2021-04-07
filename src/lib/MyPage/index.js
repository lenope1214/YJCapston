import { apiDefault } from "../client";

export const getMyInfo = () => {
    return apiDefault().get("/user", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const putMypage = (pw, roadAddr, addressDetail) => {
    return apiDefault().patch(
        "/user",
        {
            password: pw,
            address: roadAddr,
            addressDetail: addressDetail,
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
