import React from "react";
import { Link } from "react-router-dom";
import Shop from "../Shop/Shop";
import * as S from "./style";

const ShopMain = ({
    isLogin,
    logout,
    openModal, 
    shop,
    Id
}) => {
    return (
        <>
            <S.ShopMainWrap>
                <header>
                    <Link to="/" class="movemainpage">
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
                    <div className="center-nav">
                        <input
                            type="text"
                            placeholder="매장을 검색하세요."
                            style={{
                                width: "300px",
                                border: "0",
                                borderRadius: "3px 0 0 3px",
                                backgroundColor: "white",
                            }}
                        ></input>
                        <button
                            style={{
                                border: "0",
                                width: "10%",
                                borderRadius: "0 3px 3px 0",
                                backgroundColor: "white",
                                color: "grey",
                            }}
                        >
                            검색
                        </button>
                    </div>
                    {isLogin ? (
                        <div className="right-nav">
                            <button className="right1-nav" onClick={logout}>
                                LOGOUT
                            </button>
                            <Link to="/mypage" class="right2-nav">
                                MYPAGE
                            </Link>
                        </div>
                    ) : (
                        <div className="right-nav">
                            <button className="right1-nav" onClick={openModal}>
                                LOGIN
                            </button>

                            <Link to="/register" class="right2-nav">
                                JOIN
                            </Link>
                        </div>
                    )}
                    <Link to="/shop" class="shop">
                    <button>
                          매장등록하러가기
                    </button>
                    </Link>
                </header>
                
                <body>
                
 
 <table border= "1px" solid ="#bababa">
        <thead>
        <tr>
            <th class="id">매장번호</th>
            <th class="shopname">매장이름</th>
            <th class="intro">매장소개</th>
            <th class="open_time">오픈시간</th>
            <th class="close_time">마감시간</th>
            <th class="address">매장주소</th>
            <th class="addressDetail">매장상세주소</th>
            <th class="is_rs_pos">예약가능여부</th>
            
        </tr>
        </thead>

        <tbody>
        
        <tr>
            <td>
                <a>
                <input //매장번호
                    type="text"
                    id="Id"
                    placeholder={shop.id}
                    disabled
                />
                </a>
            </td>
            <td>
                <input //매장이름
                    type="text"
                    id="name"
                    placeholder={shop.name}
                    disabled
                />
            </td>
            <td>
                <input //매장소개
                    type="text"
                    id="intro"
                    placeholder={shop.intro}
                    disabled
                />
            </td>
            <td>
                <input //오픈시간
                    type="text"
                    id="open_time"
                    placeholder={shop.openTime}
                    disabled
                />    
            </td>
            <td>
                <input //마감시간
                    type="text"
                    id="close_time"
                    placeholder={shop.closeTime}
                    disabled
                />    
            </td>
            <td>
                <input //매장주소
                    type="text"
                    id="address"
                    placeholder={shop.address}
                    disabled
                />    
            </td>
            <td>
                <input //매장상세주소
                    type="text"
                    id="address1"
                    placeholder={shop.addressDetail}
                    disabled
                />    
            </td>
            <td>
                <input //예약가능여부
                    type="text"
                    id="is_rs_pos"
                    placeholder={shop.isRsPos}
                    disabled
                />    
            </td>
        </tr>
        </tbody>
    </table>
            <span></span>
                

                </body>
                <footer></footer>
            </S.ShopMainWrap>
        </>
    );
};

export default ShopMain;
