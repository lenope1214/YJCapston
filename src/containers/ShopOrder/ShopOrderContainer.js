import React, { useState, useContext, useEffect } from "react";
import { useHistory } from "react-router";
import ShopOrder from "../../components/ShopOrder/ShopOrder";
import { postLogin, getMyInfo, getshopinfo } from "../../lib/ShopOrder/index";
// import { jmthing } from "../shopcontent/shopcontentcontainer";

const ShopOrderContainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);

    // const asdf = useContext(jmthing);

    // console.log(asdf);
    const [jmshopinfo, setJmshopinfo] = useState({});
    const [jmuserinfo, setJmuserinfo] = useState({});
    const [jmorderid, setJmorderid] = useState();
    const [jmorderlist, setJmorderlist] = useState([{}]);
    const [jmallprice, setjmallpirce] = useState();

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
        getMyinfor();
        getshopinfor();
        setJmorderid(localStorage.getItem("orderId"));
        setJmorderlist(JSON.parse(localStorage.getItem("orderlist")));
        setjmallpirce(localStorage.getItem("allPrice"));
    }, []);

    console.log(jmorderid);
    console.log(jmorderlist);
    console.log(jmallprice);

    const getMyinfor = () => {
        getMyInfo()
            .then((res) => {
                console.log(res.data);
                setJmuserinfo(res.data);
            })
            .catch((err) => {
                alert("err");
            });
    };

    const getshopinfor = () => {
        getshopinfo(localStorage.getItem("shopId"))
            .then((res) => {
                console.log(res.data);
                setJmshopinfo(res.data);
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
                alert("처음부터 다시 진행해 주세요");
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
    const goBack = () => {
        history.goBack();
    }

    //결제 api

    const onClickPayment = () => {
        const { IMP } = window;
        IMP.init("imp01130487");

        /* 2. 결제 데이터 정의하기 */
        const data = {
            pg: "inicis", // PG사
            pay_method: "card", // 결제수단
            merchant_uid: `mid_${new Date().getTime()}`, // 주문번호
            amount: jmallprice, // 결제금액
            name: jmshopinfo.name, // 매장이름
            buyer_name: jmuserinfo.name, // 구매자 이름
            buyer_tel: jmuserinfo.phone, // 구매자 전화번호
            buyer_email: jmuserinfo.email, // 구매자 이메일
            buyer_addr: jmuserinfo.address + jmuserinfo.addressDetail, // 구매자 주소
        };

        /* 4. 결제 창 호출하기 */
        IMP.request_pay(data, callback);
    };

    const callback = (response) => {
        const { success, merchant_uid, error_msg } = response;

        if (success) {
            alert("결제 성공");
        } else {
            alert(`결제 실패: ${error_msg}`);
        }
    };

    return (
        <>
            <ShopOrder
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
                jmshopinfo={jmshopinfo}
                jmuserinfo={jmuserinfo}
                jmorderid={jmorderid}
                jmorderlist={jmorderlist}
                jmallprice={jmallprice}
                onClickPayment={onClickPayment}
                goBack={goBack}
            />
        </>
    );
};

export default ShopOrderContainer;
