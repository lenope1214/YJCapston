import React, { useEffect, useRef, useState } from "react";
import * as StompJs from "@stomp/stompjs";
import { useParams } from "react-router";
import * as S from "./style";
import juminicon from "./img/jmicon3.png";
import juminiconn from "./img/jmicon5.png";
import $ from "jquery";
//npm install --save jquery 설치하기
window.$ = $;

const ROOM_SEQ = 1;

const Chatting = ({ id, shopname, owner }) => {
    const param = useParams();
    console.log(param);
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
        client.current.subscribe(`/sub/${param.shopId}/u/2`, ({ body }) => {
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
            console.log(chatMessages.id);
        });
    };

    const publish = (message) => {
        if (!client.current.connected) {
            return;
        }
        scrollToBottom();

        client.current.publish({
            destination: `/sub/${param.shopId}/u/2`,
            body: JSON.stringify({
                roomSeq: ROOM_SEQ,
                message: message,
                id: owner,
            }),
        });
        console.log("ASDf" + message);
        console.log("ASDf" + id);
        console.log("00000000" + owner);

        setMessage("");
    };

    let a = null;
    let b = null;

    return (
        <>
            <S.ChattingWrap>
                <div className="total">
                    <div class="a">
                        {" "}
                        <div className="shopname">
                            <h2>{shopname} 채팅방</h2>
                        </div>
                        <div>
                            {/* {chatMessages && chatMessages.length > 0 && ( */}

                            <div>
                                {chatMessages.map((_chatMessage, index) => {
                                    // (
                                    if (_chatMessage.id == "사장님") {
                                        a = "chat";
                                        b = juminicon;
                                    } else {
                                        a = "chat-user";
                                        b = juminiconn;
                                    }
                                    return (
                                        <div key={index} className={a}>
                                            <div>
                                                {/* {_chatMessage.id} :  */}
                                                <img src={b} width="40px"></img>
                                            </div>
                                            <div className="chat-body">
                                                <p className="chat-body-name">
                                                    {_chatMessage.id}
                                                </p>
                                                <p className="chat-body-body">
                                                    {_chatMessage.message}
                                                </p>
                                            </div>
                                        </div>
                                    );
                                    // )
                                })}
                            </div>
                            {/* )} */}
                            <div class="abc">
                                <input
                                    type={"text"}
                                    placeholder={"message"}
                                    value={message}
                                    onChange={(e) => setMessage(e.target.value)}
                                    onKeyPress={(e) =>
                                        e.which === 13 && publish(message)
                                    }
                                    className="msg-input"
                                />
                                <button
                                    onClick={() => publish(message)}
                                    className="msg-but"
                                >
                                    전송
                                </button>
                            </div>
                        </div>
                        <div ref={divRref} />
                    </div>
                </div>
            </S.ChattingWrap>
        </>
    );
};

export default Chatting;
