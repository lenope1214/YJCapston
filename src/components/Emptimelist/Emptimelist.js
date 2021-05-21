import React from 'react';
import {Link} from "react-router-dom";
import * as S from "./style";
import {timelist} from "../../lib/Emptimelist/index";

const Emptimelist = ({
    shopId,
    emps,
    empName,
    empNo,
    handleEmpNo,
    date,
    handleDate,
    startTime,
    timelist,
}) => {

    return (
        <>
            <S.EmptimelistWrap>
                
                <div className="emptimelist-container">
                <div className="title">직원 근무시간 리스트</div>
                    <span className="title1">어제/오늘/내일</span>
                    {/* <span className="title2"> */}
                        <input
                            className="date"
                            id="date"
                            type="date"
                            value={date}
                            onChange={handleDate}
                            
                        />

                    {/* </span> */}
                    <span className="title3">직원검색
                    <select className="option"
                            id="empNo"
                            value={empNo}
                            onChange={handleEmpNo}
                    >
                       <option value="" disable={true}>선택</option>
                    {!!emps.length && emps.map((emp) => {
                        return (
                            
                            <option>{emp.empNo}</option>
                        )
                    })}
                    
                    </select>
                    
                    </span>
                    <button onClick={timelist}>조회</button>
                </div>
                

            <div className="emptime-table">

                <table className="emptime-list">
                    <thead className="table-head">
                        <th className="item-1">이름</th>
                        <th className="item-2">날짜</th>
                        <th className="item-3">출근시간</th>
                        <th className="item-4">퇴근시간</th>
                    </thead>
                    
                    <tbody>

                        {!!timelist.length && timelist.map((time) => {
                            return(

                        <tr>
                            <td className="body-item-1">{time.time.Name}</td>
                            <td className="body-item-2">d</td>
                            <td className="body-item-3"></td>
                        </tr>
                        )
                    })}
                    </tbody>
                </table>

            </div>    
            </S.EmptimelistWrap>

        </>
    );
};

export default Emptimelist;