import axios from "axios";

export const postLogin = (id, pw) => {
    return axios.post("http://192.168.1.17:8088/login", {
        id: id,
        pw: pw,
    });
};

export const postTest = () => {
    return axios.get("http://3.36.160.255:8081/api/allboardlist", {
        headers: {
            Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ1c2VyIiwiaWF0IjoxNjE4NTYyMjA2LCJleHAiOjE2MTg1ODAyMDZ9.MpTrrX4jn472T8bFw0woZQrAz2EKBK8CQdrmLKHIU2oxMBTbRks1hEk4szd8KiyotaBc9RrGG0f4GuWYb-Iy_g"
                `,
        },
    });
};
