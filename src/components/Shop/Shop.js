import React from "react";

import * as S from "./style";
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
    is_rs_pos,
    handleIs_rs_pos,
    search,
    modal,
    openModal,
    closeModal,
    handleSearch,
    shop_v1,
}) => {
    return (
        <>
            <S.ShopWrap>
                <header>
                    <Link to="/" class="shoppage">
                        <div className="left-nav">
                            주문
                            <span
                                style={{
                                    fontSize: "23px",
                                    paddingTop: "10px",
                                }}
                            >
                                의
                            </span>
                            민족
                        </div>
                    </Link>
                </header>
                <body>
                    <p>매장등록하기</p>
                    <span>사업자아이디</span>
                    <input
                        type="text"
                        id="Id"
                        placeholder="사업자번호"
                        onChange={handleId}
                        value={id}
                    />

                    <span>매장이름</span>
                    <input
                        type="text"
                        id="name"
                        placeholder="매장명"
                        onChange={handleShopname}
                        value={shopname}
                    />

                    <span>매장소개</span>
                    <input
                        type="text"
                        id="intro"
                        placeholder="매장소개"
                        onChange={handleIntro}
                        value={intro}
                    />

                    <span>오픈시간</span>
                    <input
                        type="time"
                        id="open_time"
                        name="open_time"
                        //    value="00:00"
                        onChange={handleOpen_time}
                        value={open_time}
                    />

                    <span>마감시간</span>
                    <input
                        type="time"
                        id="close_time"
                        name="close_time"
                        //    vlaue="00:00"
                        onChange={handleClose_time}
                        value={close_time}
                    />

                    <input
                        type="address"
                        id="address"
                        placeholder="주소를 입력하세요"
                        onChange={handleAddress}
                        value={address}
                        disabled
                    />
                    <button onClick={openModal}>검색</button>
                    <input
                        type="address1"
                        id="address1"
                        placeholder="상세 주소를 입력하세요"
                        onChange={handleAddress1}
                        value={address1}
                    />
                    <span>예약 가능여부</span>
                    <br></br>
                    <span
                        style={{
                            fontSize: "20px",
                            paddingTop: "20px",
                        }}
                    >
                        가능
                        <input
                            type="radio"
                            id="is_rs_pos"
                            name="is_rs_pos"
                            value={is_rs_pos}
                            onChange={handleIs_rs_pos}
                        />
                    </span>
                    <span
                        style={{
                            fontSize: "20px",
                            paddingTop: "20px",
                        }}
                    >
                        불가능
                        <input
                            type="radio"
                            id="is_rs_pos"
                            name="is_rs_pos"
                            value={is_rs_pos}
                            onChange={handleIs_rs_pos}
                        />
                    </span>
                </body>
                <footer>
                    <button onClick={shop_v1}>등록</button>
                </footer>
            </S.ShopWrap>

            {modal && (
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
                </S.searchWrap>
            )}
        </>
    );
};

export default Shop;
