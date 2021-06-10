import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { NavLink } from 'react-router-dom';

const OwnerNavbar = ({shopId}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    return(
        <>
        <S.NavWrap>
        <nav>
            <ul className="nav-container">
                <NavLink to={"/menulist/"+thisShopId} activeClassName="active">
                    <li className="nav-item">메뉴 정보</li>
                </NavLink>
                <NavLink to={"/shopInfo/"+thisShopId} activeClassName="active">
                    <li className="nav-item">매장 소개</li>
                </NavLink>
                <NavLink to={"/pos/"+thisShopId} activeClassName="active">
                    <li className="nav-item">포스기</li>
                </NavLink>
                <NavLink to={"/qrcode/"+thisShopId} activeClassName="active">
                    <li className="nav-item">QR코드</li>
                </NavLink>
            </ul>
        </nav>
        </S.NavWrap>
        </>
    );
}

export default OwnerNavbar;