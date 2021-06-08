import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import React from "react";
import MenuRegisterFormContainer from "./containers/MenuRegisterFormContainer";
import EventContainer from "./containers/EventContainer";
import LoginContainer from "./containers/Login/LoginContainer";
import MainContainer from "./containers/Main/MainContainer";
import MypageContainer from "./containers/MyPage/MyPageContainer";
import RegisterContainer from "./containers/Register/RegisterContainer";
import ShopContainer from "./containers/Shop/ShopContainer";
import { useEffect, useState, useRef } from "react";
import { MenuReadContainer } from "./containers/MenuRead/MenuReadContainer";
import MenuListContainer from "./containers/MenuList/MenuListContainer";
import ShopInfoContainer from "./containers/ShopInfo/ShopInfoContainer";
import MyShopContainer from "./containers/MyShop/MyShopContainer";
import ShoplistContainer from "./containers/Shoplist/ShoplistContainer";
import ShopcontentContainer from "./containers/shopcontent/shopcontentcontainer";
import Payment from "./components/Event/Payment";
import ShopOrderContainer from "./containers/ShopOrder/ShopOrderContainer";
import PaymentContainer from "./containers/PaymentDone/PaymentDoneContainer";
import QRcodeContainer from "./containers/QRcode/QRcodeContainer";
import PosContainer from "./containers/Pos/PosContainer";
import PosMainContainer from "./containers/PosMain/PosMainContainer";
import EmployeeContainer from "./containers/Employee/EmployeeContainer";
import EmplistContainer from "./containers/Emplist/EmplistContainer";
import AddReviewContainer from "./containers/AddReview/AddReviewContainer";
import EmptimeContainer from "./containers/Emptime/EmptimeContainer";
import * as StompJs from "@stomp/stompjs";
import EmptimelistContainer from "./containers/Emptimelist/EmptimelistContainer";
import ShopsocketlistContainer from "./containers/Shopsocketlist/ShopsocketlistContainer";
import SaleslistContainer from "./containers/saleslist/SaleslistContainer";
import ChattingContainer from "./containers/Chatting/ChattingContainer";

const ROOM_SEQ = 1;

const App = () => {
    const [isLogin, setIsLogin] = useState(false);

    useEffect(() => {
        const accesstoken = sessionStorage.getItem("access_token");

        if (accesstoken) {
            setIsLogin(true);
        }
    }, []);

    const handleLogin = () => {
        setIsLogin(true);
        alert("회원님 반가워요!");
    };

    const handleLogout = () => {
        setIsLogin(false);
        sessionStorage.removeItem("access_token");
        alert("로그아웃이 완료 되었습니다.");
    };

    return (
        <>
            <Router>
                <Switch>
                    <Route path="/payment" component={Payment} />
                    <Route path="/login" component={LoginContainer} />
                    <Route path="/register" component={RegisterContainer} />
                    <Route path="/mypage" component={MypageContainer} />
                    <Route path="/shop" component={ShopContainer} />
                    <Route path="/MyShop" component={MyShopContainer} />
                    <Route component={QRcodeContainer} path="/QRcode/:shopId" />
                    <Route component={PosContainer} path="/Pos/:shopId" />
                    <Route
                        component={PosMainContainer}
                        path="/PosMain/:shopId"
                    />
                    <Route
                        component={SaleslistContainer}
                        path="/saleslist/:shopId"
                    />

                    <Route
                        component={EmployeeContainer}
                        path="/Employee/:shopId"
                    />
                    <Route
                        component={EmplistContainer}
                        path="/Emplist/:shopId"
                    />
                    <Route
                        component={EmptimeContainer}
                        path="/Emptime/:shopId/:empNo"
                    />
                    <Route
                        component={EmptimelistContainer}
                        path="/Emptimelist/:shopId"
                    />
                    <Route
                        component={MenuListContainer}
                        path="/menuList/:shopId"
                    />
                    <Route
                        component={MenuRegisterFormContainer}
                        path="/create/:shopId"
                    />
                    <Route
                        path="/ShopInfo/:shopId"
                        component={ShopInfoContainer}
                    />
                    <Route component={EventContainer} path="/event" />
                    <Route component={MenuReadContainer} path="/menu/:menuId" />
                    <Route
                        component={AddReviewContainer}
                        path="/addreview/:shopId/:orderId"
                    />
                    <Route
                        path="/shopcontent/:shopId"
                        component={() => (
                            <ShopcontentContainer
                                isLogin={isLogin}
                                handleLogin={handleLogin}
                                handleLogout={handleLogout}
                            />
                        )}
                    ></Route>
                    <Route
                        path="/shoplist"
                        component={() => (
                            <ShoplistContainer
                                isLogin={isLogin}
                                handleLogin={handleLogin}
                                handleLogout={handleLogout}
                            />
                        )}
                    />
                    <Route
                        path="/shoporder"
                        component={() => (
                            <ShopOrderContainer
                                isLogin={isLogin}
                                handleLogin={handleLogin}
                                handleLogout={handleLogout}
                            />
                        )}
                    ></Route>
                    <Route
                        path="/paymentDone"
                        component={() => (
                            <PaymentContainer
                                isLogin={isLogin}
                                handleLogin={handleLogin}
                                handleLogout={handleLogout}
                            />
                        )}
                    ></Route>
                    <Route path="/test" component={TestChat} />
                    <Route
                        path="/Shopsocketlist/:shopId"
                        component={ShopsocketlistContainer}
                    ></Route>
                    <Route
                        path="/chat/:shopId"
                        component={ChattingContainer}
                    ></Route>
                    <Route
                        path="/"
                        component={() => (
                            <MainContainer
                                isLogin={isLogin}
                                handleLogin={handleLogin}
                                handleLogout={handleLogout}
                            />
                        )}
                    />
                </Switch>
            </Router>
            {/* <Route path="/test" component={TestChat} /> */}
        </>
    );
};
const TestChat = () => {
    const client = useRef({});
    const [chatMessages, setChatMessages] = useState([]);
    const [message, setMessage] = useState("");

    const divRref = useRef(null);

    useEffect(() => {
        connect();
        scrollToBottom();
        return () => disconnect();
    }, []);

    const scrollToBottom = () => {
        divRref.current.scrollIntoView({ behavior: "smooth" });
    };

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

        client.current.activate();
    };

    const disconnect = () => {
        client.current.deactivate();
    };

    const subscribe = () => {
        client.current.subscribe(`/sub/chat/3`, ({ body }) => {
            // if (window.confirm("주문이 발생 했습니다?")) {
            //     alert("happy");
            // } else {
            //     // 환불
            // }
            scrollToBottom();
            setChatMessages((_chatMessages) => [
                ..._chatMessages,
                JSON.parse(body),
            ]);
        });
    };

    const publish = (message) => {
        if (!client.current.connected) {
            return;
        }
        scrollToBottom();

        client.current.publish({
            destination: `/sub/chat/3`,
            body: JSON.stringify({ roomSeq: ROOM_SEQ, message }),
        });

        setMessage("");
    };

    return (
        <div>
            <div class="a">
                {" "}
                <div>
                    {chatMessages && chatMessages.length > 0 && (
                        <div>
                            {chatMessages.map((_chatMessage, index) => (
                                <div key={index}>{_chatMessage.message}</div>
                            ))}
                        </div>
                    )}
                    <div>HELLO</div>{" "}
                    <div class="abc">
                        <input
                            type={"text"}
                            placeholder={"message"}
                            value={message}
                            onChange={(e) => setMessage(e.target.value)}
                            onKeyPress={(e) =>
                                e.which === 13 && publish(message)
                            }
                        />
                        <button onClick={() => publish(message)}>전송</button>
                    </div>
                </div>
                <div ref={divRref} />
            </div>
        </div>
    );
};

export default App;
