import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { NavLink } from 'react-router-dom';

const PosNavbar = ({shopId}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })

    return(
        <>
        <S.PosNavbarWrap>
        <nav>
            <ul className="pos-container">
                <NavLink to={"/pos/"+thisShopId} activeClassName="active">
                <li className="pos-item">테이블현황</li>
                </NavLink>
                <NavLink to={"/emplist/"+thisShopId} activeClassName="active">
                <li className="pos-item">직원관리</li>
                </NavLink>
                <NavLink to={"/"} activeClassName="active">
                <li className="pos-item">예약관리</li>
                </NavLink>
                <NavLink to={"/"} activeClassName="active">
                <li className="pos-item">매출관리</li>
                </NavLink>
            </ul>
            
        </nav>
            </S.PosNavbarWrap>
            </>
    )
}

export default PosNavbar;
