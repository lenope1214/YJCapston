import React from "react";
import { Link } from "react-router-dom";
import * as S from "./style";

const Shoplist = ({ isLogin, logout, openModal }) => {
    return (
        <>
            <S.ShoplistWrap>
                <header>
                    <Link to="/" class="movemainpage">
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
                    <div className="center-nav">
                        <input
                            type="text"
                            placeholder="매장을 검색하세요."
                            style={{
                                width: "300px",
                                border: "0",
                                borderRadius: "3px 0 0 3px",

                                backgroundColor: "white",
                            }}
                        ></input>
                        <button
                            style={{
                                border: "0",
                                width: "10%",
                                borderRadius: "0 3px 3px 0",
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
                            <Link to="/mypage" class="right2-nav">
                                MYPAGE
                            </Link>
                        </div>
                    ) : (
                        <div className="right-nav">
                            <button className="right1-nav" onClick={openModal}>
                                LOGIN
                            </button>

                            <Link to="/register" class="right2-nav">
                                JOIN
                            </Link>
                        </div>
                    )}
                </header>
                <main></main>
                <footer></footer>
            </S.ShoplistWrap>
        </>
    );
};

export default Shoplist;
