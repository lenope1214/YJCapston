import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";

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
}) => {
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
                        <div>예약결제</div>
                        <div>
                            <div>예약자 정보 </div>
                            <div>이름: {jmuserinfo.name}</div>
                            <div>이메일: {jmuserinfo.email}</div>
                            <div>휴대폰 번호: : {jmuserinfo.phone}</div>
                        </div>
                        <div>
                            <div>식당정보</div>
                            <div>식당이름: {jmshopinfo.name} </div>
                            <div>
                                식당주소: {jmshopinfo.address}{" "}
                                {jmshopinfo.addressDetail}
                            </div>
                            <div>식당번호: {jmshopinfo.id}</div>
                            <div>식당 예약 시 요청사항</div>
                        </div>
                        <div>
                            <div>주문 번호: {jmorderid}</div>
                            <div>
                                주문 목록:{" "}
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
                            <div>결제 정보</div>
                            <div>할인 쿠폰: 없음</div>
                            <div>총결제 금액: {jmallprice}</div>
                        </div>
                    </body>
                    <footer>
                        <button onClick={onClickPayment}>결제하기</button>
                        <button>취소</button>
                    </footer>
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
