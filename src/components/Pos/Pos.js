import React, { useState, useEffect } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import $ from "jquery";

//npm install --save jquery 설치하기
window.$ = $;

const Pos = ({
    shopId,
    name,
    num,
    handleNum,
    number,
    nowTime,
    tableamount,
    ordershowlist,
    ordermenulist,
}) => {
    const [thisShopId, setThisShopId] = useState("");
    const [jmtable, setjmtable] = useState();
    useEffect(() => {
        setThisShopId(shopId);
    });

    let i = 0;
    let g = 0;

    return (
        <>
            <S.PosWrap>
                <div className="left-container">
                    <div className="shopName">{name}</div>
                </div>
                <div className="right-container">
                    <span className="groupbox"></span>

                    {tableamount.map((tableamountlist) => {
                        {
                            return (
                                <Link
                                    to={`/table/${shopId}/${tableamountlist.tableno}`}
                                >
                                    <span class="table">
                                        &nbsp;{tableamountlist.tableno}
                                        <div className="tables">
                                            {ordershowlist.map(
                                                (ordershowlist) => {
                                                    if (
                                                        tableamountlist.tableno ==
                                                        ordershowlist.id
                                                    ) {
                                                        return (
                                                            <>
                                                            <div>
                                                                {ordershowlist.menulist.map(
                                                                    (
                                                                        menulist
                                                                    ) => {
                                                                        return (
                                                                            <div>
                                                                                {
                                                                                    menulist.menuName
                                                                                }
                                                                                {
                                                                                    menulist.quantity
                                                                                }
                                                                            </div>
                                                                        );
                                                                    }
                                                                )}
                                                                {/* 가격:{" "}
                                                                {
                                                                    ordershowlist.amount
                                                                } */}
                                                                
                                                            </div>
                                                            <div className="tables-price">
                                                                {ordershowlist.amount}\
                                                                </div>
                                                                </>
                                                        );
                                                    }
                                                    
                                                }
                                            )}
                                            
                                        </div>
                                                                    
                                    </span>
                                </Link>
                            );
                        }
                    })}
                </div>
            </S.PosWrap>
        </>
    );
};

export default Pos;
