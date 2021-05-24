
import * as S from "./style";
import { Link } from "react-router-dom";
import React, { useState, useEffect } from 'react';

const PosMain = ({
    shopId,

}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    
    return (
        <>
            <S.PosMainWrap>
                <div className="main-container">
                    <body>
                    <Link to={"/pos/"+shopId}>
                    <button className="main-button1">테이블 현황</button>
                    </Link>
                    <Link to={"/employee/"+shopId}>
                    <button className="main-button2">직원관리</button>
                    </Link>
                    <button className="main-button3">예약관리</button> 
                    <button className="main-button4">매출관리</button>
                    </body>
                    <footer>
                        <div className="footer">
                        <h3>주문의 민족</h3>
                        </div>
                    </footer>
                    
                </div>
                

            </S.PosMainWrap>
        </>
    );
};

export default PosMain;