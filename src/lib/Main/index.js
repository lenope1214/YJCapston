import { apiDefault } from "../client";

export const postLogin = (id, pw) => {
    return apiDefault().post("/login", {
        id,
        password: pw,
    });
};


export const onSocial = ( // 이거 만들기
    socialId,
    socialType,
    email,
    nickname
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
