import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";

const Shoplist = ({
    isLogin,
    logout,
    openModal,
    id,
    pw,
    handleId,
    handlePw,
    login,
    closeModal,
    modal,
}) => {
    console.log(isLogin);
    return (
        <>
            <S.ShoplistWrap>
                <div className="App">
                    <div className="black-nav">
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
                        <div className="center-nav">
                            <input
                                type="text"
                                placeholder="매장을 검색하세요."
                                style={{
                                    width: "300px",
                                    border: "0",
                                    borderRadius: "7px 0 0 7px",
                                    backgroundColor: "white",
                                    hegight: "10px",
                                }}
                            ></input>
                            <button
                                style={{
                                    border: "0",
                                    width: "10%",
                                    borderRadius: "0 7px 7px 0",
                                    backgroundColor: "white",
                                    color: "grey",
                                }}
                            >
                                검색
                            </button>
                        </div>
                        {isLogin ? (
                            <div className="right-nav">
                                <button className="right1-nav" onClick={logout}>
                                    LOGOUT
                                </button>
                                <Link to="/mypage">
                                    <button class="right2-nav">MYPAGE</button>
                                </Link>
                            </div>
                        ) : (
                            <div className="right-nav">
                                <button
                                    className="right1-nav"
                                    onClick={openModal}
                                >
                                    LOGIN
                                </button>

                                <Link to="/register">
                                    <button class="right2-nav"> JOIN</button>
                                </Link>
                            </div>
                        )}
                    </div>
                </div>
            </S.ShoplistWrap>

            {modal && (
                <S.LoginWrap>
                    <header>
                        <h1>주문의 민족에 오신걸 환영합니다.</h1>
                    </header>
                    <main>
                        <p>로그인 정보를 입력하세요!!!</p>
                        <input
                            type="text"
                            placeholder="Your ID"
                            onChange={handleId}
                            value={id}
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            onChange={handlePw}
                            value={pw}
                            onKeyPress={(e) => e.key === "Enter" && login}
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
                        <button onClick={closeModal}>닫기</button>
                    </footer>
                </S.LoginWrap>
            )}
        </>
    );
};

export default Shoplist;
