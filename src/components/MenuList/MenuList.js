import { Link } from "react-router-dom";
import React, { useState, useEffect } from 'react';

import * as S from "./style";

const MenuList = ({ menues, removeMenu, shopId }) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    return (
        <>
            <S.MenuWrap>
                <div className="menu-container">
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
                                            <Link to={`/menu/${shopId}${menu.name}`} className="menu-link">
                                                <img src={`http://3.34.55.186:8088/${menu.img}`}
                                                    width='150'
                                                    height='150'
                                                    className="img-box">
                                                </img>
                                            </Link>

                                        </td>
                                        <td className="body-item-2">
                                            <Link to={`/menu/${shopId}${menu.name}`} className="menu-link">
                                                {menu.name}
                                            </Link>
                                        </td>
                                        <td className="body-item-3"><Link to={`/menu/${shopId}${menu.name}`} className="menu-link">
                                            {menu.price}
                                        </Link></td>
                                        <td className="body-item-4" onClick={() => removeMenu(`${menu.id}`)}><button className="delete-button">X</button></td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            </S.MenuWrap>
        </>
    );
}

export default MenuList;