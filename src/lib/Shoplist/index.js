import { apiDefault } from "../client";

export const postLogin = (id, pw) => {
    return apiDefault().post("/login", {
        id,
        password: pw,
    });
};

export const getShoplist = () => {
    return apiDefault().get("/shops/list", {});
};

export const getShoplistkorean = () => {
    return apiDefault().get(`shops/list?category=한식&sortTarget=score`, {});
};

export const getShoplistchinese = () => {
    return apiDefault().get(`shops/list?category=중식&sortTarget=score`, {});
};

export const getShoplistjapan = () => {
    return apiDefault().get(`shops/list?category=일식&sortTarget=score`, {});
};

export const getShoplistforign = () => {
    return apiDefault().get(`shops/list?category=양식&sortTarget=score`, {});
};

export const getShoplistdrink = () => {
    return apiDefault().get(`shops/list?category=술집&sortTarget=score`, {});
};

export const getShoplistboon = () => {
    return apiDefault().get(`shops/list?category=분식&sortTarget=score`, {});
};

export const getShoplistmeat = () => {
    return apiDefault().get(`shops/list?category=고기&sortTarget=score`, {});
};

export const getShoplistjjimtang = () => {
    return apiDefault().get(`shops/list?category=찜.탕&sortTarget=score`, {});
};

export const getShoplistcafe = () => {
    return apiDefault().get(`shops/list?category=카페.디저트&sortTarget=score`, {});
};

export const getShoplistfastfood = () => {
    return apiDefault().get(`shops/list?category=패스트푸드&sortTarget=score`, {});
};

export const getShoplistMarks = () => {
    return apiDefault().get("marks",{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    })
}