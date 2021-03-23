import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Main from "../../components/Main/Main";
import { postLogin } from "../../lib/Main/index";
import { getMyInfo } from "../../lib/MyPage";

const MainContainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);
    const [name, setName] = useState("");

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

    const getmypage = () => {
        getMyInfo()
            .then((res) => {
                setName(res.data.name);
                console.log(res.data.name);
            })
            .catch((err) => {
                alert("");
            });
    };

    const login = () => {
        postLogin(id, pw)
            .then((res) => {
                const accessToken = res.data.access_token;
                handleLogin();
                setModal(false);
                localStorage.setItem("access_token", accessToken);
                history.push("/");
            })
            .catch((err) => {
                alert("asdf");
                const status = err?.response?.status;

                if (status == 401) {
                    alert("아이디 비밀번호가 일치하지 않습니다.");
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
            getmypage={getmypage}
            name={name}
        />
    );
};
export default MainContainer;
