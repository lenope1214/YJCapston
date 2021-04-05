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
                                <th className="form-label"></th>
                                
                                    <img src={img}
                                    width='200'
                                    height='200'>
                                        {console.log(menuImg)}
                                    </img>
                                
                            </tr>
                            <tr>
                                <th className="form-label">상품명</th>
                                <td>
                                    <input
                                        type="text"
                                        value={name}
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
                                <th className="form-label">소요시간</th>
                                <td>
                                    <input
                                        type="text"  
                                        placeholder={duration}
                                        onChange={handleDuration}
                                        value={menuDuration}
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
                        
                        <button className="button" onClick={goBack}>취소</button>
                        
                    </div>
                </div>
            </S.MenuReadWrap>
        </>
    );
}

export default MenuRead;