import { apiDefault } from "../client";



export const postQRcode = (
    shopId,
    tabno,
    qrCode,
    seatQty,
) => {
    return apiDefault().post("/tables",{      
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
    return apiDefault().get(`/tables/list/${shopId}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const removetables = (
    tabId,
) => {
    return apiDefault().delete(`/tables/${tabId}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    })
}