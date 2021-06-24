import React from "react";

import * as S from "./style";
import { Link } from "react-router-dom";
import DaumPostcode from "react-daum-postcode";
import topimg from "../Main/img/QRcode2.png";

const Register = ({
    id,
    handleId,
    pw,
    handlePw,
    username,
    handleName,
    phone1,
    handlePhone1,
    phone2,
    handlePhone2,
    phone3,
    handlePhone3,
    register,
    owner,
    handleOwner,
    email,
    handleEmail,
    birthday,
    handleBirthday,
    address,
    address1,
    handleAddress,
    handleAddress1,
    search,
    modal,
    openModal,
    closeModal,
    handleKeyword,
    Keyword,
    IdCheck,
    showLocation,
    roadAddr,
    handleRoadAddr,
    handleComplete,
    phoneauth,
}) => {
    const postCodeStyle = {
        display: "block",
        position: "fixed",
        top: "50%",
        width: "30%",
        height: "73%",
        padding: "7px",
        left: "50%",
        transform: "translate(-50%, -50%)",
    };

    return (
        <>
            <S.RegisterWrap>
                <header>
                    <Link to="/" class="movemainpage">
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
                    </Link>
                </header>
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>회원가입 페이지</p>
                </div>
                <body>
                    <div className="total-body">
                        <div className="label">아이디</div>
                        <input
                            type="text"
                            id="userId"
                            placeholder="아이디를 입력해주세요."
                            onChange={handleId}
                            value={id}
                            className="input-box"
                        ></input>
                        <button onClick={IdCheck} className="button1">
                            V
                        </button>
                        <div className="label">비밀번호</div>
                        <input
                            type="password"
                            id="password"
                            placeholder="비밀번호를 입력해주세요."
                            onChange={handlePw}
                            value={pw}
                            className="input-box"
                        />
                        <div className="label">이름</div>
                        <input
                            type="text"
                            id="username"
                            placeholder="이름을 입력해주세요."
                            onChange={handleName}
                            value={username}
                            className="input-box"
                        />

                        <div className="label">전화번호</div>
                        <div>
                            <select
                                id="txtMobile1"
                                onChange={handlePhone1}
                                value={phone1}
                                className="phone-box1"
                            >
                                <option value="">::선택::</option>
                                <option value="010">010</option>
                                <option value="011">011</option>
                                <option value="016">016</option>
                                <option value="017">017</option>
                                <option value="019">019</option>
                            </select>
                            <span>-</span>
                            <input
                                className="phone-box"
                                type="text"
                                id="txtMobile2"
                                size="4"
                                onkeypress="onlyNumber();"
                                placeholder="ex)1234"
                                onChange={handlePhone2}
                                value={phone2}
                            />
                            <span>-</span>
                            <input
                                className="phone-box"
                                type="text"
                                id="txtMobile3"
                                size="4"
                                placeholder="ex)1234"
                                onChange={handlePhone3}
                                value={phone3}
                            />
                            {/* <button onClick={phoneauth}>번호인증</button> */}
                        </div>
                        <div className="label">이메일</div>
                        <input
                            type="email"
                            id="email"
                            placeholder="이메일을 입력해주세요."
                            onChange={handleEmail}
                            value={email}
                            className="input-box"
                        />
                        <div className="checkbox-box">
                            <div className="label">
                                사업자체크<span></span>
                                <input
                                    type="checkbox"
                                    id="owner"
                                    onChange={handleOwner}
                                    value={owner}
                                    className="check-box"
                                />
                            </div>
                        </div>
                        <div className="label">생년월일</div>
                        <input
                            type="date"
                            id="birthday"
                            name="birthday"
                            value="2000-01-01"
                            onChange={handleBirthday}
                            value={birthday}
                            className="input-box"
                        />
                        <div className="address-box">
                            <div className="label">주소</div>
                            <div>
                                <input
                                    type="address"
                                    id="address"
                                    placeholder={roadAddr}
                                    onChange={handleAddress}
                                    value={roadAddr}
                                    className="input-box"
                                    disabled
                                />
                                <button onClick={openModal} className="button2">
                                    검색
                                </button>
                            </div>
                            <input
                                type="address1"
                                id="address1"
                                placeholder="상세 주소를 입력하세요"
                                onChange={handleAddress1}
                                value={address1}
                                className="input-box"
                            />
                        </div>
                        <div className="button-box">
                            <button onClick={register} className="button3">
                                회원가입
                            </button>
                            <Link
                                to="/"
                                onClick={() => {
                                    window.scrollTo(0, 0);
                                }}
                            >
                                <button className="button4">취소</button>
                            </Link>
                        </div>
                    </div>
                </body>

                <footer></footer>
                {modal && (
                    <button class="Modalclosebutton" onClick={closeModal}>
                        닫기
                    </button>
                )}
            </S.RegisterWrap>
            {modal && (
                <DaumPostcode
                    onComplete={handleComplete}
                    style={postCodeStyle}
                    height={700}
                />
            )}
        </>
    );
};

export default Register;
