import React, { useEffect, useState, useRef } from "react";
import MenuList from "../../components/MenuList/MenuList";
import { useHistory } from "react-router";
import { getMenuList } from "../../lib/MenuList";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import { removeMenues } from "../../lib/MenuList/index";
import * as StompJs from "@stomp/stompjs";

const ROOM_SEQ = 1;

const MenuListContainer = (props) => {
    const [menues, setMenues] = useState([
        {
            id: "",
            img: "",
        },
    ]);
    const history = useHistory();
    const [shopId, setShopId] = useState("");

    useEffect(() => {
        showMenuList(props.match.params.shopId);
        setShopId(props.match.params.shopId);
        console.log(menues);
    }, []);

    const showMenuList = () => {
        getMenuList(props.match.params.shopId)
            .then((res) => {
                setMenues(res.data);
                const menu = res.data.map((menu) => {
                    console.log(menu.id);
                    return {
                        img: menu.imgPath,
                        id: menu.id,
                        name: menu.name,
                        intro: menu.intro,
                        price: menu.price,
                    };
                });
                setMenues(menu);
            })
            .catch((err) => {
                alert("MenuListContainer Err");
            });
    };

    const removeMenu = (id) => {
        console.log("메뉴 아이디" + id);
        removeMenues(id)
            .then((res) => {
                alert("삭제되었습니다.");

                history.push(`/menulist/${shopId}`);
                window.location.reload();
            })
            .catch((err) => {
                alert("메뉴 삭제 에러");
            });
    };

    const client = useRef({});
    const [chatMessages, setChatMessages] = useState([]);
    const [message, setMessage] = useState("");

    const divRref = useRef(null);

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
        client.current.subscribe(`/sub/chat/2`, ({ body }) => {
            if (
                window.confirm(
                    "예약주문이 왔어요! \n" +
                        "주문내용은 다음과 같아요!\n" +
                        "주문내용 : " +
                        JSON.parse(body).message.map((jmorder_list) => {
                            return jmorder_list.name + jmorder_list.count;
                        })
                )
            ) {
                alert("주문이 완료되었습니다!");
            } else {
                alert("환불하시겠어요?");
            }
        });
    };

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

    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />

            <MenuList menues={menues} removeMenu={removeMenu} shopId={shopId} />
        </>
    );
};

export default MenuListContainer;
