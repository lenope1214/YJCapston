import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Main from "../../components/Main/Main";

const MainContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [isLogin, setIsLogin] = useState(false);

    const IsLogin = () => {
        setIsLogin(true);
    };

    const logout = () => {
        setIsLogin(false);
    };

    const [modal, setModal] = useState(false);

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
        axios
            .post("http://122.202.45.37:8088/api/login", {
                id: id,
                pw: pw,
            })
            .then((res) => {
                IsLogin();
                setModal(false);
                history.push("/");
            })
            .catch((err) => err);
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
