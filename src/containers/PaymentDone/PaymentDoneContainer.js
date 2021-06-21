import React, { useEffect, useState } from "react";
import { useHistory } from "react-router";
import PaymentDone from "../../components/PaymentDone/PaymentDone";
import { postLogin, requirepay } from "../../lib/PaymentDone/index";
import Swal from 'sweetalert2';

const PaymentDoneContainer = ({ isLogin, handleLogin, handleLogout }) => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [modal, setModal] = useState(false);
    const [paymentlist, setPaymentlist] = useState([{}]);

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
        setPaymentlist(JSON.parse(localStorage.getItem("orderlist")));
        console.log(paymentlist);
    }, []);

    const require = () => {
        requirepay()
            .then((res) => {
                console.log(res);
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
    return (
        <>
            <PaymentDone
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
                paymentlist={paymentlist}
                require={require}
            />
        </>
    );
};

export default PaymentDoneContainer;
