import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
const PaymentDone = ({
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
    paymentlist,
    require,
}) => {
    console.log(paymentlist);
    return (
        <>
            <S.PaymentDoneWrap>
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
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <div>
                            <p>주문이 완료되었습니다.</p>
                            <p>이용해주셔서 감사합니다.</p>
                        </div>
                        <div>
                            <h4>주문내역 확인</h4>

                            <p>총 결제금액</p>
                            <p>{localStorage.getItem("allPrice")}</p>
                            <p>주문 목록</p>
                            <p>
                                {paymentlist.map((payment_list) => {
                                    return (
                                        <span>
                                            {payment_list.name}{" "}
                                            {payment_list.count}개{" "}
                                        </span>
                                    );
                                })}
                            </p>
                            <p>주문번호</p>
                            <p>{localStorage.getItem("orderId")}</p>
                        </div>
                        <footer>
                            <p>다른 식당도 예약하고 싶으세요?</p>
                            <Link to="/shoplist">
                                <button>다른 식당 예약하기</button>
                            </Link>
                            <button onClick={require}>환불하기</button>
                        </footer>
                    </body>
                </div>
            </S.PaymentDoneWrap>

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

export default PaymentDone;
