import React from "react";

import * as S from "./style";
import DaumPostcode from "react-daum-postcode";
import { Link } from "react-router-dom";

const Shop = ({
    id,
    handleId,
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
}) => {
    const postCodeStyle = {
        display: "block",
        position: "fixed",
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
                    <p>매장등록하기</p>
                <body>
                    
                    {/* <form encType="multipart/form-data" className="form"> */}
                    <div>사업자아이디</div>
                    <input
                        type="text"
                        id="Id"
                        placeholder="사업자번호"
                        onChange={handleId}
                        value={id}
                    />

                    <div>매장이름</div>
                    <input
                        type="text"
                        id="name"
                        placeholder="매장명"
                        onChange={handleShopname}
                        value={shopname}
                    />

                    <div>사진등록</div>

                    <input
                        type="file"
                        // id="img_url"
                        name="img_url"
                        // value={img_url}
                        onChange={handleImg_url}
                    />

                    <div>매장소개</div>
                    <textarea
                        row="8"
                        id="intro"
                        placeholder="매장소개"
                        onChange={handleIntro}
                        value={intro}
                    />

                    <div>오픈시간</div>
                    <input
                        type="text"
                        id="open_time"
                        name="open_time"
                        placeholder="00:00"
                        onChange={handleOpen_time}
                        value={open_time}
                    />

                    <div>마감시간</div>
                    <input
                        type="text"
                        id="close_time"
                        name="close_time"
                        placeholder="00:00"
                        onChange={handleClose_time}
                        value={close_time}
                    />

                    <div class="category">카테고리</div>
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
                        <option value="고기">고기</option>
                        <option value="분식">분식</option>
                        <option value="술집">술집</option>
                        <option value="패스트푸드">패스트푸드</option>
                        <option value="찜.탕">찜.탕</option>
                        <option value="카페.디저트">카페.디저트</option>
                    </select>

                    <input
                        type="text"
                        id="address"
                        placeholder={roadAddr}
                        onChange={handleAddress}
                        value={roadAddr}
                        disabled
                    />
                    <button onClick={openModal}>검색</button>
                    <input
                        type="text"
                        id="address1"
                        placeholder="상세 주소를 입력하세요"
                        onChange={handleAddress1}
                        value={address1}
                    />

                    {/* <span>예약 가능여부</span>
                    <br></br>

                    <span
                        style={{
                            fontSize:"20px",
                            paddingTop:"20px",
                        }}
                        >
                            가능
                    <input
                        type="radio"
                        id="is_rs_pos1"
                        name="is_rs_pos"
                        value={is_rs_pos = "Y"}
                        onChange={handleIs_rs_pos}
                        // checked
                        />
                        </span>
                        <span
                        style={{
                            fontSize:"20px",
                            paddingTop:"20px",
                        }}
                        >
                            불가능
                    <input
                        type="radio"
                        id="is_rs_pos"
                        name="is_rs_pos"
                        value={is_rs_pos = "N"}
                        onChange={handleIs_rs_pos}
                        />
                        </span>     */}

                    {/* </form> */}
                </body>

                <footer>
                    <button onClick={shop_v1} className="button1">
                        등록
                    </button>
                </footer>
            </S.ShopsWrap>

            {/* {modal && (
                <S.searchWrap>
                    <header>
                        <h1>주소를 검색해주세요</h1>
                    </header>
                    <body>
                        <input
                            type="text"
                            placeholder="주소를 입력하세요"
                            onChange={handleSearch}
                        />
                        
                    </body>
                    <button>확인</button>
                    <button onClick={closeModal}>닫기</button>
                </S.searchWrap> */}

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
