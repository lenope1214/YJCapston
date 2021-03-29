import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import mainpicture from "./img/사용자페이지메인.jpg";
import yangtimjang from "./img/yangtimjang.png";

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
    restaurant,
}) => {
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
                            ></input>
                            <button>검색</button>
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
                    <body>
                        <div className="topCategory">
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
                        <div className="allbody">
                            <div className="mainimg">
                                <img
                                    className="mainimg-img"
                                    src={mainpicture}
                                />
                            </div>
                            <div className="leftCategory">
                                <ul className="moneykind">
                                    <input type="text" placeholder="최소금액" />
                                    <span>~</span>
                                    <input type="text" placeholder="최대금액" />
                                    <button>검색</button>
                                </ul>
                                <ul className="selectcategory">
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
                                <div className="image">
                                    <img
                                        className="yangtimjang"
                                        src={yangtimjang}
                                    />
                                </div>

                                <div className="content">{restaurant.name}</div>

                                <span>
                                    세상에서 제일 맛있는 불고기 김밥
                                    <br />
                                    <br />
                                    <br />
                                    <br />
                                    <br />
                                </span>
                                <span>메인메뉴 : 김밥</span>
                                <span>1줄 2000 &#8361;</span>
                            </div>
                        </div>
                    </body>
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
