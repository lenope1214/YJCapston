import React from "react";

import * as S from "./style";
import reactDom from "react-dom";

const Emptime = ({
    shopId,
    nowTime,
    nowDate,
}) => {

    return(
        <>
            <S.EmptimeWrap>
                <div className="emptime-container">
                    <div className="main">
                        <span className="name">임재욱</span>
                    </div>
                    <div className="main-2">
                        <div className="date">{nowDate}</div> <br/>
                        <div className="time">{nowTime}</div>
                        <div className="work-button">
                            <button className="work">출근</button>
                            <button className="after-work">퇴근</button>
                        </div>
                    </div>
                                            
                    
                </div>

            </S.EmptimeWrap>
        </>
    );
};
export default Emptime;