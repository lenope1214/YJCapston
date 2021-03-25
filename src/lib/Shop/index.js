import axios from "axios";
import { apiDefault } from "../client";

// export const postShop = () => {
//     return axios().post(
//         "/shop",
//         {

//         })
// }

export const postShop = (
    id,
    shopname,
    intro,
    open_time,
    close_time,
    address,
    address1,
    is_rs_pos
) => {
    return apiDefault().post(
        "/Shop",
        {
            id: id,
            name: shopname,
            intro: intro,
            open_time: open_time,
            close_time: close_time,
            address: address,
            address_detail: address1,
            is_rs_pos: is_rs_pos,
        },
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );
};

// export const getLocation = (query) => {
//     return axios.post(
//         // `https://openapi.naver.com/v1/search/local.json?query=${query}display=5`
//     )
// }
