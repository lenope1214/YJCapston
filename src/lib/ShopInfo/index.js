import axios from "axios";
import {apiDefault} from "../client";
// const shopid = "1111111111";

export const getShopInfo = (shopId) => {
    return apiDefault().get(`/shop/${shopId}`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const putShopreserve = (shopId) => {
    return apiDefault().patch(`/shop/${shopId}/reserve` ,{},{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const putShopopen = (shopId) => {
    return apiDefault().patch(`/shop/${shopId}/open` ,{},{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    });
};

export const putShopInfo = (
    shopId,
    shopIntro,
    shopOpenTime,
    shopCloseTime,
    // shopAddress,
    roadAddr,
    shopAddressDetail,
    // shopCategory,
    
) => {
    return apiDefault().patch("/shop"
    ,{
        shopId:shopId,
        intro:shopIntro,
        openTime:shopOpenTime,
        closeTime:shopCloseTime,
        address:roadAddr,
        addressDetail:shopAddressDetail,
        // category:shopCategory
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,    
        },
    });
};

// export const getShopInfo = async(shopid) =>

//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

// //여기부터 
// export const putShopList = (
//     shopid,
//     shopname,
//     intro,
//     open_time,
//     close_time,
//     address,
//     address_detail,
//     category
//     ) => {
//     return apiDefault().patch("/shop",{
//         shopId: shopid,
//         name: shopname,
//         intro: intro,
//         openTime: open_time,
//         closeTime: close_time,
//         address: address,
//         addressDetail: address_detail,
//         category: category,  
//     },{
//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

// export const putShopreserve = (
//     shopId,
//     shopreserve
// ) => {
//     return apiDefault().patch(`/shop/${shopId}/reserve`
//         ,{
//             shopId:shopId,
//             isRsPos:shopreserve
//         },{
//             headers: {
//                 Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//             },
//         });
// };
