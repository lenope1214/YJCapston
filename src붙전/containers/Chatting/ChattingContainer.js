import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import Chatting from "../../components/Chatting/Chatting";
import { getMyInfo, getShopInfo } from "../../lib/Chatting/index";

const ChattingContainer = () => {
    const [id, SetId] = useState();
    const [shopname, SetShopname] = useState();
    const [owner, Setowner] = useState();

    const param = useParams();

    useEffect(() => {
        localStorage.setItem(
            "accesstoken",
            sessionStorage.getItem("access_token")
        );
        if (localStorage.getItem("accesstoken")) {
            getUserId();
            getShopname();
            if (!sessionStorage.getItem("access_token")) {
                alert("로그인을 부탁할게요");
                window.close();
            }
        }

    }, []);

    const getUserId = () => {
        getMyInfo().then((res) => {
            SetId(res.data.user.id);
            console.log(res.data.user.role);
            if (res.data.user.role == "ROLE_OWNER") {
                Setowner("사장님");
            }  else if (res.data.user.role = "ROLE_USER") {
                Setowner("고객님");
            }
        });
    };
    const getShopname = () => {
        getShopInfo(param.shopId).then((res) => {
            SetShopname(res.data.name);
        });
    };
    return <Chatting id={id} shopname={shopname} owner={owner} />;
};

export default ChattingContainer;
