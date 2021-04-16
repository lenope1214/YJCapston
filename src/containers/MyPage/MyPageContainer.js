import React, { useEffect, useState } from "react";
import MyPage from "../../components/Mypage/MyPage";
import { getMyInfo, putMypage } from "../../lib/MyPage";
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
    }, []);

    const getMyPage = () => {
        getMyInfo()
            .then((res) => {
                console.log(res.data);
                setUser(res.data);
                setAddressDetail(res.data.addressDetail);
                setRoadAddr(res.data.address);
            })
            .catch((err) => {
                alert("err");
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
        />
    );
};

export default MyPageContainer;
