import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import topimg from "../Main/img/QRcode2.png";
import moment from 'moment';
import 'moment/locale/ko';

const ShopOrder = ({
    isLogin,
    logout,
    openModal,
    modal,
    closeModal,
    handleId,
    handlePw,
    id,
    pw,
    login,
    jmshopinfo,
    jmuserinfo,
    jmorderid,
    jmorderlist,
    jmallprice,
    onClickPayment,
    goBack,
    request,
    handleRequest,
    handlePeople,
    people,
    handlepoint,
    pointcheck,
    lastprcie,
    publish,
    connect,
    rsYear,
    handlersYear,
    rsMonth,
    handlersMonth,
    rsDate,
    handlersDate,
    rsHour,
    handlersHour,
    rsMinute,
    handlersMinute,
    // orderdate,
    // ordertime,
    // handleOrdertime,
    // handleOrderdate
}) => {

    const nowYear = moment().format('YYYY');
    const nextYear = moment().add(1,'year').format('YYYY');
    

    return (
        <>
            <S.shopOrder>
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
                        <div>
                            <img src={topimg} className="topimg" />
                        </div>
                        <div className="topimg-text">
                            <p>주문 확인</p>
                        </div>
                        <div className="total-body">
                            <div className="partition">
                                <div className="box-title">예약 정보</div>
                                <div className="subtitle">인원 수</div>
                                <div>
                                    <select
                                        className="input-box"
                                        onChange={handlePeople}
                                        value={people}
                                    >
                                        <option value="1">1명</option>
                                        <option value="2">2명</option>
                                        <option value="3">3명</option>
                                        <option value="4">4명</option>
                                        <option value="5">5명</option>
                                        <option value="6">6명</option>
                                        <option value="7">7명</option>
                                        <option value="8">8명</option>
                                    </select>
                                </div>
                                <div className="subtitle">예약 시간</div>
                                <div>
                                    <select
                                        className="input-box1"
                                        value={rsYear}
                                        onChange={handlersYear}
                                    >
                                        <option value="">년도</option>
                                        
                                        <option value="2021">{nowYear}년</option>
                                        <option vlaue="2022">{nextYear}년</option>
                                    </select>

                                    <select
                                    className="input-box2"
                                        value={rsMonth}
                                        onChange={handlersMonth}
                                    >
                                        <option value="">월</option>
                                        <option value="1">1월</option>
                                        <option value="2">2월</option>
                                        <option value="3">3월</option>
                                        <option value="4">4월</option>
                                        <option value="5">5월</option>
                                        <option value="6">6월</option>
                                        <option value="7">7월</option>
                                        <option value="8">8월</option>
                                        <option value="9">9월</option>
                                        <option value="10">10월</option>
                                        <option value="11">11월</option>
                                        <option value="12">12월</option>
                                    </select>
                                    
                                    <select
                                    className="input-box2"
                                        value={rsDate}
                                        onChange={handlersDate}
                                    >
                                        <option value="">일</option>
                                        <option value="1">1일</option>
                                        <option value="2">2일</option>
                                        <option value="3">3일</option>
                                        <option value="4">4일</option>
                                        <option value="5">5일</option>
                                        <option value="6">6일</option>
                                        <option value="7">7일</option>
                                        <option value="8">8일</option>
                                        <option value="9">9일</option>
                                        <option value="10">10일</option>
                                        <option value="11">11일</option>
                                        <option value="12">12일</option>
                                        <option value="13">13일</option>
                                        <option value="14">14일</option>
                                        <option value="15">15일</option>
                                        <option value="16">16일</option>
                                        <option value="17">17일</option>
                                        <option value="18">18일</option>
                                        <option value="19">19일</option>
                                        <option value="20">20일</option>
                                        <option value="21">21일</option>
                                        <option value="22">22일</option>
                                        <option value="23">23일</option>
                                        <option value="24">24일</option>
                                        <option value="25">25일</option>
                                        <option value="26">26일</option>
                                        <option value="27">27일</option>
                                        <option value="28">28일</option>
                                        <option value="29">29일</option>
                                        <option value="30">30일</option>
                                        <option value="31">31일</option>
                                    </select>
                                    <br/>
                                    <select
                                    className="input-box1"
                                        value={rsHour}
                                        onChange={handlersHour}
                                    >
                                        <option value="">시</option>
                                        <option value="1">1시</option>
                                        <option value="2">2시</option>
                                        <option value="3">3시</option>
                                        <option value="4">4시</option>
                                        <option value="5">5시</option>
                                        <option value="6">6시</option>
                                        <option value="7">7시</option>
                                        <option value="8">8시</option>
                                        <option value="9">9시</option>
                                        <option value="10">10시</option>
                                        <option value="11">11시</option>
                                        <option value="12">12시</option>
                                        <option value="13">13시</option>
                                        <option value="14">14시</option>
                                        <option value="15">15시</option>
                                        <option value="16">16시</option>
                                        <option value="17">17시</option>
                                        <option value="18">18시</option>
                                        <option value="19">19시</option>
                                        <option value="20">20시</option>
                                        <option value="21">21시</option>
                                        <option value="22">22시</option>
                                        <option value="23">23시</option>
                                        <option value="24">24시</option>
                                    </select>
                                    
                                    <select
                                    className="input-box2"
                                        value={rsMinute}
                                        onChange={handlersMinute}    
                                    >
                                        <option value="">분</option>
                                        <option value="00">0분</option>
                                        <option value="30">30분</option>
                                    </select>
                                </div>
                                
                                <div className="subtitle">요청 사항</div>
                                <input
                                    type="text"
                                    className="input-box"
                                    value={request}
                                    onChange={handleRequest}
                                    placeholder="요청사항을 입력해주세요"
                                />
                            </div>

                            <div className="partition">
                                <div className="box-title">예약자 정보 </div>
                                <div className="subtitle">이름</div>
                                <div>
                                    <input
                                        value={jmuserinfo.name}
                                        className="input-box"
                                    ></input>
                                </div>
                                <div className="subtitle">이메일</div>
                                <div>
                                    <input
                                        value={jmuserinfo.email}
                                        className="input-box"
                                    ></input>
                                </div>
                                <div className="subtitle">휴대폰 번호</div>
                                <div>
                                    <input
                                        value={jmuserinfo.phone}
                                        className="input-box"
                                    />
                                </div>
                            </div>
                            <div className="partition">
                                <div className="box-title">식당정보</div>
                                <div className="subtitle">식당이름</div>
                                <div>
                                    <input
                                        value={jmshopinfo.name}
                                        className="input-box"
                                    />
                                </div>
                                <div className="subtitle">식당주소</div>
                                <div className="input-box">
                                    {jmshopinfo.address}{" "}
                                    {jmshopinfo.addressDetail}
                                </div>
                            </div>
                            <div className="partition">
                                <div className="box-title">주문정보</div>
                                <div className="subtitle">주문 번호</div>
                                <div>
                                    <input
                                        value={jmorderid}
                                        className="input-box"
                                    />
                                </div>
                                <div className="subtitle">주문 목록</div>
                                <div className="input-box">
                                    {jmorderlist.map((jmorder_list) => {
                                        return (
                                            <span>
                                                {jmorder_list.name}{" "}
                                                {jmorder_list.count}개{" "}
                                            </span>
                                        );
                                    })}
                                </div>
                            </div>
                            <div>
                                <div className="box-title">결제 정보</div>
                                <div className="subtitle">할인 쿠폰</div>
                                <input value="없음" className="input-box" />
                            </div>
                            <div className="subtitle">현재 포인트</div>
                            <input
                                value={jmuserinfo.point - pointcheck}
                                className="input-box"
                            ></input>
                            <br />
                            <input
                                className="input-box"
                                placeholder="사용할 포인트를 입력해주세요"
                                value={pointcheck}
                                onChange={handlepoint}
                            />

                            <div>
                                <div className="subtitle">총결제 금액</div>
                                <div>
                                    <input
                                        value={jmallprice - pointcheck}
                                        className="input-box"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="button-box">
                            <button
                                onClick={onClickPayment}
                                className="button1"
                            >
                                결제하기
                            </button>
                            <button className="button2" onClick={goBack}>
                                취소
                            </button>
                            {/* <button onClick={connect}> test1</button>
                            <button onClick={publish}>test2</button> */}
                        </div>
                    </body>
                </div>
            </S.shopOrder>

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

export default ShopOrder;
