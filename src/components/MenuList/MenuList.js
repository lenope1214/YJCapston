import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import {removeMenus} from "../../lib/MenuList/index";
import topimg from "../Main/img/QR코드사진2.png";

const MenuList = ({ menues, shopId }) => {
    return (
        <>
            <S.MenuWrap>
            <body>
            <Link to={"/myshop"}>
                        <button className="pos-button">매장 목록으로</button>
                    </Link>
                <div className="totals">

                    <div>
                        <img src={topimg} className="topimg" />
                    </div>
                    <div className="topimg-text">
                        <p>메뉴 목록</p>
                    </div>
                    <div className="menu-container">
                        {!menues.length && (
                            <div>
                                <td colSpan="8" className="empty-list">메뉴를 추가해주세요.</td>
                            </div>
                        )}
                        {!!menues.length && menues.map((menu) => {
                            // console.log(shop.closeTime);
                            return (

                                <div className="oneblock">
                                    <td className="td1">
                                        <img src={`http://3.34.55.186:8088/${menu.img}`}
                                            width='250'
                                            height='190'
                                            className="img">
                                        </img>
                                    </td>
                                    <td className="td2">
                                        <p className="shopname">{menu.name}</p>
                                        <p className="address1">PRICE</p>
                                        <p className="address2"> {menu.price}원</p>
                                    </td>
                                    <td className="td3">
                                    <Link to={`/menu/${menu.id}`} className="menu-link">
                                            <span><button className="itembutton">메뉴보기</button></span>
                                        </Link>
                                        <span onClick={async() => {
                                                            const res = await removeMenus(
                                                                    shopId,menu.id
                                                                );
                                                            }}><button className="itembutton">메뉴삭제</button></span>
                                    </td>

                                </div>

                            );
                        })}
                        <Link to={`/create/${shopId}`}>
                            <button className="btn-link">
                                메뉴 등록
                    </button>
                        </Link>
                    </div>
                </div>
                </body>
                {/* <div className="menu-container">
                <Link to={"/posmain/"+shopId}>
                        <button className="pos-button">포스기로 이동</button>
                    </Link>
                    <h2 className="menu-title">메뉴 목록</h2>
                    <Link to={`/create/${shopId}`} className="btn-link">메뉴 추가하기</Link>
                    <table className="menu-list">
                        <thead>
                            <th align="center" className="item-1">메뉴사진</th>
                            <th align="center" className="item-2">메뉴명</th>
                            <th align="center" className="item-3">메뉴가격</th>
                            <th align="center" className="item-4"></th>
                        </thead>
                        <tbody>
                            {!menues.length && (
                                <tr>
                                    <td colSpan="4" className="empty-list">목록이 비었습니다.</td>
                                </tr>
                            )}

                            {!!menues.length && menues.map((menu) => {
                                return (
                                    <tr>
                                        <td className="body-item-1">
                                            <Link to={`/menu/${menu.id}`} className="menu-link">
                                                <img src={`http://3.34.55.186:8088/${menu.img}`}
                                                    width='150'
                                                    height='150'
                                                    className="img-box">
                                                </img>
                                            </Link>

                                        </td>
                                        <td className="body-item-2">
                                            <Link to={`/menu/${menu.id}`} className="menu-link">
                                                {menu.name}
                                            </Link>
                                        </td>
                                        <td className="body-item-3"><Link to={`/menu/${menu.id}`} className="menu-link">
                                            {menu.price}
                                        </Link></td>
                                        <td className="body-item-4" onClick={async() => {
                                                            const res = await removeMenus(
                                                                    shopId,menu.id
                                                                );
                                                            }}><button className="delete-button">X</button></td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div> */}
            </S.MenuWrap>
        </>
    );
}

export default MenuList;