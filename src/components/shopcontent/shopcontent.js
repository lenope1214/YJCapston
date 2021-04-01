import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";

const Shopcontent = ({
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
    return (
        <>
            <S.shopcontentWrap>
                <div class="App">
                    <div class="black-nav">
                        <div class="left-nav">
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
                        <div class="center-nav">
                            <input
                                type="text"
                                placeholder="매장을 검색하세요."
                            ></input>
                            <button>검색</button>
                        </div>
                        {isLogin ? (
                            <div class="right-nav">
                                <button class="right1-nav" onClick={logout}>
                                    LOGOUT
                                </button>
                                <Link to="/mypage">
                                    <button class="right2-nav">MYPAGE</button>
                                </Link>
                            </div>
                        ) : (
                            <div class="right-nav">
                                <button class="right1-nav" onClick={openModal}>
                                    LOGIN
                                </button>

                                <Link to="/register">
                                    <button class="right2-nav"> JOIN</button>
                                </Link>
                            </div>
                        )}
                    </div>
                    <body>
                        <div class="topCategory">
                            <li>한식</li>
                            <li>중식</li>
                            <li>일식</li>
                            <li>양식</li>
                            <li>술집</li>
                            <li>분식</li>
                            <li>고기</li>
                            <li>찜탕</li>
                            <li>카페 디저트</li>
                            <li>패스트푸드</li>
                            <li class="cityreview">실시간 리뷰</li>
                            <li class="reviewevent">리뷰이벤트</li>
                        </div>
                        <div class="allbody">
                            <div class="leftCategory">
                                <ul class="moneykind">
                                    <input type="text" placeholder="최소금액" />
                                    <span>~</span>
                                    <input type="text" placeholder="최대금액" />
                                    <button>검색</button>
                                </ul>
                                <ul class="selectcategory">
                                    <button>예약 많은 순</button>
                                    <br />
                                    <button>주문 많은 순</button>
                                    <br />
                                    <button>
                                        최소주문금액 <br /> ↓
                                    </button>
                                    <br />
                                    <button>별점순</button>
                                </ul>
                            </div>
                            <div clasName="menulist">
                                <div class="list_shop"></div>
                                {/* {restaurant.map((test, i) => {
                                    return <div test={i}>{test.intro}</div>;
                                })}
                                {restaurant.map((test, i) => {
                                    return <div test={i}>{test.category}</div>;
                                })} */}
                            </div>
                        </div>
                    </body>
                    <footer>
                        copyright 2021 yeongJin university capston WDA team 4.
                    </footer>
                </div>
            </S.shopcontentWrap>

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

export default Shopcontent;
