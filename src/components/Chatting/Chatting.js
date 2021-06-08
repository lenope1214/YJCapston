import React, { useEffect, useRef, useState } from "react";
import * as StompJs from "@stomp/stompjs";
import { useParams } from "react-router";
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
                id: id + "(" + owner + ")",
            }),
        });
        console.log("ASDf" + message);
        console.log("ASDf" + id);

        setMessage("");
    };

    return (
        <div>
            <div class="a">
                {" "}
                <div>
                    <h2>{shopname}의 채팅방입니다!</h2>
                </div>
                <div>
                    {chatMessages && chatMessages.length > 0 && (
                        <div>
                            {chatMessages.map((_chatMessage, index) => (
                                <div key={index}>
                                    <span>
                                        {_chatMessage.id}:{_chatMessage.message}
                                    </span>
                                </div>
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

export default Chatting;
