import React from "react";
import EmplistContainer from "../../containers/Emplist/EmplistContainer";
import * as S from "./style";
import {removeemps} from "../../lib/Emplist/index";



const Emplist = ({
shopId,
emps,
nowTime,
removeemp,

}) => {
    
    return(
        <>
            <S.EmplistWrap>
               
        <div className="emp-container">
            <h2 className="emp-title">직원 목록</h2>
            <span>{nowTime}</span>
            <table className="emp-list">
                <thead className="table-head">
                    <th className="item-1">이름</th>
                    <th className="item-1">직원번호</th>
                    <th className="item-1">생년월일</th>
                    <th className="item-1">고용날짜</th>
                    <th className="item-1">전화번호</th>
                    <th className="item-1">성별</th>
                    
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
                                <td className="body-item-1">{emp.empName}</td>
                                <td className="body-item-2">{emp.empNo}</td>
                                <td className="body-item-3">{emp.birthday}</td>
                                <td className="body-item-4">{emp.hiredate}</td>
                                <td className="body-item-5">{emp.phone}</td>
                                <td className="body-item-6">{emp.gender}</td>
                                {/* <td className="body-item-6">{nowTime}</td> */}
                                <td className="body-item-7" onClick={async () => {
                                                    const res = await removeemps(
                                                        shopId,emp.empNo
                                                    );
                                                    
                                                }}><buttom className="delete-button">X</buttom></td>
                   
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