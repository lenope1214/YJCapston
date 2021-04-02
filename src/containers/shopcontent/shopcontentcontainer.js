import React, { useEffect, useState } from "react";
import Shopcontent from "../../components/shopcontent/shopcontent";
import { useHistory, useParams } from "react-router-dom";
import {
    getshopinfo,
    getshopmenu,
    postLogin,
} from "../../lib/shopcontent/index";

const Shopcontentcontainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);
    const [menu, setMenu] = useState("");
    const [shopIntro, setShopIntro] = useState({
        name: "",
        category: "",
        address: "",
        addressDetail: "",
        intro: "",
        isOpen: "",
        closeTime: "",
        isRsPos: "",
        openTime: "",
    });

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

    const param = useParams();
    console.log(param);

    useEffect(() => {
        if (param.shopId === undefined) {
            return;
        }
        getinfo(param.shopId);
        getmenu(param.shopId);
    }, [param.shopId]);

    const getinfo = () => {
        getshopinfo(param.shopId)
            .then((res) => {
                console.log(res.data);
                setShopIntro(res.data);
            })
            .catch((err) => {
                alert(err);
            });
    };

    const getmenu = () => {
        getshopmenu(param.shopId)
            .then((res) => {
                console.log(res.data);
            })
            .catch((err) => {
                alert(err);
            });
    };

    const login = () => {
        postLogin(id, pw)
            .then((res) => {
                const accessToken = res.data.access_token;
                handleLogin();
                setModal(false);
                sessionStorage.setItem("access_token", accessToken);
                history.push("/shopcontent");
            })
            .catch((err) => {
                const status = err?.response?.status;

                if (status == 400) {
                    alert(
                        "없는 계정이거나 아이디 비밀번호가 일치하지 않습니다."
                    );
                    sessionStorage.removeItem("access_token");
                    setModal(true);
                } else if (status == 500) {
                    alert("서버 문제");
                } else {
                    alert("서버 off");
                }
            });
    };

    return (
        <Shopcontent
            id={id}
            pw={pw}
            isLogin={isLogin}
            modal={modal}
            logout={handleLogout}
            handleId={handleId}
            handlePw={handlePw}
            login={login}
            openModal={openmodal}
            closeModal={closemodal}
            shopIntro={shopIntro}
        />
    );
};

export default Shopcontentcontainer;
