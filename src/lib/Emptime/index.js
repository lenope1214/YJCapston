import {apiDefault} from"../client";

export const getEmpInfo = (shopId,empNo) => {
    return apiDefault().get(`/shops/${shopId}/employees/${empNo}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    })
}


export const postWorkstart = (
    shopId,
    empNo,
    ) => {
    return apiDefault().post(`/shops/employees/work-start`
     ,{
       shopId: shopId,
       empNo: empNo,  
     },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const postWorkend = (
    shopId,
    empNo,
    ) => {
    return apiDefault().post(`/shops/employees/work-finish`
     ,{
       shopId: shopId,
       empNo: empNo,  
     },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};



