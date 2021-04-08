import React from "react";

import * as S from "./style";
import { Link } from "react-router-dom";
import DaumPostcode from "react-daum-postcode";

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
}) => {
    const postCodeStyle = {
        display: "block",
        position: "fixed",
        top: "50%",
        width: "400px",
        height: "500px",
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
                <body>
                    <p>회원가입하기</p>
                    <span>아이디</span>
                    <input
                        type="text"
                        id="userId"
                        placeholder="아이디"
                        onChange={handleId}
                        value={id}
                    />
                    <button onClick={IdCheck}>중복확인</button>
                    <span>비밀번호</span>
                    <input
                        type="password"
                        id="password"
                        placeholder="비밀번호"
                        onChange={handlePw}
                        value={pw}
                    />
                    <span>이름</span>
                    <input
                        type="text"
                        id="username"
                        placeholder="이름"
                        onChange={handleName}
                        value={username}
                    />

                    <span class="named">전화번호</span>
                    <select
                        id="txtMobile1"
                        onChange={handlePhone1}
                        value={phone1}
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
                        class="phone"
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
                        class="phone"
                        type="text"
                        id="txtMobile3"
                        size="4"
                        placeholder="ex)1234"
                        onChange={handlePhone3}
                        value={phone3}
                    />
                    <span class="named">이메일</span>
                    <input
                        type="email"
                        id="email"
                        placeholder="이메일을 입력하세요"
                        onChange={handleEmail}
                        value={email}
                    />
                    <span class="check">사업자체크　</span>
                    <input
                        class="checkbox"
                        type="checkbox"
                        id="owner"
                        onChange={handleOwner}
                        value={owner}
                    />
                    <input
                        type="date"
                        id="birthday"
                        name="birthday"
                        value="2000-01-01"
                        onChange={handleBirthday}
                        value={birthday}
                    />
                    <input
                        type="address"
                        id="address"
                        placeholder={roadAddr}
                        onChange={handleAddress}
                        value={roadAddr}
                        disabled
                    />
                    <button onClick={openModal}>검색</button>
                    <input
                        type="address1"
                        id="address1"
                        placeholder="상세 주소를 입력하세요"
                        onChange={handleAddress1}
                        value={address1}
                    />
                </body>
                <footer>
                    <button onClick={register}>회원가입</button>
                </footer>
            </S.RegisterWrap>
            {modal && (
                <DaumPostcode
                    onComplete={handleComplete}
                    style={postCodeStyle}
                    height={700}
                />
                // <S.searchWrap>
                //     <header>
                //         <h1>주소를 검색해주세요</h1>
                //     </header>
                //     <body>
                //         <input
                //             type="text"
                //             placeholder="주소를 입력하세요"
                //             onChange={handleKeyword}
                //             value={Keyword}
                //         />
                //     </body>
                //     <button onClick={openModal}>검색</button>
                //     {showLocation.map((loca) => {
                //         return (
                //             <div>
                //                 <button
                //                     onClick={() =>
                //                         handleRoadAddr(loca.roadAddr)
                //                     }
                //                 >
                //                     {loca.roadAddr}
                //                 </button>
                //                 <br />
                //             </div>
                //         );
                //     })}
                //     {/* <input type="text" value={showLocation} /> */}
                //     <button>확인</button>
                //     <button onClick={closeModal}>닫기</button>
                // </S.searchWrap>
            )}
        </>
    );
};

export default Register;
