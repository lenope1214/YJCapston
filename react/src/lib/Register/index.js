import axios from "axios";

export const postRegister = (
    member_id,
    pw,
    username,
    phone,
    auth,
    email,
    birthday,
    address,
    address1
) => {
    return axios.post("http://122.202.45.37:8088/api/join", {
        id: member_id,
        pw: pw,
        name: username,
        phone: phone,
        check: auth,
        email: email,
        birthday: birthday,
        address: address,
        address_detail: address1,
    });
};

export const getLocation = (query) => {
    return axios.post(
        `https://openapi.naver.com/v1/search/local.json?query=${query}display=5`
    );
};
