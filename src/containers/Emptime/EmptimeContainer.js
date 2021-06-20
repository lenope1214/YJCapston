import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Empbar from "../../components/Empbar/Empbar";
import Emptime from "../../components/Emptime/Emptime";
import { useHistory } from "react-router-dom";
import moment from "moment";
import "moment/locale/ko";
import { useInterval } from "react-use";
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import {
    postWorkstart,
    postWorkend,
    getEmpInfo,
    getShopInfo,
} from "../../lib/Emptime";

const EmptimeContainer = (props) => {
    const history = useHistory();
    const [shopId, setShopId] = useState("");
    const [empInfo, setEmpInfo] = useState({});
    const [empNo, setEmpNo] = useState("");
    const [empName, setEmpName] = useState("");
    const [realTime, setRealTime] = useState(Date.now());
    const nowDate = moment().format("YYYY년MM월DD일");
    const nowTime = moment().format("HH:mm:ss");
    const [shopInfo, setShopInfo] = useState({});

    // useInterval(() => {
    // setRealTime(Date.now());
    //   }, 1000);
    // //   console.log(nowTime);

    useEffect(() => {
        setShopId(props.match.params.shopId);
        ShowShopInfo(props.match.params.shopId);
        ShowEmpInfo(props.match.params.empNo);
        setEmpNo(props.match.params.empNo);
    }, []);

    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);

                console.log(res.data);
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };

    const ShowEmpInfo = () => {
        getEmpInfo(props.match.params.shopId, props.match.params.empNo)
            // getEmpInfo(props.match.params.empNo)
            .then((res) => {
                setEmpInfo(res.data);
                console.log(res.data);
            })
            .catch((err) => {});
    };

    const Start = () => {
        postWorkstart(shopId, empNo)
            .then((res) => {
                history.pushState("/emplist");
                alert("출근!");
            })
            .catch((err) => {
                const status = err?.response?.status;
                if (status == 409) {
                    alert("직원이 이미 출근 등록을 하였습니다.");
                } else if (status == 200) {
                    alert("출근등록 성공");
                } else {
                    alert("출근");
                }
            });
    };

    const End = () => {
        postWorkend(shopId, empNo)
            .then((res) => {
                history.pushState("/emplist");
                alert("퇴근!");
            })
            .catch((err) => {
                const status = err?.response?.status;
                if (status == 409) {
                    alert("출근 등록을 하지 않았습니다.");
                } else if (status == 200) {
                    alert("퇴근등록 성공");
                } else {
                    alert("퇴근");
                }
            });
    };

    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <PosNavbar shopId={shopId} />
            <Empbar shopId={shopId} />
            <Emptime
                shopId={shopId}
                nowTime={nowTime}
                realTime={realTime}
                nowDate={nowDate}
                Start={Start}
                End={End}
                empInfo={empInfo}
                empNo={empInfo.empNo}
                empName={empInfo.empName}
                shopname={shopInfo.name}
            />
        </>
    );
};

export default EmptimeContainer;
