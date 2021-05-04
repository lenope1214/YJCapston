import {apiDefault} from "../client";

export const getempList = (shopId) => {
    return apiDefault().get(`/shop/employee/${shopId}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const removeemps = (
    shopId,
    empNo,
) => {
    alert("삭제완료");
    window.location.reload();
    return apiDefault().delete(`/shop/employee?shopId=${shopId}&empNo=${empNo}`
     
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
       
    })
    
    
}
