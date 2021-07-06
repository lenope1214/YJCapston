import React, { useEffect, useState } from "react";

import Shopcontent from "../../components/shopcontent/shopcontent";
import { useHistory, useParams } from "react-router-dom";
import {
    getshopinfo,
    getshopinfo2,
    getshopmenu,
    postLogin,
    cartNumber,
    getReviewlist,
    postMark,
    deleteMark,
} from "../../lib/shopcontent/index";
import Geocode from "react-geocode";
import { removeReviews } from "../../lib/shopcontent/index";
import Swal from 'sweetalert2';

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

    const [jmMenu, setJmMenu] = useState([]);

    const [lat, setlat] = useState();
    const [lag, setlag] = useState();
    const [pricesum, setpricesum] = useState([]);
    const [shopId, setShopId] = useState();
    const [reviewList, setReviewList] = useState([{}]);

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

    useEffect(() => {
        if (param.shopId === undefined) {
            return;
        }

        getinfo(param.shopId);
        getmenu(param.shopId);
        setShopId(param.shopId);
    }, [param.shopId]);

    const getinfo = () => {
        if (!sessionStorage.getItem("access_token")) {
            getshopinfo(param.shopId)
                .then((res) => {
                    setShopIntro(res.data);
                })
                .catch((err) => {
                    console.log(err);
                });
        } else {
            getshopinfo2(param.shopId)
                .then((res) => {
                    setShopIntro(res.data);
                })

                .catch((err) => {
                    console.log(err);
                });
        }
    };

    let sum = 0;
    const handleMenu = (name, price) => {
        let a = 1;

        if (a === 1) {
            setJmMenu([...jmMenu, { name, price, count: 1 }]);
            a = 2;
        }

        for (let i = 0; i < jmMenu.length; i++) {
            if (jmMenu[i].name === name) {
                const copy = [...jmMenu];
                copy[i].count++;
                sum = jmMenu[i].count * jmMenu[i].price;
                setJmMenu(copy);
                break;
            }
        }

        console.log(pricesum);
    };

    // ... < 앞에 있는것을 지우지않고 추가하는 것

    const handleDeleteMenu = (name) => {
        const filteredMenu = jmMenu.filter(({ name: filtername }) => {
            return name !== filtername;
        });

        setJmMenu(filteredMenu);
    };

    const getmenu = () => {
        getshopmenu(param.shopId)
            .then((res) => {
                const getmenulist = res.data.map((getmenulist) => {
                    return {
                        name: getmenulist.name,
                        img: getmenulist.imgPath,
                        intro: getmenulist.intro,
                        price: getmenulist.price,
                    };
                });
                setMenu(getmenulist);
                console.log("menu " + menu.name);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    useEffect(() => {
        getreview();
    }, []);

    const getreview = () => {
        getReviewlist(param.shopId)
            .then((res) => {
                const reviewlist = res.data.map((reviewlist) => {
                    return {
                        reviewId: reviewlist.reviewId,
                        userId: reviewlist.userId,
                        shopId: reviewlist.shopId,
                        content: reviewlist.content,
                        regdate: reviewlist.regDate,
                        score: reviewlist.score,
                        imgUrl: reviewlist.imgUrl,
                        reviewId: reviewlist.reviewId,
                    };
                });
                setReviewList(reviewlist);
            })
            .catch((err) => {
                console.log(err);
            })
    }

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

    const removeReview = (id) => {
        removeReviews(id)
            .then((res) => {
                Swal.fire({
                    title: '리뷰 삭제 완료',
                    text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'success',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  }).then((result) => {
                    if (result.value) {
                        window.location.reload();
                    }
                  })
                
            })
            .catch((err) => {
                // alert(err.response.status);
                if (err.response.status == 500) {
                    Swal.fire({
                        title: '리뷰 작성자가 아닙니다.',
                        // text: "리뷰가 성공적으로 삭제됐습니다.",
                        icon: 'error',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                } else alert("리뷰 삭제 에러");
            });
    };

    const order = () => {
        if (jmMenu.length === 0) {
            Swal.fire({
                title: '주문 목록이 비었습니다.',
                // text: "리뷰가 성공적으로 삭제됐습니다.",
                icon: 'info',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              })
            return;
        }

        cartNumber(shopId)
            .then((res) => {
                if (localStorage) {
                    localStorage.removeItem("orderlist");
                    localStorage.setItem("orderlist", JSON.stringify(jmMenu));
                }
                if (localStorage.shopId) {
                    localStorage.removeItem("shopId");
                    localStorage.setItem("shopId", param.shopId);
                } else {
                    localStorage.setItem("shopId", param.shopId);
                }
                localStorage.setItem("orderId", res.data.orderId);
                history.push("/shoporder");
            })
            .catch((err) => {
                console.log(err);
                const status = err?.response?.status;
                if (status == 500) {
                    Swal.fire({
                        title: '로그인이 필요합니다.',
                        // text: "리뷰가 성공적으로 삭제됐습니다.",
                        icon: 'info',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                    openmodal();
                }
                //  else if (status == 400) {
                // }
            });
    };

    useEffect(() => {
        setTimeout(latlng(), 1000);
    });

    // 위도경도 반환
    const latlng = () => {
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
    const Mark = () => {
        if (shopIntro.marked == "N"){
            postMark(shopId)
       
            .then((res) => {
                window.location.reload();
                Swal.fire({
                    title: '찜등록 완료',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'success',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            })
            .catch((err) => {
                const status = err?.response?.status;
                    if(status== 400){
                        Swal.fire({
                            title: '로그인 후 이용해주세요.',
                            // text: "리뷰가 성공적으로 삭제됐습니다.",
                            icon: 'info',
                            // showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            // cancelButtonColor: '#d33',
                            confirmButtonText: '확인',
                            // cancelButtonText: '취소'
                          })
                    }
            });
        }
        else if (shopIntro.marked == "Y"){
            deleteMark(shopId)
                .then((res) => {
                    window.location.reload();
                    Swal.fire({
                        title: '찜 해제 완료',
                        // text: "리뷰가 성공적으로 삭제됐습니다.",
                        icon: 'success',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                })
                .catch((err) => {
                    Swal.fire({
                        title: '찜 해제 오류',
                        // text: "리뷰가 성공적으로 삭제됐습니다.",
                        icon: 'error',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                });
        }
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
            order={order}
            shopId={shopId}
            reviewList={reviewList}
            removeReview={removeReview}
            Mark={Mark}
            phone={shopIntro.phone}
        />
    );
};

export default Shopcontentcontainer;