import React from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
import DaumPostcode from "react-daum-postcode";
import topimg from "../Main/img/QR코드사진2.png";

const Shop = ({
    shopId,
    handleshopId,
    shopname,
    handleShopname,
    intro,
    handleIntro,
    open_time,
    handleOpen_time,
    close_time,
    handleClose_time,
    address,
    handleAddress,
    address1,
    handleAddress1,
    // is_rs_pos,
    // handleIs_rs_pos,
    img_url,
    handleImg_url,
    search,
    modal,
    openModal,
    closeModal,
    handleSearch,
    category,
    handleCategory,
    shop_v1,
    roadAddr,
    handleComplete,
    goBack,
    phone1,
    handlePhone1,
    phone2,
    handlePhone2,
    phone3,
    handlePhone3,
}) => {
    const postCodeStyle = {
        display: "block",
        position: "absolute",
        top: "50%",
        width: "400px",
        height: "500px",
        padding: "7px",
        left: "50%",
        transform: "translate(-50%, -50%)",
    };
    return (
        <>
            <S.ShopsWrap>
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>매장 등록하기</p>
                </div>
                    <body>
                    <div className="total-body">
                        <div className="label">사업자아이디</div>
                        <input
                            type="text"
                            id="Id"
                            placeholder="사업자번호"
                            onChange={handleshopId}
                            value={shopId}
                            className="input-box"
                        />

                        <div className="label">매장이름</div>
                        <input
                            type="text"
                            id="name"
                            placeholder="매장명"
                            onChange={handleShopname}
                            value={shopname}
                            className="input-box"
                        />

                        <div className="label">매장전화번호</div>
                        <div>
                            <select
                                id="num1"
                                onChange={handlePhone1}
                                value={phone1}
                                className="phone-box1"
                            >
                                <option value="">::선택::</option>
                                <option value="010">010</option>
                                <option value="02">02</option>
                                <option value="051">051</option>
                                <option value="053">053</option>
                                <option value="031">031</option>
                                <option value="050">050</option>
                            </select>
                            <span>-</span>
                            <input
                                className="phone-box"
                                type="text"
                                id="num2"
                                size="4"
                                onkeypress="onlyNumber();"
                                placeholder="ex)1234"
                                onChange={handlePhone2}
                                value={phone2}
                            />
                            <span>-</span>
                            <input
                                className="phone-box"
                                type="text"
                                id="num3"
                                size="4"
                                placeholder="ex)1234"
                                onChange={handlePhone3}
                                value={phone3}
                            />
                        </div>

                        <div className="label">사진등록</div>

                        <input
                            type="file"
                            name="img_url"
                            onChange={handleImg_url}
                            className="input-box-img"
                        />

                        <div className="label">매장소개</div>
                        <textarea
                            row="8"
                            id="intro"
                            placeholder="매장소개"
                            className="input-box"
                            onChange={handleIntro}
                            value={intro}
                        />

                        <div className="label">오픈시간</div>
                        <input
                            type="text"
                            id="open_time"
                            name="open_time"
                            placeholder="00:00"
                            onChange={handleOpen_time}

                            className="input-box"
                            value={open_time}
                        />

                        <div className="label">마감시간</div>
                        <input
                            type="text"
                            id="close_time"
                            name="close_time"
                            placeholder="00:00"
                            onChange={handleClose_time}
                            className="input-box"
                            value={close_time}
                        />

                        <div className="label">카테고리</div>
                        <select
                            id="category"
                            onChange={handleCategory}
                            value={category}
                        >
                            <option value="" disable={true}>
                                선택
                        </option>
                            <option value="한식">한식</option>
                            <option value="일식">일식</option>
                            <option value="중식">중식</option>
                            <option value="양식">양식</option>
                            <option value="고기">고기</option>
                            <option value="분식">분식</option>
                            <option value="술집">술집</option>
                            <option value="패스트푸드">패스트푸드</option>
                            <option value="찜.탕">찜.탕</option>
                            <option value="카페.디저트">카페.디저트</option>
                        </select>
                        <div className="label">주소</div>
                        <input
                            type="text"
                            id="address"
                            placeholder={roadAddr}
                            onChange={handleAddress}
                            value={roadAddr}
                            className="input-box"
                            disabled
                        />
                        <button onClick={openModal} className="button1">검색</button>
                        <div>
                        <input
                            type="text"
                            id="address1"
                            placeholder="상세 주소를 입력하세요"
                            onChange={handleAddress1}
                            value={address1}
                            className="input-box"
                        />
                        </div>
                        <div className="button-box">
                            <button onClick={shop_v1} className="button3">등록</button>
                            <Link to="/myshop" onClick={() => { window.scrollTo(0, 0) }}>
                                <button className="button4" >취소</button>
                            </Link>
                        </div>
                        </div>
                    </body>
                
            </S.ShopsWrap>
            {modal && (
                <DaumPostcode
                    onComplete={handleComplete}
                    style={postCodeStyle}
                    height={700}
                />
            )}
        </>
    );
};

export default Shop;
