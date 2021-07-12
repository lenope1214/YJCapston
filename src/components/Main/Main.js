import React, { useState, useEffect } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import { debounce, throttle } from "lodash";
import steak from "./img/backvideo.mp4";
import dia from "./img/dia.png";
import sample from "./img/sample.PNG";
import sampleback1 from "./img/sample1.png";
import icons from "./img/icons.PNG";
import item1 from "./img/C_QRITEM.png";
import item2 from "./img/C_RESITEM.png";
import item3 from "./img/C_CHATITEM.png";
import $ from "jquery";
import GoogleLogin from 'react-google-login';
// npm i react-google-login 
// yarn add react-google-login
window.$ = $;
const clientId = "1048529450072-biomd2b5ak4h1l94m0sqvmgvcc7eilf7.apps.googleusercontent.com";
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
    name,
    role,
    onSocial
}) => {
    let delay = false;
    let currentPage = 1;
    let pageCount = $(".section").length;

    const onSuccess = async(response) => {
    	console.log(response);
    
        const { googleId, profileObj : { email, name } } = response;
        
        await onSocial({
            socialId : googleId,
            socialType : 'google',
            email,
            nickname : name
        });
    }
    sessionStorage.setItem("accesstoken", "googleId");
    const onFailure = (error) => {
        console.log(error);
    }
    // function getWindowDimensions() {
    //     const { innerWidth: width, innerHeight: height } = window;
    //     return {
    //         width,
    //         height,
    //     };
    // }
    // function useWindowDimensions() {
    //     const [windowDimensions, setWindowDimensions] = useState(
    //         getWindowDimensions()
    //     );

    //     useEffect(() => {
    //         function handleResize() {
    //             setWindowDimensions(getWindowDimensions());
    //         }

    //         window.addEventListener("resize", handleResize);
    //         return () => window.removeEventListener("resize", handleResize);
    //     }, []);

    //     return windowDimensions;
    // }

    // const { height, width } = useWindowDimensions();

    // $(document).ready(function() {

    //     var swipe = document.getElementsByTagName('.section');

    //     $(document).on('mousewheel DOMMouseScroll', function(event) {
    //           event.preventDefault();
    //           if (delay) return;
    //           delay = true;
    //           setTimeout(function() { delay = false }, 100)

    //           var wd = event.originalEvent.wheelDelta || -event.originalEvent.detail;
    //           console.log(wd);

    //           if (wd < 0) {
    //               if (currentPage < pageCount) {
    //                   currentPage++;
    //                   console.log(currentPage);
    //               }
    //           } else {
    //               if (1 < currentPage) {
    //                   currentPage--;
    //                   console.log(currentPage);
    //               }
    //           }
    //           if (currentPage == 1) {
    //               document.scrollTo({top:0, left: 0, behavior: "smooth"})
    //               $('.scroll-down').removeClass('none');
    //               $('.title').removeClass('black');
    //               $('.link').removeClass('black');
    //               $('.log-but').removeClass('black');
    //               $('#tag1').removeClass('black2');
    //             $('#tag2').removeClass('black2');
    //             $('#tag3').removeClass('black2');
    //           } else if (currentPage == 2) {
    //               window.scrollTo({top: height, left: 0, behavior: "smooth"})
    //               $('.scroll-down').removeClass('none');
    //               $('.title').removeClass('black');
    //               $('.link').removeClass('black');
    //               $('.log-but').removeClass('black');
    //               $('#tag1').removeClass('black2');
    //             $('#tag2').removeClass('black2');
    //             $('#tag3').removeClass('black2');
    //           } else if (currentPage == 3) {
    //             window.scrollTo({top: height*2.1, left: 0, behavior: "smooth"})
    //             $('.scroll-down').addClass('none');
    //             $('.title').addClass('black');
    //             $('.link').addClass('black');
    //             $('.log-but').addClass('black');
    //             $('#tag1').addClass('black2');
    //             $('#tag2').addClass('black2');
    //             $('#tag3').addClass('black2');
    //         }

    //           $('#tag' + currentPage).addClass('active');
    //           for (var i = 1; i <= pageCount; i++) {
    //               if (i != currentPage) {
    //                   $('#tag' + i).removeClass('active');
    //               }
    //           }
    //       });
    //   });

    return (
        <>
            <S.MainWrap>
                <div className="App">
                    <div className="header">
                        <div className="title">
                            <span className="title1">주문</span>
                            <span className="title2">의</span>
                            <span className="title3">민족</span>
                        </div>
                        <span className="link">사업자</span>
                        <span className="link">사용자</span>
                        {/* <span className="link">어플다운</span> */}
                        <div className="log-box">
                            {isLogin ? (
                                <>
                                    <Link to="/mypage">
                                       <button
                                            className="log-but p"
                                            onClick={() => {
                                                window.scrollTo(0, 0);
                                            }}
                                        >
                                            {name}
                                        </button>
                                    </Link>
                                    <button
                                        className="log-but"
                                        onClick={logout}
                                    >
                                        LOG OUT
                                    </button>
                                </>
                            ) : (
                                <>
                                    <Link to="/register">
                                        <button
                                            className="log-but"
                                            onClick={() => {
                                                window.scrollTo(0, 0);
                                            }}
                                        >
                                            JOIN
                                        </button>
                                    </Link>
                                    <button
                                        className="log-but"
                                        onClick={openModal}
                                    >
                                        LOG IN
                                    </button>
                                </>
                            )}
                        </div>
                    </div>
                    {/* <ul class="ul">
                        <li id="tag1" class="active"></li>
                        <li id="tag2"></li>
                        <li id="tag3"></li>
                    </ul> */}
                    <div className="scroll-down">∨</div>
                    <div className="section" id="sec1">
                        <div className="adimg">
                            <video autoPlay muted loop className="backvideo">
                                <source src={steak} type="video/mp4"></source>
                            </video>
                            <div className="videotext">
                                <p className="vt0">THE</p>
                                <p className="vt1">
                                    PEOPLE<span className="of"> OF</span>
                                    &nbsp;ORDER
                                </p>
                                <img
                                    src={dia}
                                    width="240px"
                                    height="80px"
                                    className="dia"
                                ></img>
                                <div className="but-box">
                                    {role=="ROLE_OWNER" ? (
                                    <>
                                    <Link to="/myshop">
                                        <button className="link-button1">
                                            식당관리
                                        </button>
                                    </Link>
                                    </>
                                    ) : (
                                        <>
                                        <Link to="/shoplist">
                                        <button className="link-button1">
                                            예약하기
                                        </button>
                                    </Link>
                                        </>
                                    )}
                                    
                                    <br />
                                    
                                    <br />
                                    {/* <button className="link-button3">
                                        어플다운
                                    </button> */}
                                    <br />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="section" id="sec2">
                        <div className="secondbox">
                            <img src={sampleback1} className="secondimg" />

                            <div className="second-text">
                                <p className="second-title">SERVICE 서비스</p>
                                <hr width="70px" className="hr" />
                                <div className="item-1">
                                    <img
                                        src={item1}
                                        width="200px"
                                        height="200px"
                                    />
                                    <p className="itemtitle">QR 주문</p>
                                    <p className="itemdesc">
                                        직원 호출 없이 <br />
                                        테이블에 앉아서 <br />
                                        QR 코드로 주문
                                    </p>
                                </div>
                                <div className="item-2">
                                    <img
                                        src={item2}
                                        width="200px"
                                        height="200px"
                                    />
                                    <p className="itemtitle">예약 기능</p>
                                    <p className="itemdesc">
                                        식당에 가기 전, <br />
                                        컴퓨터나 핸드폰으로 <br />
                                        예약해서 시간 아끼기!
                                    </p>
                                </div>
                                <div className="item-3">
                                    <img
                                        src={item3}
                                        width="200px"
                                        height="200px"
                                    />
                                    <p className="itemtitle">챗봇 상담</p>
                                    <p className="itemdesc">
                                        식당이 바쁜 때에 <br />
                                        챗봇 상담으로 <br />
                                        질문 가능!
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="section" id="sec3">
                        <div className="preparing">
                            <div className="left">
                                <div className="left-1">
                                    <span>주만지 </span>
                                    <span className="left11">영진전문대</span>
                                    <br></br>
                                    <span>WD-A </span>
                                    <span className="left22">JUMANJI</span>
                                </div>
                                <div className="left-2">
                                    <img src={icons} />
                                </div>
                                <div className="left-3">
                                    <br />
                                    <br />
                                    (주)주만지
                                    <br />
                                    영진전문대학교 3-WD-A반
                                    <br />
                                    <br />
                                    사업자번호: 000-00-00000
                                    <br />
                                    <br />
                                    캡스톤프로젝트
                                    <br />
                                    전화번호 : 010-0000-0000
                                    <br />
                                    메일 : jumanji@naver.com
                                    <br />
                                </div>
                            </div>
                            <div className="right">
                                <Link to="/register">
                                    <button className="button-1">
                                        회원가입하러 가기
                                    </button>
                                </Link>
                                <br />
                                <button
                                    className="button-1"
                                    onClick={openModal}
                                >
                                    로그인하러 가기
                                </button>
                                <br />
                                {/* <button className="button-1">
                                    어플다운하러 가기
                                </button> */}
                            </div>
                        </div>
                    </div>
                </div>
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
                            <GoogleLogin
                                clientId={clientId}
                                responseType={"id_token"}
                                onSuccess={onSuccess}
                                onFailure={onFailure}
                            />
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