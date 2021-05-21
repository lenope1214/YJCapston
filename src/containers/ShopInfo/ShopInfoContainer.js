import React, { useEffect, useState, useRef } from "react";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import ShopInfo from "../../components/ShopInfo/ShopInfo";
import {
    getShopInfo,
    putShopreserve,
    putShopopen,
    putShopInfo,
} from "../../lib/ShopInfo";
import * as StompJs from "@stomp/stompjs";

const ROOM_SEQ = 1;

const ShopInfoContainer = (props) => {
    const history = useHistory();
    const [shopInfo, setShopInfo] = useState({});
    const [shopId, setShopId] = useState("");
    const [shopName, setShopName] = useState(null);
    const [shopIntro, setShopIntro] = useState(null);
    const [shopOpenTime, setShopOpenTime] = useState(null);
    const [shopCloseTime, setShopCloseTime] = useState(null);
    const [shopAddress, setShopAddress] = useState(null);
    const [shopAddressDetail, setShopAddressDetail] = useState(null);
    const [shopIsRsPos, setShopIsRsPos] = useState(null);
    const [shopCategory, setShopCategory] = useState(null);
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [modal, setModal] = useState(false);
    const [showLocation, setShowLocation] = useState([
        {
            roadAddr: "",
        },
    ]);

    const handleShopName = (e) => {
        const value = e.target.value;
        setShopName(value);
    };
    const handleShopIntro = (e) => {
        const value = e.target.value;
        setShopIntro(value);
    };
    const handleShopOpenTime = (e) => {
        const value = e.target.value;
        setShopOpenTime(value);
    };
    const handleShopCloseTime = (e) => {
        const value = e.target.value;
        setShopCloseTime(value);
    };
    const handleShopAddress = (e) => {
        const value = e.target.value;
        setShopAddress(value);
        console.log(value);
    };
    const handleShopAddressDetail = (e) => {
        const value = e.target.value;
        setShopAddressDetail(value);
        console.log(shopAddressDetail);
    };
    const handleShopIsRsPos = (e) => {
        const value = e.target.value;
        setShopIsRsPos(value);
    };
    const handleShopCategory = (e) => {
        const value = e.target.value;
        setShopCategory(value);
    };

    const openmodal = () => {
        setModal(true);
        console.log("true");
    };
    const closemodal = () => {
        setModal(false);
        console.log("false");
    };

    const handleRoadAddr = (roadAddr) => {
        setRoadAddr(roadAddr);
        closemodal();
    };

    const Shop_v2 = () => {
        putShopreserve(shopId)
            .then((res) => {
                // history.push("/shopInfo/"+shopId)
                history.push("/myshop");
                alert("예약여부변경완료");
            })
            .catch((err) => {
                alert("예약변경에러");
            });
    };

    const Shop_v3 = () => {
        putShopopen(shopId)
        .then((res) => {
            //history.push("/shopInfo/"+shopId)
            history.push("/myshop");
            alert("오픈여부변경완료+");
        });
    };

    //-----상세정보 수정
    const Shop_v1 = () => {
        putShopInfo(
            shopId,
            shopIntro,
            shopOpenTime,
            shopCloseTime,
            // shopAddress,
            roadAddr,
            shopAddressDetail
            // shopCategory
        )
            .then((res) => {
                history.push("/myShop");
                alert("수정되었습니다.");
            })
            .catch((err) => {
                alert("putshopInfo err");
            });
    };

    //-------선택한 아이디정보 가져오기
    useEffect(() => {
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);
                setRoadAddr(res.data.address);
                console.log(res.data);
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };

    //--------주소 api
    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = "";

        if (data.addressType === "R") {
            if (data.bname !== "") {
                extraAddress += data.bname;
            }
            if (data.buildingName !== "") {
                extraAddress +=
                    extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
            }
            fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
        }

        handleRoadAddr(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
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
            } 
            // else {
            //     alert("환불하시겠어요?");
            // }
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
            <ShopInfo
                id={shopInfo.id}
                name={shopInfo.name}
                intro={shopInfo.intro}
                img={shopInfo.imgPath}
                openTime={shopInfo.openTime}
                closeTime={shopInfo.closeTime}
                category={shopInfo.category}
                address={shopInfo.address}
                isOpen={shopInfo.isOpen}
                addressDetail={shopInfo.addressDetail}
                isRsPos={shopInfo.isRsPos}
                shopId={shopId}
                shopName={shopName}
                handleShopName={handleShopName}
                shopIntro={shopIntro}
                handleShopIntro={handleShopIntro}
                shopOpenTime={shopOpenTime}
                handleShopOpenTime={handleShopOpenTime}
                shopCloseTime={shopCloseTime}
                handleShopCloseTime={handleShopCloseTime}
                shopAddress={shopAddress}
                handleShopAddress={handleShopAddress}
                shopAddressDetail={shopAddressDetail}
                handleShopAddressDetail={handleShopAddressDetail}
                shopIsRsPos={shopIsRsPos}
                handleShopIsRsPos={handleShopIsRsPos}
                shopCategory={shopCategory}
                handleShopCategory={handleShopCategory}
                Shop_v1={Shop_v1}
                Shop_v2={Shop_v2}
                Shop_v3={Shop_v3}
                handleComplete={handleComplete}
                roadAddr={roadAddr}
                handleRoadAddr={handleRoadAddr}
                modal={modal}
                openModal={openmodal}
                closeModal={closemodal}
                // handleShopreserve={handleShopreserve}
            />
        </>
    );
};

export default ShopInfoContainer;
