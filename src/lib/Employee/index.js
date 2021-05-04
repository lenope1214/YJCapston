import { apiDefault } from "../client";

export const postEmp = (
    shopId,
    empNo,
    empName,
    birthday,
    hiredate,
    phone,
    gender,
) => {
    return apiDefault().post("/shop/employee",{
    shopId:shopId,
    empNo:empNo,
    empName:empName,
    birthday:birthday,
    hiredate:hiredate,
    phone:phone,
    gender:gender,    
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};