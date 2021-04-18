import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import topimg from "../Main/img/QR코드사진2.png";

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
    goBack
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
                                    <select className="input-box">
                                        <option value="1명">1명</option>
                                        <option value="2명">2명</option>
                                        <option value="3명">3명</option>
                                        <option value="4명">4명</option>
                                        <option value="5명">5명</option>
                                        <option value="6명">6명</option>
                                        <option value="7명">7명</option>
                                        <option value="8명">8명</option>
                                    </select>
                                </div>
                                <div className="subtitle">예약 시간</div>
                                <div><input className="input-box" type="date"></input></div>
                                <div><input className="input-box" type="time"></input></div>
                            </div>
                            <div className="partition">
                                <div className="box-title">예약자 정보 </div>
                                <div className="subtitle">이름</div> 
                                <div><input value={jmuserinfo.name} className="input-box"></input></div>
                                <div className="subtitle">이메일</div>
                                <div><input value={jmuserinfo.email} className="input-box"></input></div>
                                <div className="subtitle">휴대폰 번호</div>
                                <div><input value={jmuserinfo.phone} className="input-box" /></div>
                            </div>
                            <div className="partition">
                                <div className="box-title">식당정보</div>
                                <div className="subtitle">식당이름</div>
                                <div><input value={jmshopinfo.name} className="input-box" /></div>
                                <div className="subtitle">식당주소</div>
                                    <div className="input-box">
                                    {jmshopinfo.address}{" "}
                                    {jmshopinfo.addressDetail}
                                </div>
                                <div className="subtitle">식당번호</div>
                                <div><input value={jmshopinfo.id} className="input-box" /></div>
                            </div>
                            <div className="partition">
                                <div className="subtitle">주문 번호</div>
                                <div><input value={jmorderid}  className="input-box"/></div>
                                <div className="subtitle">
                                    주문 목록</div>
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
                                <div><input value="없음"  className="input-box"/></div>
                                <div className="subtitle">총결제 금액</div>
                                <div><input value={jmallprice}  className="input-box"/></div>
                            </div>
                        </div>
                        <div class="button-box">
                            <button onClick={onClickPayment} className="button1">결제하기</button>
                            <button className="button2" onClick={goBack}>취소</button>
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
