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
        getUserId();
        getShopname();
    }, []);

    const getUserId = () => {
        getMyInfo().then((res) => {
            SetId(res.data.user.id);
            console.log(res.data.user.role);
            if (res.data.user.role == "ROLE_OWNER") {
                Setowner("사장님");
            } else {
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
