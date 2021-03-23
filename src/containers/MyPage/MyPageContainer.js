import React, { useState } from "react";
import MyPage from "../../components/Mypage/MyPage";
import { putMypage } from "../../lib/MyPage";
import { useHistory } from "react-router-dom";

const MyPageContainer = () => {
    const history = useHistory();
    const [Pw, setPw] = useState("");

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
    };

    const Mypage = () => {
        putMypage(Pw)
            .then((res) => {
                history.push("/main");
            })
            .catch((err) => err);
    };

    return <MyPage Pw={Pw} handlePw={handlePw} Mypage={Mypage} />;
};

export default MyPageContainer;
