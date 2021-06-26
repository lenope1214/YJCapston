import React, { useState, useEffect } from "react";
import * as S from "./style";
import { NavLink } from "react-router-dom";

const PosNavbar = ({ shopId }) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    });
    const openwindow = () => {
        window.open(
            `http://3.34.55.186:3000/Shopsocketlist/${shopId}`,
            "_blank",
            "location = no, toolbars= no, status= no, width = 500, height = 500 , scrollbars = no"
        );
    };

    return (
        <>
            <S.PosNavbarWrap>
                <nav>
                    <ul className="pos-container">
                        <NavLink
                            to={"/pos/" + thisShopId}
                            activeClassName="active"
                        >
                            <li className="pos-item">테이블현황</li>
                        </NavLink>
                        <NavLink
                            to={"/emplist/" + thisShopId}
                            activeClassName="active"
                        >
                            <li className="pos-item">직원관리</li>
                        </NavLink>
                        {/* <NavLink to={"/"} activeClassName="active"> */}

                        <li
                            className="pos-item1"
                            onClick={() => {
                                localStorage.setItem(
                                    "token",
                                    sessionStorage.getItem("access_token")
                                );
                                window.open(
                                    `http://localhost:3000/Shopsocketlist/${shopId}`,
                                    "_blank",
                                    "location = no, toolbars= no, status= no, width = 500, height = 500 , scrollbars = no"
                                );
                            }}
                        >
                            예약관리
                        </li>

                        {/* </NavLink> */}
                        <NavLink
                            to={"/saleslist/" + shopId}
                            activeClassName="active"
                        >
                            <li className="pos-item">매출관리</li>
                        </NavLink>
                    </ul>
                </nav>
            </S.PosNavbarWrap>
        </>
    );
};

export default PosNavbar;
