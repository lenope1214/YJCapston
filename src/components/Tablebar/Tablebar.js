import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { NavLink } from 'react-router-dom';

const Tablebar = ({shopId}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })

    return(
        <>
        <S.TablebarWrap>
            <nav>
        <div className="emp-container">
        
            
        <div className="menu-bar">
            <NavLink to={"/pos/"+thisShopId} activeClassName="Active">
            <button className="menu-1">테이블 현황</button>
            </NavLink>
            
            <button className="menu-2">테이블 등록</button>
            
           
        
        </div>    
         </div> 

        
          
         </nav>
        
        </S.TablebarWrap>
        </>
    );
};

export default Tablebar;