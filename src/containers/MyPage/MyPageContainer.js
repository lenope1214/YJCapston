import React, { useEffect, useState } from "react";
import MyPage from "../../components/Mypage/MyPage";
import {
    getMyInfo,
    putMypage,
    Mypageorder,
    requirelist,
    ordermenulist,
} from "../../lib/MyPage";
import { useHistory } from "react-router-dom";

const MyPageContainer = () => {
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

    const history = useHistory();
    const [Pw, setPw] = useState("");
    const [user, setUser] = useState({
        name: "",
        phone: "",
        id: "",
        address: "",
        addressDetail: "",
    });
    const [roadAddr, setRoadAddr] = useState("");
    const [addressDetail, setAddressDetail] = useState("");
    const [address, setAddress] = useState("");
    const [jmlist, setJmlist] = useState([{}]);

    const handleRoadAddr = (roadAddr) => {
        setRoadAddr(roadAddr);
        closemodal();
    };

    const handleAddress = (e) => {
        const value = e.target.value;
        setAddress(value);
    };
    const handleAddressDetail = (e) => {
        const value = e.target.value;
        setAddressDetail(value);
    };

    const [modal, setModal] = useState(false);

    const openmodal = () => {
        setModal(true);
        console.log("true");
    };
    const closemodal = () => {
        setModal(false);
        console.log("false");
    };

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
    };

    const Mypage = () => {
        putMypage(Pw, roadAddr, addressDetail)
            .then((res) => {
                history.push("/main");
            })
            .catch((err) => err);
    };

    // 이게 서버에서 코드 받아오는 함수
    useEffect(() => {
        getMyPage();

        // getMyPageorder();
    }, []);
    console.log("res", user);

    const getMyPageorder = () => {
        Mypageorder()
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const require = () => {
        window.confirm("정말로 주문을 취소하겠어요?");
        // if (arlam == true) {
        //     requirelist()
        //         .then((res) => {})
        //         .catch((err) => {
        //             alert(err);
        //         });
        //     alert("결제 취소가 완료되었습니다.");
        // } else if ((arlam = false)) {
        //     alert("감사합니다.");
        // }
    };

    const orderinfom = () => {
        ordermenulist().then((res) => {
            console.log(res.data);
        });
    };

    const getMyPage = () => {
        getMyInfo()
            .then((res) => {
                console.log(res.data);
                setUser(res.data.user);
                setAddressDetail(res.data.user.addressDetail);
                setRoadAddr(res.data.user.address);
                const orderjmlist = res.data.orderList.map((orderjmlist) => {
                    return {
                        jmid: orderjmlist.orderId,
                        jmorderRequest: orderjmlist.orderRequest,
                        jmpeople: orderjmlist.people,
                        jmamount: orderjmlist.totalAmount,
                        jmshopName: orderjmlist.shopName,
                    };
                });
                setJmlist(orderjmlist);
            })
            .catch((err) => {
                alert(err);
            });
    };

    return (
        <MyPage
            Pw={Pw}
            handlePw={handlePw}
            Mypage={Mypage}
            user={user}
            modal={modal}
            openmodal={openmodal}
            handleComplete={handleComplete}
            roadAddr={roadAddr}
            handleAddress={handleAddress}
            handleAddressDetail={handleAddressDetail}
            addressDetail={addressDetail}
            handleRoadAddr={handleRoadAddr}
            jmlist={jmlist}
            require={require}
            orderinfom={orderinfom}
        />
    );
};

export default MyPageContainer;
