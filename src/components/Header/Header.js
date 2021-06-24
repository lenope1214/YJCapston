// import React from 'react';
import React, { useState } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";

import moment from 'moment';
import 'moment/locale/ko';
import {useInterval} from 'react-use';
//npm i react-use   설치하기

const Header = () => {
    
    const [realTime, setRealTime] = useState(Date.now());
    const nowDate = moment().format('YYYY-MM-DD')
    const nowTime = moment().format('HH:mm:ss');
    
    // useInterval(() => {
    //     setRealTime(Date.now());
    //   }, 1000);
    return (
        <>
            <S.headerWrap>
                <header>
                    <Link to="/" class="shoppage" onClick={() => {window.scrollTo(0,0)}}>
                        <div className="left-nav">
                            주문
                            <span
                                style={{
                                    fontSize: "23px",
                                    paddingTop: "10px",  
                                }}
                            >
                                의
                            </span>
                            민족
                            {/* <span className="date">{nowDate}</span>
                            <span className="time">{nowTime}</span> */}
                        </div>
                    </Link>
                </header>
            </S.headerWrap>
        </>
    );
}

export default Header;