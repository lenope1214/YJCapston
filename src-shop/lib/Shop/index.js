import axios from "axios";
import { apiDefault } from "../client";


// export const postShop = () => {
//     return axios().post(
//         "/shop",
//         {

//         })
// }

export const postShop =(
    id,
    shopname,
    intro,
    open_time,
    close_time,
    address,
    addressDetail,
    is_rs_pos
) => {
    
    return  apiDefault().post("/shop",{
    id: id,
    name: shopname,
    intro: intro,
    openTime: open_time,
    closeTime: close_time,
    address: address,
    addressDetail: addressDetail,
    isRsPos: is_rs_pos
    },{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};

// export const getLocation = (query) => {
//     return axios.post(
//         // `https://openapi.naver.com/v1/search/local.json?query=${query}display=5`
//     )
// }