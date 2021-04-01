import React, { useState, useEffect } from "react";
import Shoplist from "../../components/Shoplist/Shoplist";
import { useHistory } from "react-router-dom";
import { postLogin, getShoplist } from "../../lib/Shoplist/index";
import Shopcontent from "../../components/shopcontent/shopcontent";

const ShoplistContainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);
    const [restaurant, setRestaurant] = useState([
        {
            name: "",
            category: "",
            address: "",
            intro: "",
        },
    ]);

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

    useEffect(() => {
        showShoplist();
    }, []);

    console.log("restaurant :", restaurant);

    const showShoplist = () => {
        getShoplist()
            .then((res) => {
                console.log(res.data);
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.id,
                    };
                });

                setRestaurant(rstrt);
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
                history.push("/shoplist");
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
        <>
            <Shoplist
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
                restaurant={restaurant}
            />
        </>
    );
};

export default ShoplistContainer;
