import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";

import Login from "../../components/Login/Login";
import { postLogin } from "../../lib/Login";

const LoginContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");

    const handleId = (e) => {
        const value = e.target.value;
        setId(value);
    };

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
    };

    const login = async () => {
        axios
            .post("http://10.30.3.91:8088/login", {
                id: id,
                pw: pw,
            })
            .then((res) => history.push("/main"))
            .catch((err) => err);
    };

    return (
        <Login
            id={id}
            pw={pw}
            handleId={handleId}
            handlePw={handlePw}
            login={login}
        />
    );
};

export default LoginContainer;
