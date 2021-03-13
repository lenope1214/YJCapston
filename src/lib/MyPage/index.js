import { apiDefault } from "../client";

export const getMyInfo = () => {
    return apiDefault().get("/mypage", {
        headers: {
            Authorization: `Berear ${localStorage.getItem("access_token")}`,
        },
    });
};
