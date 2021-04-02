import axios from "axios";
import {apiDefault} from "../client";
const shopid = "1111111111";

export const getShopList = () => {
    return apiDefault().get(`/shop/${shopid}`,{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        }, 
    })
}

// export const getShopInfo = async(shopid) => {
//     return apiDefault().get("/shop",{
//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

// //여기부터 
// export const putShopInfo = (
//     shopid,
//     shopname,
//     intro,
//     open_time,
//     close_time,
//     address,
//     address1,
//     category
//     ) => {
//     return apiDefault().patch(`/shop/${shopid}`,{
//         id: shopid,
//         name: shopname,
//         intro: intro,
//         open_time: open_time,
//         close_time: close_time,
//         address: address,
//         address_detail: address1,
//         category: category,  
//     },{
//         headers: {
//             Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
//         },
//     });
// };

