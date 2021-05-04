import { apiDefault } from "../client";
import axios from "axios";

export const getMyInfo = () => {
    return apiDefault().get("/user", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const Mypageorder = () => {
    return apiDefault().get("/order", {
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

export const ordermenulist = (id) => {
    return apiDefault().get(`/order/${id}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const requirelist = (id) => {
    return axios.get(`http://3.34.55.186:8088/iamport/cancel?m_id=${id}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
