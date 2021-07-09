import { apiDefault } from "../client";

export const postLogin = (id, password) => {
    return apiDefault().post("/login", {
        id,
        password,
    });
};

export const getshopinfo = (shopId) => {
    return apiDefault().get(`/shops/${shopId}`, {});
};

export const getMyInfo = () => {
    return apiDefault().get("/users", {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const patchorder = (request, people,arriveTime, shopId,amount) => {
    return apiDefault().patch(
        "/orders",
        {
            orderId: localStorage.getItem("orderId"),
            orderRequest: request,
            people: people,
            shopId: shopId,
            amount:amount,
            arriveTime:arriveTime,
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
        "/payments",
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
