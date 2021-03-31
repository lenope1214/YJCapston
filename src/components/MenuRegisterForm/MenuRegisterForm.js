import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";

const MenuRegisterForm = ({
    menuname,
    handleMenuname,
    price,
    handlePrice,
    img,
    handleImg,
    menudesc,
    handleMenudesc,
    menu_v1,
}) => {
    return(
        <>
        <S.MenuRegisterWrap>
        <h2 className="menu-title">메뉴 추가</h2>
        <div className="menu-register-container">   
                <table className="register-form">
                    <tbody>
                        <tr>
                            <th className="form-label">메뉴명</th>
                            <td>
                                <input
                                    type="text"
                                    id="name"
                                    placeholder="메뉴명"
                                    value={menuname}
                                    onChange={handleMenuname} 
                                />
                            </td>
                        </tr>
                        <tr>
                            <th className="form-label">메뉴가격</th>
                            <td>
                                <input
                                    type="text"
                                    id="price"
                                    placeholder="메뉴가격"
                                    value={price}
                                    onChange={handlePrice} 
                                />
                                <span className="in-won">₩</span>
                            </td>
                        </tr>
                        <tr>
                            <th className="form-label">메뉴이미지</th>
                            <td>
                                <input 
                                    type="file"
                                    id="img_url"
                                    value={img}
                                    onChange={handleImg}
                                />
                            </td>
                        </tr>
                        <tr>
                            <th className="form-label">메뉴설명</th>
                            <td>
                                <textarea 
                                    row="5"
                                    placeholder="메뉴설명"
                                    id="description"
                                    value={menudesc}
                                    onChange={handleMenudesc}
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div className="button-div">
                    <button onClick={menu_v1} className="button">추가</button>
                    <Link to="/menulist" className="button">
                        <button className="button">취소</button>
                    </Link>
                </div>
            
        </div>
        </S.MenuRegisterWrap>
        </>
    );
}

export default MenuRegisterForm;