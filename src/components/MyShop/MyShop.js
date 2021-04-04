import React from "react";
import { Link } from "react-router-dom";
import Shop from "../Shop/Shop";
import * as S from "./style";

const MyShop = ({
    isLogin,
    logout,
    openModal, 
    shop,
    Id
}) => {
    return (
        <>
            <S.MyShopWrap>
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
                    <button className="shop">
                          매장등록하러가기
                    </button>
                    </Link>
                </header>
                
                <body>
                
 
 <table border= "1px" solid ="#bababa" className="shoplist">
        <thead>
        <tr>
            <th align="center" className="id">매장번호</th>
            <th align="center" className="shopname">매장이름</th>
            <th align="center" className="intro">매장소개</th>
            <th align="center" className="openTime">오픈시간</th>
            <th align="center" className="closeTime">마감시간</th>
            <th align="center" className="address">매장주소</th>
            <th align="center" className="addressDetail">매장상세주소</th>
            {/* <th class="is_rs_pos">예약가능여부</th> */}
            
        </tr>
        </thead>

        <tbody>
        
                {/* {!shop.length && (
                    <tr>
                        <td colSpan="3">목록이 비었습니다.</td>
                    </tr>
                )} */}

        {!!shop.length && shop.map((shop) => {
            console.log(shop.closeTime);
            return(
        <tr>
            <Link to={`/menuList/${shop.id}`}>
            <td>{shop.id}</td>
            </Link>
            <td>{shop.shopname}</td>
            <td>{shop.intro}</td>
            <td>{shop.openTime}</td>
            <td>{shop.closeTime}</td>
            <td>{shop.address}</td>
            <td>{shop.addressDetail}</td>
            {/* <td>{shop.isRsPos}</td> */}
        </tr>

            );
        })}

        </tbody>
    </table>
                </body>
                <footer></footer>
            </S.MyShopWrap>
        </>
    );
};

export default MyShop;
