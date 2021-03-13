import React, { useState } from "react";

import * as S from "./style";

const MyPage = ({ Pw, handlePw }) => {
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
                    <input
                        type="text"
                        id="id"
                        placeholder="tjdqhr123"
                        disabled
                    />
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
                        placeholder="홍길동"
                        disabled
                    />
                    <span>이메일</span>
                    <input
                        type="text"
                        id="email"
                        placeholder="니 이메일 입력하세요"
                    />
                </body>
                <footer>
                    <button>수정</button>
                    <button>삭제</button>
                </footer>
            </S.MypageWrap>
        </>
    );
};

export default MyPage;
