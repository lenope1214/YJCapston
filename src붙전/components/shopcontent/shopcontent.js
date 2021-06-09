import React, { useEffect, useState } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import useritem from "./img/useritem.PNG";
import { RenderAfterNavermapsLoaded, NaverMap, Marker } from "react-naver-maps";
import { isNull } from "lodash";
// import e from "cors";

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
    reviewList,
    Mark,
    MarkDelete,
    removeReview,
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

    let a = null;
    let imgbox = "";
    let b = null;
    let rspos_y = null;
    let rspos_n = null;
    let isopen_y = null;
    let isopen_n = null;

    if (shopIntro.marked == "N") {
        b = "🖤";
    } else {
        b = "💗";
    }

    if (shopIntro.isRsPos == "N"){
        rspos_n = "불가능";
    } else {
        rspos_y = "가능";
    }
    if (shopIntro.isOpen == "N"){
        isopen_n="미오픈";
    }else {
        isopen_y = "오픈";
    }

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
                                    {/* ☝ */}
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
                                            <button
                                                className="mark"
                                                onClick={Mark}
                                            >
                                                {b}
                                            </button>
                                        </div>

                                        <br />
                                        <div>
                                            <div class="shopother3">
                                                {shopIntro.intro}
                                            </div>
                                        </div>
                                        <div>
                                            <div class="shopother4">
                                                {/* 예약 여부 : {shopIntro.isRsPos} */}
                                                예약 여부 : <span class="rspos_y">{rspos_y}</span><span class="rspos_n">{rspos_n}</span>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="shopother5">
                                                {/* 오픈 여부 : {shopIntro.isOpen} */}
                                                오픈 여부 : <span class="isopen_y">{isopen_y}</span><span class="isopen_n">{isopen_n}</span>
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
                                            <div>
                                                <button
                                                    onClick={() =>
                                                        window.open(
                                                            `http://3.34.55.186:3000/chat/${shopIntro.shopId}`,
                                                            "_blank",
                                                            "location = no, toolbars= no, status= no, width = 400, height = 500 , scrollbars = no"
                                                        )
                                                    }
                                                >
                                                    채팅방가기
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

                                                    <td class="menu-item1">
                                                        {menukind.name}
                                                    </td>

                                                    <td class="menu-item">
                                                        {menukind.intro}
                                                    </td>
                                                    <td class="menu-item">
                                                        {menukind.price}원
                                                    </td>
                                                </tr>
                                            );
                                        })}
                                    </tbody>
                                </table>
                            </div>
                            <div className="review-box">
                                <div className="review-title">
                                    리뷰 리스트 ({reviewList.length})
                                </div>
                                {!reviewList.length && (
                                    <td className="none-review">
                                        등록된 리뷰가 없습니다.
                                    </td>
                                )}
                                {!!reviewList.length &&
                                    reviewList.map((review) => {
                                        if (review.score == "5") {
                                            a = "★★★★★";
                                        } else if (review.score == "4") {
                                            a = "★★★★☆";
                                        } else if (review.score == "3") {
                                            a = "★★★☆☆";
                                        } else if (review.score == "2") {
                                            a = "★★☆☆☆";
                                        } else if (review.score == "1") {
                                            a = "★☆☆☆☆";
                                        }
                                        if (review.imgUrl == null) {
                                            imgbox = "-none";
                                        } else imgbox = "";

                                        return (
                                            <div className="review-item">
                                                <tr className="review-1">
                                                    <td className="review-1">
                                                        <span>
                                                            <img
                                                                src={useritem}
                                                                className="useritem"
                                                            />
                                                        </span>
                                                        <span className="username">
                                                            {review.userId}
                                                        </span>
                                                        <span>
                                                            <button
                                                                onClick={() =>
                                                                    removeReview(
                                                                        `${review.reviewId}`
                                                                    )
                                                                }
                                                                className="delete"
                                                            >
                                                                삭제
                                                            </button>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td className="review-2">
                                                        <span className="red">
                                                            {a}
                                                        </span>
                                                        <span>
                                                            {review.score}
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td className="review-3">
                                                        {review.regdate}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td className="review-4">
                                                        <img
                                                            src={`http://3.34.55.186:8088/${review.imgUrl}`}
                                                            width="150"
                                                            height="150"
                                                            className={
                                                                "imgbox" +
                                                                imgbox
                                                            }
                                                        ></img>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td className="review-5">
                                                        {review.content}
                                                    </td>
                                                </tr>
                                            </div>
                                        );
                                    })}
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
