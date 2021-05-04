import { apiDefault } from "../client";



export const postQRcode = (
    shopId,
    tabno,
    qrCode,
    seatQty,
) => {
    return apiDefault().post("/table",{      
    shopId:shopId,
    no:tabno,
    qrCode:qrCode,
    seatQty:seatQty,
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};



export const getAddQR = (shopId) => {
    return apiDefault().get(`/tableList/${shopId}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const removetables = (
    tabId,
) => {
    return apiDefault().delete(`/table/${tabId}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    })
}