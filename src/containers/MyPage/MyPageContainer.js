import React, { useState } from "react";
import MyPage from "../../components/Mypage/MyPage";

const MyPageContainer = () => {
    const [Pw, setPw] = useState("asdf");

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
        console.log(value);
    };

    return <MyPage Pw={Pw} handlePw={handlePw} />;
};

export default MyPageContainer;
