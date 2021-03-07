import axios from "axios";

export const postLogin = (id, pw) => {
    return axios.post("http://122.202.45.37:8088/rest/join", {
        id: id,
        pw: pw,
    });
};
