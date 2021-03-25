import React, { useEffect, useState } from "react";
import MyPage from "../../components/Mypage/MyPage";
import { getMyInfo, putMypage } from "../../lib/MyPage";
import { useHistory } from "react-router-dom";

const MyPageContainer = () => {
    const history = useHistory();
    const [Pw, setPw] = useState("");
    const [user, setUser] = useState({
        name: "",
        phone: "",
        id: "",
    });

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

    // 이게 서버에서 코드 받아오는 함수
    useEffect(() => {
        getmypage();
    }, []);

    const getmypage = () => {
        getMyInfo()
            .then((res) => {
                console.log(res.data);
                setUser(res.data);
            })
            .catch((err) => {
                alert("");
            });
    };

    return <MyPage Pw={Pw} handlePw={handlePw} Mypage={Mypage} user={user} />;
};

export default MyPageContainer;
