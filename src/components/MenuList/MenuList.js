import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import {getMenuRead, getNowMenu} from "../../lib/MenuRead"
import MenuReadContainer from "../../containers/MenuRead/MenuReadContainer";

const MenuList = ({ menues }) => {
    return (
        <>
            <S.MenuWrap>
                <div className="menu-container">
                    <h2 className="menu-title">메뉴 목록</h2>
                    <Link to="/create" className="btn-link">메뉴 추가하기</Link>
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
                                    
                                    <td>이미지</td>
                                    <Link to={`/menu/${menu.id}`} className="menu-list">       
                                    <td>{menu.name}</td>
                                    </Link>
                                    <td>{menu.price}</td>
                                    <td><button>삭제</button></td>
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