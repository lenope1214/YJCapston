import React, { useState } from "react";
import MyPage from "../../components/Mypage/MyPage";
import { getMyInfo } from "../../lib/MyPage";

const MyPageContainer = () => {
    const [Pw, setPw] = useState("");
    const [name, setName] = useState("");
    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
        console.log(value);
    };

    const getMypage = () => {
        getMyInfo()
            .then((res) => {
                setName(res.data.name);
            })
            .catch((err) => {});
    };

    return <MyPage Pw={Pw} handlePw={handlePw} />;
};

export default MyPageContainer;
