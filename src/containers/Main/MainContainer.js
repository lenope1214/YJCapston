import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Main from "../../components/Main/Main";
import { postLogin } from "../../lib/Main/index";

const MainContainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
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

    const login = () => {
        postLogin(id, pw)
            .then((res) => {
                // const accessToken = res.data.access_token;

                handleLogin();
                setModal(false);
                // localStorage.setItem("access_token", accessToken);
                history.push("/");
            })
            .catch((err) => {
                alert("이성 ㅂ일 안ㅇ함 ");
                const status = err?.response?.status;

                if (status == 401) {
                    alert("로그이 ㄴ다시 해주");
                    handleLogout();
                    localStorage.removeItem("access_token");
                    setModal(true);
                }
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
            logout={handleLogout}
        />
    );
};
export default MainContainer;
