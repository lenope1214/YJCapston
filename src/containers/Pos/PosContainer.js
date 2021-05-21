import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Pos from "../../components/Pos/Pos";
import { getShopOrder} from "../../lib/Pos/";
import {getShopInfo} from "../../lib/Pos";
import moment, { now } from 'moment';
import 'moment/locale/ko';
import {useInterval} from 'react-use';
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import Tablebar from "../../components/Tablebar/Tablebar";
//npm i react-use   설치하기
 
const PosContainer = (props) => {
    const [shopId, setShopId] = useState("");
    const [num, setNum] = useState("");
    const [shopInfo, setShopInfo] = useState({});
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [Orders, setOrders] = useState([
        {

        }
    ]);
    const [realTime, setRealTime] = useState(Date.now());
    const nowTime = moment().format('YYYY년MM월DD일 HH시mm분ss초');

    useInterval(() => {
        setRealTime(Date.now());
      }, 1000);

    const handleNum = (e) => {
        const value = e.target.value;
        setNum(value);
        }


        useEffect(() => {
            ShowShopInfo(props.match.params.shopId);
            setShopId(props.match.params.shopId);
        }, []);
    
        const ShowShopInfo = () => {
            getShopInfo(props.match.params.shopId)
                .then((res) => {
                    setShopInfo(res.data);
                    setRoadAddr(res.data.address);
                    console.log(res.data);
                })
                .catch((err) => {
                    alert("showshopInfo err");
                });
        };


    // useEffect(() => {
    //     ShowShopOrder(props.match.params.shopId);
    //     setShopId(props.match.params.shopId);
    // },[]);

    // const ShowShopOrder = () => {
    //     getShopOrder(props.match.params.shopId)
    //     .then((res) => {
    //         setOrders(res.data);
    //         const order = res.data.map((order) => {

    //             return {
    //                 id: order.id,
    //                 // no:order.no,
    //                 // name:order.name,

    //             };
    //         });
    //         setOrders(order);
    //         console.log(res.data);
    //         })
    //         .catch((err) => {
    //             alert("err");
    //     });
    // };

    
    return (
        <>
            <Header/>
            <OwnerNavbar shopId={shopId}/>
            <PosNavbar shopId={shopId}/>
            <Tablebar shopId={shopId}/>
            <Pos
              shopId={shopId}
              Orders={Orders}
              handleNum={handleNum}
              num={num}
              realTime={realTime}
              nowTime={nowTime}
              name={shopInfo.name}
              roadAddr={roadAddr}
            />
        </>
    );
};

export default PosContainer;