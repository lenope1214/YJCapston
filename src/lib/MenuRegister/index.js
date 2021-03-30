import axios from "axios";
import { apiDefault } from "../client";

export const postMenu = (
    menuname,
    price,
    img,
    menudesc,
    shopId
) => {
    alert("postMenu함수 실행");
    return apiDefault().post("/menu", {
        name: menuname,
        price: price,
        img: img,
        intro: menudesc,
        shopId: '022344278'
    }, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};