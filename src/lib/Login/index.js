import axios from "axios";

export const postLogin = (id, pw) => {
    return axios.post("http://192.168.1.17:8088/login", {
        id: id,
        pw: pw,
    });
};
