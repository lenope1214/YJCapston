import React, { useState } from "react";
import { Link } from "react-router-dom";

import * as S from "./style";

import DaumPostcode from "react-daum-postcode";
import topimg from "../Main/img/QR코드사진2.png";

const MyPage = ({
    Pw,
    handlePw,
    Mypage,
    user,
    modal,
    openmodal,
    handleComplete,
    roadAddr,
    handleAddress,
    handleAddressDetail,
    addressDetail,
    handleRoadAddr,
    jmlist,
    require,
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
        border: "1px",
    };

    return (
        <>
            <div class="abc">
                {modal && (
                    <DaumPostcode
                        onComplete={handleComplete}
                        style={postCodeStyle}
                        height={700}
                    />
                )}
            </div>
            <S.MypageWrap>
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
                    <p>마이 페이지</p>
                </div>
                <body>
                    <div className="total-body">
                        <div className="label">사용자ID</div>
                        <input
                            type="text"
                            id="id"
                            placeholder={user.id}
                            disabled
                            className="input-box"
                        />
                        <div className="label">비밀번호</div>
                        <input
                            type="password"
                            id="password"
                            placeholder="입력하세요"
                            onChange={handlePw}
                            value={Pw}
                            className="input-box"
                        />
                        <div className="label">이름</div>
                        <input
                            type="text"
                            id="name"
                            placeholder={user.name}
                            disabled
                            className="input-box"
                        />
                        <div className="label">이메일</div>
                        <input
                            type="text"
                            id="email"
                            placeholder="이메일 입력하세요"
                            value={user.email}
                            className="input-box"
                        />
                        <div className="label">전화번호</div>
                        <select
                            id="txtMobile1"
                            defaultValue="010"
                            className="phone-box1"
                        >
                            <option value="" disabled={true}>
                                ::선택::
                            </option>
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="019">019</option>
                        </select>
                        -
                        <input
                            className="phone-box"
                            type="text"
                            id="txtMobile2"
                            size="4"
                            onkeypress="onlyNumber();"
                            value={user.phone.substring(3, 7)}
                        />
                        -
                        <input
                            className="phone-box"
                            type="text"
                            id="txtMobile3"
                            size="4"
                            onkeypress="onlyNumber();"
                            value={user.phone.substring(7, 11)}
                        />
                        <div className="label">POINT</div>
                        <input
                            className="phone-box"
                            type="text"
                            id="txtMobile3"
                            size="4"
                            onkeypress="onlyNumber();"
                            value={user.point}
                            disabled
                            className="input-box"
                        />
                        <div className="label">주소</div>
                        <input
                            id="address"
                            placeholder={user.address}
                            value={roadAddr}
                            onChange={handleRoadAddr}
                            disabled
                            className="input-box"
                        />
                        <button onClick={openmodal} className="button1">
                            검색
                        </button>
                        <div className="label">상세주소</div>
                        <input
                            placeholder={user.addressDetail}
                            onChange={handleAddressDetail}
                            value={addressDetail}
                            className="input-box"
                        />
                        <p class="label">주문목록</p>
                        <div>
                            <table>
                                <thead>
                                    <th>주문번호</th>
                                    <th>금액</th>
                                    <th>주문명</th>
                                    <th>결제시각</th>
                                    <th>인원 수</th>
                                    <th>요청사항</th>
                                    <th>상태</th>
                                </thead>
                                <tbody>
                                    {jmlist.map((jmlist2) => {
                                        return (
                                            <tr>
                                                <td>{jmlist2.jmid}</td>
                                                <td>null</td>
                                                <td>donthave</td>
                                                <td>donothave</td>
                                                <td>{jmlist2.jmpeople}</td>
                                                <td>
                                                    {jmlist2.jmorderRequest}
                                                </td>
                                                <td>
                                                    <span>결제완료</span>
                                                    <br></br>
                                                    <button
                                                        className="delete-button"
                                                        onClick={require}
                                                    >
                                                        환불하기
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                        <div className="button-box">
                            <button onClick={Mypage} className="button3">
                                수정
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
            </S.MypageWrap>
        </>
    );
};

export default MyPage;
