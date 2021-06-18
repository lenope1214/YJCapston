import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import Chatting from "../../components/Chatting/Chatting";
import { getMyInfo, getShopInfo } from "../../lib/Chatting/index";
import Swal from 'sweetalert2';

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
                Swal.fire({
                    title: '로그인을 해주세요.',
                    text: "이용하기 위해서는 로그인이 필요합니다.",
                    icon: 'warning',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                }).then((result) => {
                    if (result.value) { 
                        window.close();
                    }
                })
                
            }
        }
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
