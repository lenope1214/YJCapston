import React from "react"
import { Link } from "react-router-dom"
import * as S from "./style";

const MenuRead = ({ 
    menuId,
    name, 
    intro, 
    price, 
    menuName,
    handleMenuName,
    menuPrice,
    handleMenuPrice,
    menuImg,
    handleMenuImg,
    menuIntro,
    handleMenuIntro,
    ReadMenu,
}) => {
    return (
        <>
            <S.MenuReadWrap>
            <h2 className="menu-title">메뉴 상세</h2>
                <div className="menu-register-container">       
                    <table className="register-form">
                        <tbody>
                            <tr>
                                <th className="form-label">상품명</th>
                                <td>
                                    <input
                                        type="text"
                                        placeholder={name}
                                        onChange={handleMenuName}
                                        value={menuName}
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">상품가격</th>
                                <td>
                                    <input
                                        type="text"  
                                        placeholder={price}
                                        onChange={handleMenuPrice}
                                        value={menuPrice}
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">상품소개</th>
                                <td>
                                    <textarea
                                        row="3"
                                        placeholder={intro}
                                        onChange={handleMenuIntro}
                                        value={menuIntro}
                                    />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="button-div">
                        <button onClick={ReadMenu} className="button">수정</button>
                        <Link to="/menulist" className="button">
                        <button className="button">취소</button>
                        </Link>
                    </div>
                </div>
            </S.MenuReadWrap>
        </>
    );
}

export default MenuRead;