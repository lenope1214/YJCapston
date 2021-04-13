import React, { useEffect, useState } from "react";
import Shopcontent from "../../components/shopcontent/shopcontent";
import { useHistory, useParams } from "react-router-dom";
import {
    getshopinfo,
    getshopmenu,
    postLogin,
} from "../../lib/shopcontent/index";
import Geocode from "react-geocode";
Geocode.setApiKey("AIzaSyBvpJoGP7dKHRovDgP4CSByczdZC7vrz18");
Geocode.setLanguage("kr");
Geocode.setRegion("kr");

const Shopcontentcontainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);
    const [mapModal, setmapModal] = useState(false);
    const [menu, setMenu] = useState([
        {
            name: "",
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
    const [latlagaddress, setlatlagaddress] = useState();
    const [lat, setlat] = useState();
    const [lag, setlag] = useState();

    const openhandleModal = () => {
        setmapModal(true);
    };

    const closehandleModal = () => {
        setmapModal(false);
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

    const handleMenu = (name, price) => {
        let a = 0;
        if (a === 0) {
            setJmMenu([...jmMenu, { name, price, count: 1 }]);
            a = 1;
        }
        for (let i = 0; i < jmMenu.length; i++) {
            if (jmMenu[i].name === name) {
                const copy = [...jmMenu];
                copy[i].count++;
                setJmMenu(copy);
                break;
            }
        }
    };
    // ... < 앞에 있는것을 지우지않고 추가하는 것

    const handleDeleteMenu = (name) => {
        const filteredMenu = jmMenu.filter(({ name: filtername }) => {
            console.log(name, filtername);
            return name !== filtername;
        });
        setJmMenu(filteredMenu);
    };

    const getmenu = () => {
        getshopmenu(param.shopId)
            .then((res) => {
                console.log(res.data);
                const getmenulist = res.data.map((getmenulist) => {
                    return {
                        name: getmenulist.name,
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
                history.push("/shoplist");
            })
            .catch((err) => {
                alert(err);
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
                    alert("로그인서버문제");
                }
            });
    };
    useEffect(() => {
        setTimeout(latlng(), 1000);
    });

    // 위도경도 반환
    const latlng = () => {
        console.log(shopIntro);
        Geocode.fromAddress(shopIntro.address + shopIntro.addressDetail).then(
            (response) => {
                const { lat, lng } = response.results[0].geometry.location;
                setlat(lat);
                setlag(lng);
            },
            (error) => {
                console.error(error);
            }
        );
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
            lat={lat}
            lag={lag}
            mapModal={mapModal}
            openhandleModal={openhandleModal}
            closehandleModal={closehandleModal}
        />
    );
};

export default Shopcontentcontainer;
