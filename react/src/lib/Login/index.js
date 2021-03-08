import axios from "axios";

export const postLogin = (id, pw) => {
    return axios.post("http://10.30.3.91:8088/login", {
        id: id,
        pw: pw,
    });
};
