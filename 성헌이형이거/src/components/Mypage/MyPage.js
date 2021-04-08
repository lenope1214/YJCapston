import React, { useState } from "react";
import { Link } from "react-router-dom";

import * as S from "./style";

import DaumPostcode from "react-daum-postcode";

const MyPage = ({
    Pw,
    handlePw,
    Mypage,
    user,
    modal,
    openmodal,
    handleComplete,
    roadAddr,
    handleAddress,
    handleAddressDetail,
    addressDetail,
    handleRoadAddr,
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
        border: "1px",
    };

    return (
        <>
            <div class="abc">
                {modal && (
                    <DaumPostcode
                        onComplete={handleComplete}
                        style={postCodeStyle}
                        height={700}
                    />
                )}
            </div>
            <S.MypageWrap>
                <header>
                    <h1>회원정보수정</h1>
                    <p>회원의 정보를 수정합니다.</p>
                </header>

                <body>
                    <p>아이디입력</p>
                    <span class="id">사용자id</span>
                    <input type="text" id="id" placeholder={user.id} disabled />
                    <br></br>
                    <span>비밀번호</span>
                    <input
                        type="password"
                        id="password"
                        placeholder="입력하세요"
                        onChange={handlePw}
                        value={Pw}
                    />
                    <p>개인정보입력</p>
                    <span class="name">이름</span>
                    <input
                        type="text"
                        id="name"
                        placeholder={user.name}
                        disabled
                    />
                    <br></br>
                    <span class="email">이메일</span>
                    <input
                        type="text"
                        id="email"
                        placeholder="이메일 입력하세요"
                        value={user.email}
                    />
                    <br></br>
                    <span class="phone">전화번호</span>
                    <select id="txtMobile1" defaultValue="010">
                        <option value="" disabled={true}>
                            ::선택::
                        </option>
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="019">019</option>
                    </select>
                    -
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile2"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.phone.substring(3, 7)}
                    />
                    -
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile3"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.phone.substring(7, 11)}
                    />
                    <br></br>
                    <span class="point">point</span>
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile3"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.point}
                        disabled
                    />
                    <br></br>
                    <span class="address">주소</span>
                    <input
                        id="address"
                        placeholder={user.address}
                        value={roadAddr}
                        onChange={handleRoadAddr}
                        disabled
                    />
                    <button onClick={openmodal}>검색</button>
                    <br></br>
                    <span class="addressDetail">상세주소</span>
                    <input
                        placeholder={user.addressDetail}
                        onChange={handleAddressDetail}
                        value={addressDetail}
                    />
                </body>

                <footer>
                    <button onClick={Mypage}>수정</button>
                    <Link to="/">
                        <button>취소</button>
                    </Link>
                </footer>
            </S.MypageWrap>
        </>
    );
};

export default MyPage;
