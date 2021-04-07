import axios from "axios";
import { apiDefault } from "../client";

export const postMenu = (
    formData,
    menuname,
    price,
    menudesc,
    shopId,
) => {
    alert("postMenu함수 실행");
    return apiDefault().post("/menu", {
        formData,
        name: menuname,
        price: price,
        // img: formData,
        intro: menudesc,
        shopId: shopId,
        
    }, {
        headers: {
            
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            "content-type": "multipart/form-data",
        },
    });
};