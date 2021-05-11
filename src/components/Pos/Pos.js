import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { Link } from "react-router-dom";

const Pos = ({
   shopId,
   name,
   num,
   handleNum,
   number,
   nowTime,
}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    return (
        <>
            

            
            <S.PosWrap>
                
                <div className="pos-title">
                    <span>{shopId}</span>
                    <Link to={"/posmain/"+shopId}>
                    <button className="title1">포스기</button>
                    </Link>
                    <button className="title2">직원관리</button>
                    <button className="title3">예약관리</button>
                    <span>{nowTime}</span>
                </div>
                <div className="left-container">
                    <div className="left-head">테이블 번호</div>

                    <div className="left-body">메뉴명/단가/수량/할인/금액</div>

                    <div className="left-body2">해당테이블의 주문현황</div>

                    <div className="left-body3">합계</div>

                    <div className="left-body4">매출</div>
                </div>
                <div className="right-container">
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                            />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                            />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                            />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
            {/* ------------------------------- */}
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                            value={num}
                            onChange={handleNum}
                            />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
            {/* ------------------------------- */}
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
            {/* ------------------------------- */}
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                    <div className="table1">
                        <input 
                            type="text"
                            className="input-box"
                        />
                    </div>
                        
                </div>
    <button onClick={number} className="number">테이블 저장</button>
                   
                </S.PosWrap>
            
        </>
    );
};

export default Pos;