import axios from "axios";
import { apiDefault } from "../client";

export const postMenu = (
    formData,
    menuname,
    price,
    menudesc,
) => {
    alert("postMenu함수 실행");
    return apiDefault().post("/menus", {
        formData,
        name: menuname,
        price: price,
        // img: formData,
        intro: menudesc,
        shopId: '0223446783',
        
    }, {
        headers: {
            
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            "content-type": "multipart/form-data",
        },
    });
};