import React from "react";
import { Link } from "react-router-dom";
import Shop from "../Shop/Shop";
import * as S from "./style";
import topimg from "../Main/img/QR코드사진2.png";

const MyShop = ({
    isLogin,
    logout,
    openModal,
    shops,
    removeShop,
    Id
}) => {
    return (
        <>
            <S.MyShopWrap>
                <body>
                <div className="totals">
                    <div>
                        <img src={topimg} className="topimg" />
                    </div>
                    <div className="topimg-text">
                        <p>매장 목록</p>
                    </div>
                    <div className="menu-container">
                        {!shops.length && (
                            <div>
                                <td colSpan="8" className="empty-list">목록이 비었습니다.</td>
                            </div>
                        )}
                        {!!shops.length && shops.map((shop) => {
                            // console.log(shop.closeTime);
                            return (

                                <div className="oneblock">
                                    <td className="td1">
                                        <img src={`http://3.34.55.186:8088/${shop.img}`}
                                            width='250'
                                            height='190'
                                            className="img">
                                        </img>
                                    </td>
                                    <td className="td2">
                                        <p className="shopname">{shop.shopname}</p>
                                        <p className="address1">ADDRESS</p>
                                        <p className="address2"> {shop.address}</p>
                                        <p className="rspos1">RESERVE</p>
                                        <p className="rspos2"> {shop.isRsPos}</p>
                                        <p className="rspos1">OPEN</p>
                                        <p className="isopen2"> {shop.isOpen}</p>
                                    </td>
                                    <td className="td3">
                                        <Link to={`/menuList/${shop.shopId}`} className="menu-link">
                                            <span><button className="itembutton">매장보기</button></span>
                                        </Link>
                                        <span onClick={() => removeShop(`${shop.shopId}`)}><button className="itembutton">매장삭제</button></span>
                                    </td>
                                </div>

                            );
                        })}
                        <Link to="/shop">
                            <button className="btn-link">
                                매장 등록
                    </button>
                        </Link>
                    </div>
                </div>
                </body>
            </S.MyShopWrap>
        </>
    );
};

export default MyShop;
