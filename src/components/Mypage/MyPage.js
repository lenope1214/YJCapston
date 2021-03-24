import React, { useState } from "react";
import { Link } from "react-router-dom";

import * as S from "./style";

const MyPage = ({ Pw, handlePw, Mypage, user }) => {
    return (
        <>
            <S.MypageWrap>
                <header>
                    <h1>회원정보수정</h1>
                    <p>회원의 정보를 수정합니다.</p>
                </header>

                <body>
                    <p>아이디입력</p>
                    <span>사용자id</span>
                    <input type="text" id="id" placeholder={user.id} disabled />
                    <span>비밀번호</span>
                    <input
                        type="password"
                        id="password"
                        placeholder="입력하세요"
                        onChange={handlePw}
                        value={Pw}
                    />
                    <p>개인정보입력</p>
                    <span>이름</span>
                    <input
                        type="text"
                        id="name"
                        placeholder={user.name}
                        disabled
                    />
                    <span>이메일</span>
                    <input
                        type="text"
                        id="email"
                        placeholder="이메일 입력하세요"
                        value={user.email}
                    />
                    <span class="named">전화번호</span>
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
                    <span>-</span>
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile2"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.phone.substring(3, 7)}
                    />
                    <span>-</span>
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile3"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.phone.substring(7, 11)}
                    />
                    <span>point</span>
                    <input
                        class="phone"
                        type="text"
                        id="txtMobile3"
                        size="4"
                        onkeypress="onlyNumber();"
                        value={user.point}
                        disabled
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
