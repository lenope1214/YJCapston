import React from "react";
import * as S from "./style";
import { Link } from "react-router-dom";

const Employee = ({
shopId,
birthday,
handlebirthday,
empName,
handleempName,
phone,
handlephone,
phone1,
handlephone1,
phone2,
handlephone2,
phone3,
handlephone3,
Emp,
gender,
handlegender,
empNo,
handleempNo,
hiredate,
handlehiredate,
hiredate1,
handlehiredate1,
hiredate2,
handlehiredate2,
hiredate3,
handlehiredate3,
}) => {

    return(
        <>
        <S.EmployeeWrap>
    <div className="emp-container">
        <div className="label-title"><h2>직원등록</h2></div>
        <div className="label">매장번호</div>
            <div>
                <input 
                    type="text"
                    id="shopid"
                    value={shopId}
                    className="input-box"
                    disabled
                />
            </div>
        <div className="label">직원번호</div>
            <div>
                <input
                    type="text"
                    length="11"
                    id="empNo"
                    value={empNo}
                    onChange={handleempNo}
                    className="input-box"
                />
            </div>
        <div className="label">이름</div>
            <div>
                <input
                    type="text"
                    id="name"
                    value={empName}
                    onChange={handleempName}
                    className="input-box"
                />
            </div>
        <div className="label">나이</div>
            <div>
                <input
                    type="date"
                    id="birthday"
                    value={birthday}
                    onChange={handlebirthday}
                    className="input-box"
                />
            </div>
        <div className="label">고용날짜</div>
            <div>
                <input
                    type="date"
                    id="hiredate"
                    onChange={handlehiredate}
                    value={hiredate}
                    className="input-box"                    
                    />
                </div>
            {/* <input
                type="text"
                id="year"
                onChange={handlehiredate1}
                value={hiredate1}
                className="input-datebox"
                />
            <span>년</span>

            <input
                type="text"
                id="month"
                onChange={handlehiredate2}
                value={hiredate2}
                className="input-datebox"
                />
            <span>월</span>
            <input
                type="text"
                id="day"
                onChange={handlehiredate3}
                value={hiredate3}
                className="input-datebox"
                />
            <span>일</span> */}
        <div className="label">전화번호</div>
            {/* <div>
                <input
                    type="text"
                    id="phone"
                    value={phone}
                    onChange={handlePhone}
                    className="input-box"
                />
            </div> */}
            <input
                type="text"
                id="phone1"
                onChange={handlephone1}
                value={phone1}
                className="input-datebox"
                />
            <span className="phonebox">-</span>

            <input
                type="text"
                id="phone2"
                onChange={handlephone2}
                value={phone2}
                className="input-datebox"
                />
            <span className="phonebox">-</span>
            <input
                type="text"
                id="phone3"
                onChange={handlephone3}
                value={phone3}
                className="input-datebox"
                />
            
            <div className="label">성별</div>
                <select
                    id="gender"
                    onChange={handlegender}
                    value={gender}
                    className="option"
                >
                    <option value="" disable={true}>선택</option>
                    <option value="M">남자</option>
                    <option value="F">여자</option>
                </select>
            <div>
            <button onClick={Emp} className="emp">등록</button>
            </div>
        </div>    
    
            
        </S.EmployeeWrap>
        </>
    );
};

export default Employee;