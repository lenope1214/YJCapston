import React from "react"
import * as S from "./style";

const MenuRead = ({
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
    menuDuration
}) => {
    return (
        <>
            <S.MenuReadWrap>
                <h2 className="menu-title">메뉴 상세</h2>
                <div className="menu-register-container">
                    <table className="register-form">
                        <tbody>
                            <tr>
                                <th colSpan='2' align='center' className="img-box">
                                    <img src={`http://3.34.55.186:8088/${img}`}
                                        width='80%'
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
                                        value={name}
                                        className="input-box"
                                        readOnly
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
                        </tbody>
                    </table>
                    <div className="button-div">
                        <button onClick={ReadMenu} className="button">수정</button>
                        <button className="button" onClick={goBack}>취소</button>
                    </div>
                </div>
            </S.MenuReadWrap>
        </>
    );
}

export default MenuRead;