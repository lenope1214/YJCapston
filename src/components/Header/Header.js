import React from 'react';

import * as S from "./style";
import { Link } from "react-router-dom";

const Header = () => {
    
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
                        </div>
                    </Link>
                </header>
            </S.headerWrap>
        </>
    );
}

export default Header;