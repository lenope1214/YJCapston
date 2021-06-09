import {apiDefault} from "../client";

export const getempList = (shopId) => {
    return apiDefault().get(`/shops/${shopId}/employees/`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
export const getemptimelist = (shopId,date,empNo) => {
    return apiDefault().get(`/shops/${shopId}/work-times?empNo=${empNo}&date=${date}`
    ,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
}