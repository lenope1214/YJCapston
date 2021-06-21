import { apiDefault } from "../client";

export const getshoporder = (shopId) => {
    console.log(shopId);
    const abc = 123;
    return apiDefault().get(`/orders/list/${shopId}`, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

export const getShopInfo = (shopId) => {
    return apiDefault().get(`/shops/${shopId}`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

//-----------------------------

//전일 매출조회
// export const getbeforesearch = (shopId,beforDate) => {
//     return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${beforDate}&&scope=date`,{
//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

//오늘조회
export const getnowsearch = (shopId,nowDate) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${nowDate}&&scope=date`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

//해당 일 조회
export const getdaysearch = (shopId,nowDate1) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${nowDate1}&&scope=date`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

//후일 매출 조회
// export const getnextsearch = (shopId,nextDate) => {
//     return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${nextDate}&&scope=date`,{
//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

//-----------------------------

//이번 주 조회
export const getnowweeksearch = (shopId,nowDate) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${nowDate}&&scope=week`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

//해당 주 조회
export const getweeksearch = (shopId,month3) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${month3}&&scope=week`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

//-----------------------------

//이번 달 조회
export const getnowmonthsearch = (shopId,nowDate) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${nowDate}&&scope=month`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

//해당 월 조회하기 버튼 눌렀을 때
export const getmonthsearch = (shopId,month4) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?date=${month4}&&scope=month`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    })
}
//-----------------------------

//해당 기간조회
export const gettermsearch = (shopId,month,month2) => {
    return apiDefault().get(`/shops/${shopId}/payments/statistics?aDate=${month}&&scope=between&&bDate=${month2}`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
