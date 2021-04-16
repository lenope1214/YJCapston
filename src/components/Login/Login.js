import React, { useState } from "react";

import * as S from "./style";

const Login = ({ id, pw, handleId, handlePw, login, isLogin, test, a }) => {
    const [modal, setModal] = useState(true);
    const type = localStorage.getItem("type");

    const openModal = () => {
        setModal(true);
    };
    const closeModal = () => {
        setModal(false);
    };

    return (
        <>
            {<button onClick={openModal}>로그인</button>}
            {modal && (
                <>
                    {<S.Loginback onClick={closeModal}></S.Loginback>}
                    <S.LoginWrap>
                        <header>
                            <h1>주문의 민족에 오신걸 환영합니다.</h1>
                        </header>
                        <main>
                            <button onClick={test}>asdfsdaf</button>
                            <p>로그인 정보를 입력하세요!!!</p>
                            <input
                                type="text"
                                placeholder="Your ID"
                                onChange={handleId}
                                value={id}
                            />
                            <div>
                                {a.map((abc) => {
                                    return <div>abc.b_dtt</div>;
                                })}
                            </div>
                            {/* <img src="https://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=http://3.34.55.186:3000/shopcontent/5101695500/tablenum" /> */}
                            <input
                                type="password"
                                placeholder="Password"
                                onChange={handlePw}
                                value={pw}
                                onKeyPress={(e) => e.key === "Enter" && login()}
                            />
                        </main>
                        <footer>
                            <div>
                                <label>
                                    <input type="checkbox" />
                                    <span>기억하기</span>
                                </label>
                            </div>
                            <button onClick={login}>로그인</button>
                        </footer>
                    </S.LoginWrap>
                </>
            )}
        </>
    );
};

export default Login;
