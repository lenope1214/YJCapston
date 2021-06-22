import React, { useState, useEffect } from "react";
import Shoplist from "../../components/Shoplist/Shoplist";
import { useHistory } from "react-router-dom";
import {
    postLogin,
    getShoplist,
    getShoplistkorean,
    getShoplistchinese,
    getShoplistjapan,
    getShoplistforign,
    getShoplistdrink,
    getShoplistboon,
    getShoplistmeat,
    getShoplistjjimtang,
    getShoplistcafe,
    getShoplistfastfood,
    getShoplistMarks,
} from "../../lib/Shoplist/index";
import Swal from 'sweetalert2';

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
            addressDetail: "",
            img: "",
        },
    ]); 
    // const [marklist, setmarklist] = useState(
    //     {
    //         name: "",
    //         category: "",
    //         address: "",
    //         intro: "",
    //         addressDetail: "",
    //         img: "",
    //     }
    // );

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
    //한식
    const showkorean = () => {
        getShoplistkorean()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
                console.log(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //중식
    const showchinese = () => {
        getShoplistchinese()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //일식
    const showJapan = () => {
        getShoplistjapan()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //양식
    const showforign = () => {
        getShoplistforign()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {Swal.fire({
                title: '매장이 없습니다.',
                // text: "리뷰가 성공적으로 삭제됐습니다.",
                icon: 'info',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              })
            });
    };
    //술집
    const showdrink = () => {
        getShoplistdrink()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //분식
    const showboon = () => {
        getShoplistboon()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //고기
    const showmeat = () => {
        getShoplistmeat()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //찜탕
    const showjjimtang = () => {
        getShoplistjjimtang()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //카페디저트
    const showcafe = () => {
        getShoplistcafe()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    //패스트푸드
    const showfastfood = () => {
        getShoplistfastfood()
            .then((res) => {
                const rstrt = res.data.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
            });
    };
    // 실시간리뷰
    // const showdrink = () => {
    //     getShoplistdrink()
    //         .then((res) => {
    //             const rstrt = res.data.map((rstrt) => {
    //                 return {
    //                     address: rstrt.address,
    //                     name: rstrt.name,
    //                     intro: rstrt.intro,
    //                     category: rstrt.category,
    //                     id: rstrt.id,
    //                     addressDetail: rstrt.addressDetail,
    //                     img: rstrt.imgPath,
    //                 };
    //             });
    //             setRestaurant(rstrt);
    //         })
    //         .catch((err) => {
    //             alert("매장이없습니다.");
    //         });
    // };
    //모두보기
    const showShoplist = () => {
        getShoplist()
            .then((res) => {
                console.log(res.data);
                const rstrt = res.data.map((rstrt) => {
                    console.log(res.data);
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                
                setRestaurant(rstrt);
            })
            .catch((err) => {
                Swal.fire({
                    title: '매장이 없습니다.',
                    // text: "리뷰가 성공적으로 삭제됐습니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  })
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
                    Swal.fire({
                        // title: '없는 계정이거나 아이디 비밀번호가 일치하지 않습니다.',
                        text: "없는 계정이거나 아이디 비밀번호가 일치하지 않습니다.",
                        icon: 'info',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                    sessionStorage.removeItem("access_token");
                    setModal(true);
                } else if (status == 500) {
                    alert("서버 문제");
                } else {
                    alert("서버 off");
                }
            });
    };

    const showMarks = () => {
        getShoplistMarks()
            .then((res) => {
                console.log("asdf"+res.data)
                const rstrt = res.data.shopList.map((rstrt) => {
                    return {
                        address: rstrt.address,
                        name: rstrt.name,
                        intro: rstrt.intro,
                        category: rstrt.category,
                        id: rstrt.shopId,
                        addressDetail: rstrt.addressDetail,
                        img: rstrt.imgPath,
                        reviews: rstrt.reviews,
                        score: rstrt.score,
                    };
                });
                setRestaurant(rstrt);
            })
            .catch((err) => {
                const status = err?.response?.status;
                if(status==400) {
                    Swal.fire({
                        title: '로그인 오류.',
                        // text: "없는 계정이거나 아이디 비밀번호가 일치하지 않습니다.",
                        icon: 'error',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                }else {
                    alert("err");
                }
            });
    };


    // const showMarks = () => {
    //     getMark()
    //     .then((res) => {
    //         setmarklist(res.data);
            
    //         const marks = res.data.map((marks) => {
    //             return {
    //                 //여기에 get요청했을때 서버에서 어떤데이터를 주는가?
    //                 address: marks.address,
    //                     name: marks.name,
    //                     intro: marks.intro,
    //                     category: marks.category,
    //                     id: marks.shopId,
    //                     addressDetail: marks.addressDetail,
    //                     img: marks.imgPath,
    //                 };

    //         });
    //         setmarklist(marks);
    //     })
    //     .catch((err) => {
    //         alert("err");
    //     })
    // }

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
                showkorean={showkorean}
                showchinese={showchinese}
                showJapan={showJapan}
                showforign={showforign}
                showdrink={showdrink}
                showboon={showboon}
                showmeat={showmeat}
                showjjimtang={showjjimtang}
                showcafe={showcafe}
                showfastfood={showfastfood}
                showShoplist={showShoplist}
                showMarks={showMarks}
                // marklist={marklist}
            />
        </>
    );
};

export default ShoplistContainer;
