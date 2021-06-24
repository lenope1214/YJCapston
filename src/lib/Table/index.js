import { apiDefault } from "../../lib/client";

export const getshopmenu = (shopId) => {
    return apiDefault().get(`/menus/list/${shopId}`, {});
};

export const postorder = (shopId, tableNo, people, allPrice, orderId) => {
    console.log(orderId);
    return apiDefault().patch(
        `/orders`,
        {
            shopId: shopId,
            people: people,
            tabNo: parseInt(tableNo),
            amount: allPrice,
            orderId: orderId,
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

export const postorder1 = (shopId, tableNo, people, allPrice, orderId) => {
    console.log(orderId);
    return apiDefault().post(
        `/orders`,
        {
            shopId: shopId,
            people: people,
            tabNo: parseInt(tableNo),
            amount: allPrice,
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

export const postordermenu = (postmenulist) => {
    return apiDefault().post(
        "/order-menus",
        { list: postmenulist },
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem(
                    "access_token"
                )}`,
            },
        }
    );
};

export const getorderlist = (shopId) => {
    return apiDefault().get(`/orders/list/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const patchordermenu = (postmenulist) => {
    return apiDefault().patch(
        "/order-menus",
        { list: postmenulist },
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem(
                    "access_token"
                )}`,
            },
        }
    );
};

// export const paymentdone = (orderId) => {
//     return apiDefault().post(
//         "/payments/complite",
//         { orderId: orderId },
//         {
//             headers: {
//                 Authorization: `Bearer ${sessionStorage.getItem(
//                     "access_token"
//                 )}`,
//             },
//         }
//     );
// };

export const paymentdone = (orderId) => {
    return apiDefault().post("/payments/complete",{
        orderId:orderId
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },  
    });
};