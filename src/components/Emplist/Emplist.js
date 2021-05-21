import React from "react";
import EmplistContainer from "../../containers/Emplist/EmplistContainer";
import * as S from "./style";
import {removeemps} from "../../lib/Emplist/index";
import reactDom from "react-dom";
import { Link } from "react-router-dom";



const Emplist = ({
shopId,
shop,
emps,
nowTime,
removeemp,
realTime
}) => {
    
    return(
        <>
            <S.EmplistWrap>
               
        <div className="emp-container">
            <div className="emp-title"><h1>직원관리</h1></div>
            <span className="emp-title-1">직원 리스트</span>
            {/* <span className="time">{nowTime}</span> */}
            
            <table className="emp-list">
                <thead className="table-head">
                    <th className="item-1">직원번호</th>
                    <th className="item-1">이름</th>
                    <th className="item-1">생년월일</th>
                    <th className="item-1">고용날짜</th>
                    <th className="item-1">전화번호</th>
                    <th className="item-1">성별</th>
                    <th className="item-2"></th>
                    
                </thead>
                <tbody>

                    {!emps.length && (
                        <tr>
                            <td colSpan="7" className="empty-list">목록이 비어있습니다.</td>
                        </tr>
                    )}

                    {!!emps.length && emps.map((emp) => {
                        return (
                            <tr>
                                <td className="body-item-2">{emp.empNo}</td>
                                <td className="body-item-1">
                                <Link to={`/emptime/${shopId}/${emp.empNo}`} className="emp-link">
                                    {emp.empName}
                                </Link>
                                </td>
                                <td className="body-item-3">{emp.birthday}</td>
                                <td className="body-item-4">{emp.hiredate}</td>
                                <td className="body-item-5">{emp.phone}</td>
                                <td className="body-item-6">{emp.gender}</td>
                              
                                <td className="body-item-7" onClick={async () => {
                                                    const res = await removeemps(
                                                        shopId,emp.empNo
                                                    );
                                                    
                                                }}><buttom className="delete-button">삭제</buttom></td>
                   
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        
        </div>
            </S.EmplistWrap>
        </>
    );
};

export default Emplist;