import { apiDefault } from "../client";
import axios from "axios";

export const getMyInfo = () => {
    return apiDefault().get("/users", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const Mypageorder = () => {
    return apiDefault().get("/orders/list", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const putMypage = (pw, roadAddr, addressDetail) => {
    return apiDefault().patch(
        "/users",
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
    return apiDefault().get(`/orders/${id}`, {
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

export const requirelist2 = (id, shopId) => {
    return axios.get(
        `http://3.34.55.186:8088/iamport/shop/cancel?shop_id=${shopId}&m_id=${id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
        }
    );
};
