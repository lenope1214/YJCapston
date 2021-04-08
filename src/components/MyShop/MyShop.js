import React from "react";
import { Link } from "react-router-dom";
import Shop from "../Shop/Shop";
import * as S from "./style";

const MyShop = ({
    isLogin,
    logout,
    openModal, 
    shop,
    removeShop,
    Id
}) => {
    return (
        <>
            <S.MyShopWrap>
            <div className="menu-container">
            <h2 className="menu-title">매장 목록</h2>
                <Link to="/shop">
                    <button className="btn-link">
                          매장등록하기
                    </button>
                    </Link>
                
 <table className="menu-list">
        <thead>
            <th align="center" className="item-1">매장사진</th>
            <th align="center" className="item-2">매장번호</th>
            <th align="center" className="item-3">매장이름</th>
            {/* <th align="center" className="intro">매장소개</th> */}
            <th align="center" className="item-4">카테고리</th>
            {/* <th align="center" className="openTime">오픈시간</th>
            <th align="center" className="closeTime">마감시간</th> */}
            <th align="center" className="item-5">매장주소</th>
            {/* <th align="center" className="addressDetail">매장상세주소</th> */}
            <th align="center" className="item-6">예약</th>
            <th align="center" className="item-7"></th>
            
        
        </thead>

        <tbody>
        
                {!shop.length && (
                    <tr>
                        <td colSpan="6">목록이 비었습니다.</td>
                    </tr>
                )}

            
        {!!shop.length && shop.map((shop) => {
            // console.log(shop.closeTime);
            return(    
            <tr>
            <td className="body-item-1">
                <img src={`http://3.34.55.186:8088/${shop.img}`}
                width='150'
                height='150'
                className="img-box">
                </img>
            </td>
            <td className="body-item-2">
                <Link to={`/menuList/${shop.id}`} className="menu-link">
                    {shop.id}
                </Link>
            </td>
            <td className="body-item-3">{shop.shopname}</td>
            {/* <td>{shop.intro}</td> */}
            <td className="body-item-4">{shop.category}</td>
            {/* <td>{shop.openTime}</td>
            <td>{shop.closeTime}</td> */}
            <td className="body-item-5">{shop.address}</td>
            {/* <td>{shop.addressDetail}</td> */}
            <td className="body-item-6">{shop.isRsPos}</td>
            <td className="body-item-7" onClick={() => removeShop(`${shop.id}`)}><button className="delete-button">X</button></td>
            
        </tr>

            );
        })}

        </tbody>
    </table>
    </div>
                
                <footer></footer>
            </S.MyShopWrap>
        </>
    );
};

export default MyShop;
