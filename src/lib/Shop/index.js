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
    address1,
    // is_rs_pos,
    category,
    img_url
) => {
    return  apiDefault().post("/shop",{
     id:id,
     name:shopname,
     intro:intro,
     openTime:open_time,
     closeTime:close_time,
     address:address,
     addressDetail:address1,
     category: category,
    // shopId: id,
    // name: shopname,
    // intro: intro,
    // openTime: open_time,
    // closeTime: close_time,
    // address: address,
    // addressDetail: address1,
    // isRsPos: is_rs_pos,
    // category: category,  
    img:img_url
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