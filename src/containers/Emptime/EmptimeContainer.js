import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Empbar from "../../components/Empbar/Empbar";
import Emptime from "../../components/Emptime/Emptime";
import { useHistory } from "react-router-dom";
import moment from 'moment';
import 'moment/locale/ko';
import {useInterval} from 'react-use';
import PosNavbar from "../../components/PosNavbar/PosNavbar";

const EmptimeContainer = (props) => {
    const [shopId, setShopId] = useState("");

    const [realTime, setRealTime] = useState(Date.now());
    const nowDate = moment().format('YYYY년MM월DD일');

    
    const nowTime = moment().format('HH:mm:ss');


    useInterval(() => { 
        setRealTime(Date.now());
      }, 1000);
    //   console.log(nowTime);

    useEffect(() => {
      
        setShopId(props.match.params.shopId);
    }, []);

    return(
        <>
        <Header/>
        <OwnerNavbar shopId={shopId}/>
        <PosNavbar shopId={shopId}/>
        <Empbar shopId={shopId}/>
        <Emptime
        shopId={shopId}
        nowTime={nowTime}
        realTime={realTime}
        nowDate={nowDate}
        />
        </>
    )
}

export default EmptimeContainer;