import React, { useEffect, useState, useRef } from "react";
import Shopsocketlist from "../../components/Shopsocketlist/Shopsocketlist";
import * as StompJs from "@stomp/stompjs";

const ROOM_SEQ = 1;

const ShopsocketlistContainer = (props) => {
    const [shopId, setShopId] = useState(props.match.params.shopId);
    const [list, setlist] = useState([]);

    const client = useRef({});
    const [chatMessages, setChatMessages] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(() => {
        connect();

        return () => disconnect();
    }, []);

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
        client.current.subscribe(`/sub/${shopId}/o/roomId`, ({ body }) => {
            alert("주문이왔어요!");
            let ordernumber = JSON.parse(body).ordernumber;
            let orderid = JSON.parse(body).orderid.id;
            let orderlist = JSON.parse(body).orderlist.map((jmorder_list) => {
                return jmorder_list.name + jmorder_list.count;
            });
            let orderphone = JSON.parse(body).orderid.phone;
            let shopId = JSON.parse(body).ordershopId;
            let request = JSON.parse(body).request;
            let orderdate = JSON.parse(body).orderdate;
            let ordertime = JSON.parse(body).ordertime;
            

            // console.log(JSON.parse(body).ordernumber);

            setlist((prev) => {
                return [
                    ...prev,
                    { ordernumber, orderid, orderlist, orderphone, shopId, request,ordertime,orderdate},
                ];
            });

            // if (
            //     window.confirm(
            //         "예약주문이 왔어요! \n" +
            //             "주문내용은 다음과 같아요!\n" +
            //             "주문내용 : " +
            //             JSON.parse(body).message.map((jmorder_list) => {
            //                 return jmorder_list.name + jmorder_list.count;
            //             })
            //     )
            // ) {
            //     alert("주문이 완료되었습니다!");
            // }
            //  else {
            //     alert("환불하시겠어요?");
            // }
        });
    };
    console.log(list);
    const publish = (message) => {
        if (!client.current.connected) {
            return;
        }

        client.current.publish({
            destination: `/sub/chat/2`,
            body: JSON.stringify({ roomSeq: ROOM_SEQ, message }),
        });

        setMessage("");
    };
    return <Shopsocketlist list={list} />;
};

export default ShopsocketlistContainer;
