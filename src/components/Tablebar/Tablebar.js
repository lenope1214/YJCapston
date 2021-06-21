import React, { useState, useEffect } from "react";
import * as S from "./style";
import { NavLink } from "react-router-dom";

const Tablebar = ({ shopId }) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    });

    return (
        <>
            <S.TablebarWrap>
                <nav>
                    <div className="emp-container">
                        <div className="menu-bar">
                            <NavLink
                                to={"/pos/" + thisShopId}
                                activeClassName="Active"
                            >
                                <button className="menu-1">테이블 현황</button>
                            </NavLink>

                            <button
                                class="menu-2"
                                onClick={() =>
                                    window.open(
                                        `http://3.34.55.186:3000/chat/${shopId}`,
                                        "_blank",
                                        "location = no, toolbars= no, status= no, width = 400, height = 500 , scrollbars = no"
                                    )
                                }
                            >
                                채팅방가기
                            </button>
                            <div className="menu-3">

                            </div>
                        </div>
                    </div>
                </nav>
            </S.TablebarWrap>
        </>
    );
};

export default Tablebar;
