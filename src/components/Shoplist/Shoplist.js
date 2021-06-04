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
    showShoplist,
    restaurant,
    showkorean,
    showchinese,
    showJapan,
    showforign,
    showdrink,
    showboon,
    showmeat,
    showjjimtang,
    showcafe,
    showfastfood,
    showMarks,
    marklist
    
}) => {
    console.log(restaurant);
    let AWS_BASE_URL = "http://3.34.55.186:8088/";
    return (
        <>
            <S.ShoplistWrap>
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
                                class="search-box"
                            ></input>
                            <button class="search-button">검색</button>
                        </div>
                        {isLogin ? (
                            <div class="right-nav">
                                <button class="right1-nav" onClick={logout}>
                                    LOGOUT
                                </button>
                                <Link to="/mypage" className="but-back">
                                    <button class="right2-nav">MYPAGE</button>
                                </Link>
                            </div>
                        ) : (
                            <div class="right-nav">
                                <button class="right1-nav" onClick={openModal}>
                                    LOGIN
                                </button>

                                <Link to="/register" className="but-back">
                                    <button class="right2-nav"> JOIN</button>
                                </Link>
                            </div>
                        )}
                    </div>
                    <body>
                        <div class="topCategory">
                            <button onClick={showkorean}>
                                <li>한식</li>
                            </button>
                            <button onClick={showchinese}>
                                <li>중식</li>
                            </button>
                            <button onClick={showJapan}>
                                <li>일식</li>
                            </button>
                            <button onClick={showforign}>
                                <li>양식</li>
                            </button>
                            <button onClick={showdrink}>
                                <li>술집</li>
                            </button>
                            <button onClick={showboon}>
                                <li>분식</li>
                            </button>
                            <button onClick={showmeat}>
                                <li>고기</li>
                            </button>
                            <button onClick={showjjimtang}>
                                <li>찜탕</li>
                            </button>
                            <button onClick={showcafe}>
                                <li>카페 디저트</li>
                            </button>
                            <button onClick={showfastfood}>
                                <li>패스트푸드</li>
                            </button>
                            <button onClick={showMarks}>
                                <li class="cityreview">찜 목록</li>
                            </button>
                            <button onClick={showShoplist}>
                                <li class="reviewevent">모두보기</li>
                            </button>
                        </div>
                        <div class="allbody">
                            <div class="mainimg">
                                <img class="mainimg-img" src={mainpicture} />
                            </div>
                            <div class="guidetext">
                                
                                <button className="all-category" onClick={showShoplist}>매장 목록</button>     
                                {/* <Link to="/Marklist"> */}
                                {/* <button className="mark-category" onClick={showMarks}>찜 목록</button>  */}
                                {/* </Link> */}
                                <hr class="hrstyle" />
                                {/* <hr class="hrstyle2" /> */}
                            </div>
                            <div clasName="menulist">
                                <div class="leftCategory">
                                    <select class="moneykind">
                                        <option value="예약 많은 순">
                                            예약 많은 순
                                        </option>
                                        <option value="최소주문금액">
                                            최소주문금액
                                        </option>
                                        <option value="별점순">별점순</option>
                                    </select>
                                    <button>검색</button>
                                </div>
                                <div class="list_shop">
                                    {!restaurant.length && (
                                        <div className="none-shop">매장이 없습니다.</div>
                                    )}
                                    {!!restaurant.length && restaurant.map((shop) => {
                                        return (
                                            <Link
                                                to={`/shopcontent/${shop.id}`}
                                            >
                                                <button>
                                                    <div class="testname">
                                                        <div class="image">
                                                            <img
                                                                class="yangtimjang"
                                                                src={
                                                                    AWS_BASE_URL +
                                                                    shop.img
                                                                }
                                                            />
                                                        </div>

                                                        <div class="listcontent">
                                                            <div class="linesetting">
                                                                <div class="listname">
                                                                    {shop.name}
                                                                </div>
                                                                {/* <div class="listcategory">
                                                                    {
                                                                        shop.category
                                                                    }
                                                                </div> */}
                                                            </div>
                                                            <div class="listAddress">
                                                                평점 ★ {shop.score} ({shop.reviews})
                                                            </div>

                                                            {/* <div>
                                                                {
                                                                    shop.addressDetail
                                                                }
                                                            </div> */}
                                                        </div>
                                                    </div>
                                                </button>
                                            </Link>
                                        );
                                    })}
                                </div>
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
                        copyright 2021 YeongJin university capston WDA team 4.
                    </footer>
                </div>
            </S.ShoplistWrap>

            {modal && (
                <S.LoginWrap>
                    <header>
                        <h1 className="login-title">
                            주문
                            <span
                                style={{
                                    fontSize: "17px",
                                    paddingTop: "10px",
                                }}
                            >
                                의
                            </span>
                            민족
                        </h1>
                    </header>
                    <main>
                        <p className="login-text">로그인 정보를 입력</p>
                        <input
                            type="text"
                            placeholder="ID"
                            onChange={handleId}
                            value={id}
                            className="login-input"
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            onChange={handlePw}
                            value={pw}
                            className="login-input"
                            onKeyPress={(e) => e.key === "Enter" && login}
                        />
                    </main>
                    <footer>
                        <div className="remeber">
                            <label>
                                <input type="checkbox" />
                                <span>기억하기</span>
                            </label>
                        </div>
                        <div className="login-but-box">
                            <button onClick={login} className="login-but">
                                로그인
                            </button>
                            <button onClick={closeModal} className="login-but">
                                닫기
                            </button>
                        </div>
                    </footer>
                </S.LoginWrap>
            )}
        </>
    );
};

export default Shoplist;
