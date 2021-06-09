import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";

const Event = () => {
    return(
        <>
        <S.EventWrap>
        <div className="menu-container">
            <h2 className="menu-title">이벤트</h2>
        </div>
        </S.EventWrap>
        </>
    );
}

export default Event;