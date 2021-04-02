import axios from "axios";
import { apiDefault } from "../client";

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
    return apiDefault().post("/join", {
        id: member_id,
        password: pw,
        name: username,
        phone: phone,
        role: auth,
        email: email,
        birthday: birthday,
        address: address,
        addressDetail: address1,
    });
};

export const getLocation = (keyword) => {
    return apiDefault().get(
        `/searchAddr?keyword=${keyword}&currentPage=1&countPerPage=5`
    );
};

export const getIdCheck = (member_id) => {
    return apiDefault().get(`/validate/${member_id}`, {
        id: member_id,
    });
};
