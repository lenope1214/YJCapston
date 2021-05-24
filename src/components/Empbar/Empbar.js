import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { NavLink } from 'react-router-dom';

const Empbar = ({shopId}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })

    return(
        <>
        <S.EmpbarWrap>
        <nav>
        <div className="emp-container">
        <div className="header"><h2>직원관리</h2></div>
            
        <div className="menu-bar">
            <NavLink to={"/employee/"+thisShopId} activeClassName="active">     
            <button className="menu-1">직원등록/삭제</button>
            </NavLink>
            <NavLink to={"/emplist/"+thisShopId}>
            <button className="menu-2">직원 리스트</button>
            </NavLink>
            <button className="menu-3">근무시간</button>
            <button className="menu-4"></button>
        </div>    
         </div> 
          
         </nav>
        
    
        </S.EmpbarWrap>
        </>
    )
}

export default Empbar;