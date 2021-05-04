import { apiDefault } from "../client";

export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const getshopinfo = (shopId) => {
    return apiDefault().get(`/shop/${shopId}`, {});
};

export const getMyInfo = () => {
    return apiDefault().get("/user", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const patchorder = (request, people) => {
    return apiDefault().patch(
        "/order",
        {
            orderId: localStorage.getItem("orderId"),
            orderRequest: request,
            people: people,
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
export const paymentservice = (jmallprice, pointcheck) => {
    console.log(Math.floor((jmallprice - pointcheck) * 0.01));
    return apiDefault().post(
        "/payment",
        {
            orderId: localStorage.getItem("orderId"),
            pg: "inisis",
            payMethod: "card",
            amount: jmallprice - pointcheck,
            // point: (jmallprice - pointcheck) * 0.01,
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
