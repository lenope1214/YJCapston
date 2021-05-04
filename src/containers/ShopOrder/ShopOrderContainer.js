import React, { useState, useContext, useEffect, useRef } from "react";
import { useHistory } from "react-router";
import ShopOrder from "../../components/ShopOrder/ShopOrder";
import {
    postLogin,
    getMyInfo,
    getshopinfo,
    patchorder,
    paymentservice,
} from "../../lib/ShopOrder/index";
import * as StompJs from "@stomp/stompjs";
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
    const [request, setRequest] = useState();
    const [people, setPeople] = useState();
    const [pointcheck, setpointcheck] = useState(0);

    const openmodal = () => {
        setModal(true);
    };

    const closemodal = () => {
        setModal(false);
    };

    const handleRequest = (e) => {
        const value = e.target.value;
        setRequest(value);
    };

    const handlepoint = (e) => {
        const value = e.target.value;
        setpointcheck(value);
        if (jmallprice - pointcheck < 0) {
            alert("포인트를 너무 많이 사용하셨어요. 다시입력해주세요");
            setpointcheck("");
        }
        if (jmuserinfo.point < value) {
            alert("보유한 포인트보다 많아요. 다시 입력해주세요");
            setpointcheck("");
        }
    };

    const handlePeople = (e) => {
        const value = e.target.value;
        setPeople(value);
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

    const patchcontent = () => {
        patchorder(request, people)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                alert("err");
            });
    };

    const getMyinfor = () => {
        getMyInfo()
            .then((res) => {
                setJmuserinfo(res.data.user);
            })
            .catch((err) => {
                alert("err");
            });
    };

    const getshopinfor = () => {
        getshopinfo(localStorage.getItem("shopId"))
            .then((res) => {
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
    };

    //실시간 알림
    const ROOM_SEQ = 1;
    const client = useRef({});

    const connect = () => {
        client.current = new StompJs.Client({
            brokerURL: "ws:/3.34.55.186:8088/ws-stomp/websocket", // 웹소켓 서버로 직접 접속
            // webSocketFactory: () => new SockJS("/ws-stomp/websocket"), // proxy를 통한 접속
            connectHeaders: {
                socket_token: "jmj-chatting",
            },
            debug: function (str) {
                console.log(str);
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
            onConnect: () => {
                subscribe();
            },
            onStompError: (frame) => {
                console.error(frame);
            },
        });

        console.log(client.current);

        const subscribe = () => {
            client.current.subscribe(`/sub/chat/1`, ({ body }) => {
                if (window.confirm("주문이 발생 했습니다?")) {
                    alert("happy");
                } else {
                    // 환불;
                }
            });
        };

        client.current.activate();
    };

    const disconnect = () => {
        client.current.deactivate();
    };

    const publish = () => {
        if (!client.current.connected) {
            return;
        }

        client.current.publish({
            destination: `/sub/chat/2`,
            body: JSON.stringify({ roomSeq: ROOM_SEQ, message: jmorderlist }),
        });
    };

    //결제 api

    const onClickPayment = () => {
        const { IMP } = window;
        IMP.init("imp59387591");

        /* 2. 결제 데이터 정의하기 */
        const data = {
            pg: "inicis", // PG사
            pay_method: "card", // 결제수단
            merchant_uid: localStorage.getItem("orderId"), // 주문번호
            amount: jmallprice - pointcheck, // 결제금액
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
            patchcontent();
            connect();
            setTimeout(() => {
                paymentservice(jmallprice, pointcheck);
            }, 3000);

            alert("결제 성공");
            history.push("/PaymentDone");
        } else {
            alert(`결제 실패: ${error_msg}`);
        }
    };

    useEffect(() => {
        return () => {
            console.log(1);
            // disconnect();
        };
    }, []);

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
                handleRequest={handleRequest}
                request={request}
                handlePeople={handlePeople}
                people={people}
                handlepoint={handlepoint}
                pointcheck={pointcheck}
                connect={connect}
                publish={publish}
            />
        </>
    );
};

export default ShopOrderContainer;
