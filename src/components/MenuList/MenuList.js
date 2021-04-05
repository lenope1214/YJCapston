import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";

const MenuList = ({ menues, removeMenu, shopId }) => {
    return (
        <>
            <S.MenuWrap>
                <div className="menu-container">
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
                                    <td colSpan="3">목록이 비었습니다.</td>
                                </tr>
                            )}

                            {!!menues.length && menues.map((menu) => {
                                return (
                                    <tr>
                                        <td className="body-item-1">
                                            <img src={`${menu.img}`}
                                                width='100'
                                                height='100'>
                                                {console.log(`${menu.img}`)}
                                            </img>
                                        </td>
                                        <td className="body-item-2">
                                            <Link to={`/menu/${shopId}${menu.name}`} className="menu-link">
                                                {menu.name}
                                            </Link>
                                        </td>
                                        <td className="body-item-3">{menu.price}</td>
                                        <td className="body-item-4" onClick={() => removeMenu(`${menu.id}`)}><button>X</button></td>
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