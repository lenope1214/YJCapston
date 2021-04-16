import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

import Login from "../../components/Login/Login";
import { postLogin, postTest } from "../../lib/Login";

const LoginContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [a, seta] = useState([{}]);

    useEffect(() => {
        postTest();
    });
    const handleId = (e) => {
        const value = e.target.value;
        setId(value);
    };

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
    };

    const login = async () => {
        postLogin(id, pw)
            .then((res) => history.push("/main"))
            .catch((err) => err);
    };

    const test = () => {
        postTest()
            .then((res) => {
                const c = res.data.map((c) => {
                    return {
                        b_content: c.b_content,
                        b_dtt: c.b_dtt,
                    };
                });
                seta(c);
            })
            .catch((err) => console.log(err));
    };
    console.log(a);

    return (
        <Login
            id={id}
            pw={pw}
            handleId={handleId}
            handlePw={handlePw}
            login={login}
            test={test}
            a={a}
        />
    );
};

export default LoginContainer;
