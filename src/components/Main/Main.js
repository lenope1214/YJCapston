import React, { useState, useEffect } from "react";
import topimg from "./img/topimg.jpg";
import juminicon from "./img/주민아이콘.png";
import downimg from "./img/다운로드.png";
import peopleimg from "./img/주민사람.png";
import backimg2 from "./img/backimg.jpg";
import shopimg from "./img/매장아이콘.png";
import backimg3 from "./img/종이질감갈색화면.png";
import * as S from "./style";
import { Link } from "react-router-dom";
import { debounce } from "lodash";

const Main = ({
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
    function getWindowDimensions() {
        const { innerWidth: width, innerHeight: height } = window;
        return {
            width,
            height,
        };
    }

    function useWindowDimensions() {
        const [windowDimensions, setWindowDimensions] = useState(
            getWindowDimensions()
        );

        useEffect(() => {
            function handleResize() {
                setWindowDimensions(getWindowDimensions());
            }

            window.addEventListener("resize", handleResize);
            return () => window.removeEventListener("resize", handleResize);
        }, []);

        return windowDimensions;
    }

    const { height, width } = useWindowDimensions();
    const scrollTo1 = () => {
        window.scrollTo({ top: 0, left: 0, behavior: "smooth" });
    };
    const scrollTo2 = () => {
        window.scrollTo({ top: height, left: 0, behavior: "smooth" });
    };
    const scrollTo3 = () => {
        window.scrollTo({ top: height * 2, left: 0, behavior: "smooth" });
    };
    const scrollTo4 = () => {
        window.scrollTo({ top: height * 3, left: 0, behavior: "smooth" });
    };
    const useScrollDirection = () => {
        const [scrollDirection, setScrollDirection] = useState("");
        const [prevOffset, setPrevOffset] = useState(0);
        const toggleScrollDirection = debounce(() => {
            let scrollY = window.scrollY;
            if (scrollY === 0) {
                setScrollDirection("");
            }
            if (scrollY > prevOffset) {
                setScrollDirection("down");
            } else if (scrollY < prevOffset) {
                setScrollDirection("up");
            }
            setPrevOffset(scrollY);
        }, 20);
        useEffect(() => {
            window.addEventListener("scroll", toggleScrollDirection);
            return () => {
                window.removeEventListener("scroll", toggleScrollDirection);
            };
        });
        return scrollDirection;
    };

    const [but1, setbut1] = useState("");
    const [but2, setbut2] = useState("");
    const [but3, setbut3] = useState("");
    const [but4, setbut4] = useState("");
    const direction = useScrollDirection("");
    console.log(direction);

    useEffect(() => {
        if (0 <= window.scrollY && window.scrollY < height) {
            setbut1("-selected");
            setbut2("");
            setbut3("");
            setbut4("");
        } else if (height <= window.scrollY && window.scrollY < height * 2) {
            setbut1("");
            setbut2("-selected");
            setbut3("");
            setbut4("");
        } else if (
            height * 2 <= window.scrollY &&
            window.scrollY < height * 3
        ) {
            setbut1("");
            setbut2("");
            setbut3("-selected");
            setbut4("");
        } else if (window.scrollY === height * 3) {
            setbut1("");
            setbut2("");
            setbut3("");
            setbut4("-selected");
        }
        if (window.scrollY < height) {
            console.log(window.scrollY);
            if (direction === "down") {
                window.scrollTo({ top: height, left: 0, behavior: "smooth" });
                setbut1("");
                setbut2("-selected");
                setbut3("");
                setbut4("");
            } else if (direction === "up") {
                window.scrollTo({ top: 0, left: 0, behavior: "smooth" });
            }
        } else if (window.scrollY > height && window.scrollY < height * 2) {
            if (direction === "down") {
                window.scrollTo({
                    top: height * 2,
                    left: 0,
                    behavior: "smooth",
                });
                setbut1("");
                setbut2("");
                setbut3("-selected");
                setbut4("");
            } else if (direction === "up") {
                window.scrollTo({ top: height, left: 0, behavior: "smooth" });
            }
        } else if (window.scrollY > height * 2 && window.scrollY < height * 3) {
            if (direction === "down") {
                window.scrollTo({
                    top: height * 3,
                    left: 0,
                    behavior: "smooth",
                });
                setbut1("");
                setbut2("");
                setbut3("");
                setbut4("-selected");
            } else if (direction === "up") {
                window.scrollTo({
                    top: height * 2,
                    left: 0,
                    behavior: "smooth",
                });
            }
        }
    });

    return (
        <>
            <S.MainWrap>
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
                                style={{
                                    width: "300px",
                                    border: "0",
                                    borderRadius: "7px 0 0 7px",
                                    backgroundColor: "white",
                                    hegight: "20px",
                                }}
                            ></input>
                            <button
                                style={{
                                    border: "0",
                                    width: "10%",
                                    borderRadius: "0 7px 7px 0",
                                    backgroundColor: "white",
                                    color: "grey",
                                }}
                            >
                                검색
                            </button>
                        </div>
                        {isLogin ? (
                            <>
                                <div className="right-nav">
                                    <button
                                        className="right2-nav"
                                        onClick={logout}
                                    >
                                        LOG OUT
                                    </button>
                                    <Link to="/mypage">
                                        <button class="right2-nav">
                                            MY PAGE
                                        </button>
                                    </Link>
                                </div>
                                <div className="scroll-nav">
                                    <button
                                        className={"right1-nav" + but1}
                                        onClick={scrollTo1}
                                    >
                                        메인
                                        {console.log(but1)}
                                    </button>
                                    <button
                                        className={"right1-nav" + but2}
                                        onClick={scrollTo2}
                                    >
                                        어플
                                    </button>
                                    <button
                                        className={"right1-nav" + but3}
                                        onClick={scrollTo3}
                                    >
                                        사업자
                                    </button>
                                    <button
                                        className={"right1-nav" + but4}
                                        onClick={scrollTo4}
                                    >
                                        매장
                                    </button>
                                </div>
                            </>
                        ) : (
                            <>
                                <div className="right-nav">
                                    <button
                                        className="right2-nav"
                                        onClick={openModal}
                                    >
                                        LOG IN
                                    </button>
                                    <Link to="/register">
                                        <button class="right2-nav">
                                            {" "}
                                            JOIN
                                        </button>
                                    </Link>
                                </div>
                                <div className="scroll-nav">
                                    <button
                                        className={"right1-nav" + but1}
                                        onClick={scrollTo1}
                                    >
                                        메인
                                    </button>
                                    <button
                                        className={"right1-nav" + but2}
                                        onClick={scrollTo2}
                                    >
                                        어플
                                    </button>
                                    <button
                                        className={"right1-nav" + but3}
                                        onClick={scrollTo3}
                                    >
                                        사업자
                                    </button>
                                    <button
                                        className={"right1-nav" + but4}
                                        onClick={scrollTo4}
                                    >
                                        매장
                                    </button>
                                </div>
                            </>
                        )}
                    </div>

                    <div className="adimg">
                        <div className="topimg">
                            <img
                                src={topimg}
                                width="100%"
                                height="100%"
                                className="topimg-img"
                            />
                        </div>
                        <div className="toptext">
                            <span>테이블</span>
                            <span style={{ fontSize: "30px" }}>에서</span>
                            <br></br>
                            <span>주문</span>
                            <span style={{ fontSize: "30px" }}>하고</span>
                            <br></br>
                            <span>집</span>
                            <span style={{ fontSize: "30px" }}>에서</span>
                            <br></br>
                            <span>예약</span>
                            <span style={{ fontSize: "30px" }}>하자</span>
                        </div>
                    </div>

                    <div className="but-item1">
                        {/* <div className="backimg2">
                            <img src={backimg2} width="100%" height="700px" />
                        </div> */}
                        <div className="backimg3">
                            <img
                                src={backimg3}
                                width="100%"
                                height="100%"
                                className="backimg-img"
                            />
                        </div>
                        <div className="but-item1-text">
                            <p>주문의민족</p>
                            <p>모든 기능</p>
                            <p>이용하기!</p>
                        </div>
                        <div className="but-item1-icon">
                            <img src={juminicon} width="200px" height="200px" />
                            <div className="but-item1-but">
                                <button
                                    onClick={() => {
                                        console.log(1);
                                    }}
                                    style={{
                                        fontSize: "30px",
                                        fontFamily: "Wemakeprice-Bold",
                                        height: "130px",
                                        width: "210px",
                                        borderRadius: "60px",
                                        border: 0,
                                    }}
                                >
                                    주민앱<br></br>
                                    다운로드<br></br>
                                    <img src={downimg} width="40px" />
                                </button>
                            </div>
                        </div>
                        <div className="but-item1-text2">
                            <p>주민 앱 다운시 모든 기능</p>
                            <p>편리하게 사용 가능!</p>
                        </div>
                    </div>
                    <div className="but-item2">
                        <div className="backimg2">
                            <img
                                src={backimg2}
                                width="100%"
                                height="100%"
                                className="backimg-img"
                            />
                        </div>
                        <div className="but-item2-text">
                            <p>웹으로도</p>
                            <p>가능한</p>
                            <p>사업자 등록</p>
                            <p>매장 관리!</p>
                        </div>
                        <div className="but-item2-icon">
                            <img src={peopleimg} width="300px" height="300px" />
                            <div className="but-item2-but">
                                <Link to="/myshop">
                                    <button
                                        style={{
                                            fontSize: "30px",
                                            fontFamily: "Wemakeprice-Bold",
                                            height: "100px",
                                            width: "210px",
                                            borderRadius: "60px",
                                            border: 0,
                                        }}
                                    >
                                        사업자<br></br>
                                        페이지로<br></br>→
                                    </button>
                                </Link>
                            </div>
                        </div>
                        <div className="but-item2-text2">
                            <p>주민 앱 다운시 모든 기능</p>
                            <p>편리하게 사용 가능!</p>
                        </div>
                    </div>
                    <div className="but-item3">
                        <div className="backimg2">
                            <img
                                src={backimg3}
                                width="100%"
                                height="100%"
                                className="backimg-img"
                            />
                        </div>
                        <div className="but-item3-text">
                            <p>집에서</p>
                            <p>매장 둘러보고</p>
                            <p>예약하자!</p>
                        </div>
                        <div className="but-item3-icon">
                            <img src={shopimg} width="250px" height="250px" />
                            <div className="but-item3-but">
                                <Link to="/shoplist">
                                    <button
                                        className="but-item3-but"
                                        onclick="href='/shoplist'"
                                        style={{
                                            fontSize: "30px",
                                            fontFamily: "Wemakeprice-Bold",
                                            height: "130px",
                                            width: "230px",
                                            borderRadius: "60px",
                                            border: 0,
                                        }}
                                    >
                                        매장 둘러보고<br></br>
                                        예약하기<br></br>→
                                    </button>
                                </Link>
                            </div>
                        </div>
                        <div className="but-item3-text2">
                            <p>웹에서도 매장 둘러보고</p>
                            <p>예약 가능한 주문의 민족</p>
                        </div>
                    </div>
                </div>
                <footer>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                </footer>
            </S.MainWrap>

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

export default Main;
