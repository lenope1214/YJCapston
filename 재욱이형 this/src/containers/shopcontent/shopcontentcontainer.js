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
    const [menu, setMenu] = useState([
        {
            id: "",
            img: "",
            intro: "",
            price: "",
        },
    ]);
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
        imgPath: "",
    });

    const [priceSum, setPriceSum] = useState(0);
    const [jmMenu, setJmMenu] = useState([]);

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

    const handleMenu = (id, price) => {
        let a = 0;
        if (a === 0) {
            setJmMenu([...jmMenu, { id, price, count: 1 }]);
            a = 1;
        }
        for (let i = 0; i < jmMenu.length; i++) {
            if (jmMenu[i].id === id) {
                const copy = [...jmMenu];
                copy[i].count++;
                setJmMenu(copy);
                break;
            }
        }
    };
    // ... < 앞에 있는것을 지우지않고 추가하는 것

    const handleDeleteMenu = (id) => {
        const filteredMenu = jmMenu.filter(({ id: filterId }) => {
            console.log(id, filterId);
            return id !== filterId;
        });
        setJmMenu(filteredMenu);
    };

    const getmenu = () => {
        getshopmenu(param.shopId)
            .then((res) => {
                console.log(res.data);
                const getmenulist = res.data.map((getmenulist) => {
                    return {
                        id: getmenulist.id,
                        img: getmenulist.imgPath,
                        intro: getmenulist.intro,
                        price: getmenulist.price,
                    };
                });
                setMenu(getmenulist);
                console.log(menu);
            })
            .catch((err) => {
                console.log(err);
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
            menu={menu}
            handleMenu={handleMenu}
            jmMenu={jmMenu}
            handleDeleteMenu={handleDeleteMenu}
        />
    );
};

export default Shopcontentcontainer;
