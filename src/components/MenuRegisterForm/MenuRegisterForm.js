import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import topimg from "../Main/img/QR코드사진2.png";

const MenuRegisterForm = ({
    menuname,
    handleMenuname,
    price,
    handlePrice,
    handleImg,
    menudesc,
    handleMenudesc,
    menu_v1,
    duration,
    handleDuration,
    shopId,
}) => {
    return (
        <>
            <S.MenuRegisterWrap>
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>메뉴 추가</p>
                </div>
                <div className="total-body">
                    <form encType="multipart/form-data">
                                    <div className="label">메뉴명</div>
                                    <div>
                                        <input
                                            type="text"
                                            id="name"
                                            placeholder="메뉴명"
                                            value={menuname}
                                            onChange={handleMenuname}
                                            className="input-box"
                                        />
                                    </div>
      
                                <div>
                                    <div className="label">메뉴가격</div>
                                    <div>
                                        <input
                                            type="number"
                                            id="price"
                                            placeholder="메뉴가격"
                                            value={price}
                                            onChange={handlePrice}
                                            className="input-box"
                                        />
        
                                        <span className="in-won">₩</span>
                 
                                    </div>
                                </div>
                                <div>
                                    <div className="label">메뉴이미지</div>
                                    <div>
                                        <input
                                            type="file"
                                            onChange={handleImg}
                                            className="input-box"
                                        />
                                    </div>
                                    <div className="label">소요시간</div>
                                    <div>
                                        <input
                                            type="number"
                                            id="duration"
                                            placeholder="예상 소요시간(분)"
                                            value={duration}
                                            onChange={handleDuration}
                                            className="input-box"
                                        />
                                        <span className="in-minute">분</span>
                                    </div>
                                    <div className="label">메뉴설명</div>
                                    <div>
                                        <textarea
                                            row="8"
                                            placeholder="메뉴설명"
                                            id="description"
                                            value={menudesc}
                                            onChange={handleMenudesc}
                                            className="input-box-area"
                                        />
                                    </div>
                                    </div>
                    </form>
                    <div className="button-box">
                        <button onClick={menu_v1} className="button3">추가</button>
                        <Link to={`/menulist/${shopId}`} className="button">
                            <button className="button4">취소</button>
                        </Link>
                    </div>
                </div>
            </S.MenuRegisterWrap>
        </>
    );
}

export default MenuRegisterForm;