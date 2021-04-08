import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import yangtimjang from "../Shoplist/img/yangtimjang.png";

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
    shopIntro,
    menu,
    handleMenu,
    jmMenu,
}) => {
    console.log(jmMenu);
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

                            <div class="shopcon">
                                <div class="shopcon_2">
                                    <div class="shop_img">
                                        <img
                                            class="shop_img2"
                                            src={yangtimjang}
                                        />
                                    </div>
                                    <div class="shopcon_1">
                                        <span>
                                            <span class="shopother1">
                                                {shopIntro.name}
                                            </span>
                                        </span>
                                        <br />
                                        <div>
                                            <div class="shopother2">
                                                {shopIntro.category}
                                            </div>
                                        </div>
                                        <br />
                                        <div>
                                            <div class="shopother3">
                                                {shopIntro.intro}
                                            </div>
                                        </div>
                                        <div>
                                            <div class="shopother4">
                                                예약 여부 : {shopIntro.isRsPos}
                                            </div>
                                        </div>
                                        <div>
                                            <div class="shopother5">
                                                오픈 여부 : {shopIntro.isOpen}
                                            </div>
                                        </div>

                                        <div>
                                            <div class="shopother6">
                                                오픈 시간 : {shopIntro.openTime}
                                            </div>
                                        </div>

                                        <div>
                                            <div class="shopother7">
                                                클로즈 시간 :{" "}
                                                {shopIntro.closeTime}
                                            </div>
                                        </div>
                                        <div>
                                            <div class="shopother8">
                                                주소 : {shopIntro.address}{" "}
                                                {shopIntro.addressDetail}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tablebody">
                                <table>
                                    <thead class="tablehead">
                                        <th>메뉴사진</th>
                                        <th>메뉴 명</th>
                                        <th>메뉴 정보</th>
                                        <th>메뉴 가격</th>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td>
                                                {menu.map((menukind) => {
                                                    let HOUSE_BASE_URL =
                                                        "http://122.202.45.37:8088/";
                                                    let SCHOOL_BASE_URL =
                                                        "http://192.168.1.17:8088/";
                                                    let AWS_BASE_URL =
                                                        "http://3.34.55.186:8088/";
                                                    let SCHOOL_BASE_URL2 =
                                                        "http://192.168.0.24:8088/";
                                                    return (
                                                        <div class="tablecontent1">
                                                            <img
                                                                src={
                                                                    SCHOOL_BASE_URL +
                                                                    menukind.img
                                                                }
                                                                width="100px"
                                                            ></img>
                                                        </div>
                                                    );
                                                })}
                                            </td>
                                            <td>
                                                <button onClick={handleMenu}>
                                                    {menu.map((menukind) => {
                                                        return (
                                                            <div class="tablecontent">
                                                                {menukind.id.substring(
                                                                    10,
                                                                    99
                                                                )}
                                                            </div>
                                                        );
                                                    })}
                                                </button>
                                            </td>

                                            <td>
                                                {menu.map((menukind) => {
                                                    return (
                                                        <div class="tablecontent">
                                                            {menukind.intro}
                                                        </div>
                                                    );
                                                })}
                                            </td>
                                            <td>
                                                {menu.map((menukind) => {
                                                    return (
                                                        <div class="tablecontent">
                                                            {menukind.price}
                                                        </div>
                                                    );
                                                })}
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="showjm">
                            <div class="jmlist">주문 목록</div>
                            <br></br>
                            <div class="jmcontent">
                                {menu.map((menukind) => {
                                    return (
                                        <div>
                                            <input placeholder={jmMenu} />
                                            <div>
                                                {menukind.id.substring(10)}
                                            </div>
                                            <div>{menukind.price}</div>
                                        </div>
                                    );
                                })}
                            </div>
                            <div class="jmallprice">
                                <span>합계</span>
                            </div>
                            <div class="jmprice"></div>

                            <button class="gojm">주문하기</button>
                        </div>
                    </body>
                    <footer>
                        {/* <h4>
                            copyright 2021 yeongJin university capston WDA team
                            4.
                        </h4> */}
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