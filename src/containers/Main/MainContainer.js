import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Main from "../../components/Main/Main";
import { postLogin } from "../../lib/Main/index";

const MainContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [isLogin, setIsLogin] = useState(false);
    const [modal, setModal] = useState(false);

    const IsLogin = () => {
        setIsLogin(true);
    };

    const logout = () => {
        setIsLogin(false);
    };

    const openmodal = () => {
        setModal(true);
    };

    const closemodal = () => {
        setModal(false);
    };

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
            .then((err) => err)
            .catch((res) => {
                IsLogin();
                setModal(false);
                history.push("/");
            });
    };

    return (
        <Main
            id={id}
            pw={pw}
            handleId={handleId}
            handlePw={handlePw}
            login={login}
            isLogin={isLogin}
            modal={modal}
            openModal={openmodal}
            closeModal={closemodal}
            logout={logout}
        />
    );
};
export default MainContainer;
