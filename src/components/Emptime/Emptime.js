import React from "react";

import * as S from "./style";
import reactDom from "react-dom";

const Emptime = ({
    shopId,
    empNo,
    nowTime,
    nowDate,
    Start,
    End,
    empName,
    shopname,
}) => {

    return(
        <>
            <S.EmptimeWrap>
                <div className="emptime-container">
                    <div className="main">
                        <span className="shopname">{shopname}</span>
                        <span className="empname">{empName}</span>
                    </div>
                    <div className="main-2">
                        <div className="date">{nowDate}</div> <br/>
                        <div className="time">{nowTime}</div>
                        <div className="work-button">
                            <button onClick={Start} className="work">출근</button>
                            <button onClick={End} className="after-work">퇴근</button>
                        </div>
                    </div>
                                            
                    
                </div>

            </S.EmptimeWrap>
        </>
    );
};
export default Emptime;