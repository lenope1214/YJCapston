import {apiDefault} from "../client";

export const getempList = (shopId) => {
    return apiDefault().get(`/shops/${shopId}/employees/`
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
    return apiDefault().delete(`/shops/employees?shopId=${shopId}&empNo=${empNo}`
     
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
       
    })
    
    
}
