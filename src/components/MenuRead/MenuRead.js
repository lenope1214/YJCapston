import React from "react"
import * as S from "./style";

const MenuRead = ({
    shopId,
    name,
    intro,
    price,
    menuPrice,
    handleMenuPrice,
    menuImg,
    img,
    handleMenuImg,
    menuIntro,
    handleMenuIntro,
    ReadMenu,
    goBack,
    duration,
    handleDuration,
    menuDuration,
    isPopular,
    isSale,
    menu_v2,
    menu_v3,
    handleMenuName,
    menuName,
}) => {
    return (
        <>
            <S.MenuReadWrap>
                
                <div className="menu-register-container">
                <h2 className="menu-title">메뉴 상세</h2>
                    <table className="register-form">
                        <tbody>
                            <tr>
                                <th colSpan='2' align='center' className="img-box">
                                    <img src={`http://3.34.55.186:8088/${img}`}
                                        width='60%'
                                        // height='60%'
                                        className="img-box-box"
                                    />
                                </th>
                            </tr>
                            <tr>
                                <th className="form-label">상품명</th>
                                <td>
                                    <input
                                        type="text"
                                        // value={name}
                                        // value={shopId}
                                        className="input-box"
                                        placeholder={name}
                                        value={menuName}
                                        onChange={handleMenuName}
                                        // readOnly
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
                                        className="input-box"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">소요시간</th>
                                <td>
                                    <input
                                        type="text"
                                        placeholder={duration}
                                        onChange={handleDuration}
                                        value={menuDuration}
                                        className="input-box"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">상품소개</th>
                                <td>
                                    <textarea
                                        row="4"
                                        placeholder={intro}
                                        onChange={handleMenuIntro}
                                        value={menuIntro}
                                        className="input-box-area"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">인기상품</th>
                                <td>
                                <input
                                        type="text"
                                        value={isPopular}
                                        className="input-box"
                                        readOnly
                                    />
                                    <button onClick={menu_v2} className="ps-button">인기여부변경</button>
                                </td>
                            </tr>
                            <tr>
                                <th className="form-label">판매중</th>
                                <td>
                                <input
                                        type="text"
                                        value={isSale}
                                        className="input-box"
                                        readOnly
                                    />
                                    <button onClick={menu_v3} className="ps-button">판매여부변경</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="button-div">
                        <button onClick={ReadMenu} className="button3">수정</button>
                        <button className="button4" onClick={goBack}>취소</button>
                    </div>
                </div>
            </S.MenuReadWrap>
        </>
    );
}

export default MenuRead;