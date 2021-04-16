import React, { useEffect, useState } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import yangtimjang from "../Shoplist/img/yangtimjang.png";
import { RenderAfterNavermapsLoaded, NaverMap, Marker } from "react-naver-maps";

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
    handleDeleteMenu,
    lat,
    lag,
    mapModal,
    openhandleModal,
    closehandleModal,
    order,
}) => {
    var x = (lat *= 1);
    var y = (lag *= 1);

    let HOUSE_BASE_URL = "http://122.202.45.37:8088/";
    let SCHOOL_BASE_URL = "http://192.168.1.17:8088/";
    let AWS_BASE_URL = "http://3.34.55.186:8088/";
    let SCHOOL_BASE_URL2 = "http://192.168.0.24:8088/";

    const [allPrice, setAllPrice] = useState();
    useEffect(() => {
        let a = jmMenu.reduce((prev, curr) => {
            console.log(prev, curr);
            return prev + curr.price * curr.count;
        }, 0);

        setAllPrice(a);
    }, [jmMenu]);
    localStorage.setItem("allPrice", allPrice);
    return (
        <>
            <S.shopcontentWrap>
                <div class="App">
                    <div class="black-nav">
                        <div class="left-nav">
                            <Link to="/shoplist" class="jmmjlink">
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
                            </Link>
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
                            <div class="shopcon">
                                <div class="shopcon_2">
                                    <div class="shop_img">
                                        <img
                                            class="shop_img2"
                                            src={
                                                AWS_BASE_URL + shopIntro.imgPath
                                            }
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
                                                <button
                                                    onClick={openhandleModal}
                                                    class="showmapbtn"
                                                >
                                                    자세히보기
                                                </button>
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
                                        {menu.map((menukind) => {
                                            return (
                                                <tr>
                                                    <td class="menu-item">
                                                        <button
                                                            class="menu-item-button"
                                                            onClick={() =>
                                                                handleMenu(
                                                                    menukind.name,
                                                                    menukind.price
                                                                )
                                                            }
                                                        >
                                                            <img
                                                                src={
                                                                    AWS_BASE_URL +
                                                                    menukind.img
                                                                }
                                                                width="100px"
                                                            ></img>
                                                        </button>
                                                    </td>

                                                    <td class="menu-item">
                                                        {menukind.name}
                                                    </td>

                                                    <td class="menu-item">
                                                        {menukind.intro}
                                                    </td>
                                                    <td class="menu-item">
                                                        {menukind.price}
                                                    </td>
                                                </tr>
                                            );
                                        })}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="showjm">
                            <div class="jmlist">주문 목록</div>
                            <br></br>
                            <div class="jmcontent">
                                {jmMenu.map((jmlist) => {
                                    return (
                                        <div>
                                            <div class="jmList_all">
                                                <div class="jmList_1">
                                                    {jmlist.name}
                                                </div>
                                                <div class="jmList_3">
                                                    {jmlist.count}개
                                                </div>
                                                <div class="jmList_2">
                                                    {jmlist.price}원
                                                </div>

                                                <button
                                                    class="jmList_4"
                                                    onClick={() =>
                                                        handleDeleteMenu(
                                                            jmlist.name
                                                        )
                                                    }
                                                >
                                                    삭제
                                                </button>
                                                <br></br>
                                            </div>
                                        </div>
                                    );
                                })}
                            </div>
                            <div class="jmallprice">
                                <span>합계</span>
                                <div class="jmprice8">{allPrice}원</div>
                            </div>

                            <button class="gojm" onClick={order}>
                                주문하기
                            </button>
                        </div>
                    </body>
                    {mapModal && (
                        <button
                            onClick={closehandleModal}
                            class="closehandleModal"
                        >
                            닫기
                        </button>
                    )}
                    {mapModal && (
                        <RenderAfterNavermapsLoaded
                            ncpClientId={"44kkvl80g1"} // 자신의 네이버 계정에서 발급받은 Client ID
                            error={<p>Maps Load Error</p>}
                            loading={<p>Maps Loading...</p>}
                        >
                            <NaverMapAPI />
                        </RenderAfterNavermapsLoaded>
                    )}

                    <footer>
                        <h4>
                            copyright 2021 yeongJin university capston WDA team
                            4.
                        </h4>
                    </footer>
                    <div></div>
                </div>
            </S.shopcontentWrap>

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
    // 네이버지도 api

    function NaverMapAPI() {
        const navermaps = window.naver.maps;
        return (
            <NaverMap
                mapDivId={"maps-getting-started-uncontrolled"} // default: react-naver-map
                style={{
                    borderRadius: "7px",
                    border: "1px solid #555",
                    position: "absolute",
                    top: "30%",
                    left: "50%",
                    width: "29%", // 네이버지도 가로 길이
                    height: "410px", // 네이버지도 세로 길이
                }}
                defaultCenter={{
                    lat: x,
                    lng: y,
                }} // 지도 초기 위치
                defaultZoom={18} // 지도 초기 확대 배율
            >
                <Marker
                    key={1}
                    position={new navermaps.LatLng(x, y)}
                    animation={2}
                    onClick={() => {
                        alert(
                            shopIntro.name +
                                "식당에서 전해요~ " +
                                "\n" +
                                shopIntro.intro
                        );
                    }}
                />
            </NaverMap>
        );
    }
};

export default Shopcontent;
