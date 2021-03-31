import React from 'react';
import * as S from "./style";
import { Link, NavLink } from 'react-router-dom';

const OwnerNavbar = () => {
    return(
        <>
        <S.NavWrap>
        <nav>
            <ul className="nav-container">
                <NavLink to="/menulist" activeClassName="active">
                    <li className="nav-item">메뉴 정보</li>
                </NavLink>
                <NavLink to="/info" activeClassName="active">
                    <li className="nav-item">매장 소개</li>
                </NavLink>
                <NavLink to="/event" activeClassName="active">
                    <li className="nav-item">이벤트</li>
                </NavLink>
            </ul>
        </nav>
        </S.NavWrap>
        </>
    );
}

export default OwnerNavbar;