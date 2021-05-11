import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import * as S from "./style";

const Emptimelist = ({
    shopId,
    emps,
}) => {

    return (
        <>
            <S.EmptimelistWrap>
                <div className="emptimelist-container">
                    <span className="title1">wodnrd</span>
                    <span className="title2">wodnr</span>
                    <span className="title3">wodnr</span>
                </div>

                <table className="emptime-list">
                    <thead className="table-head">
                        <th className="item-1">이름</th>
                        <th className="item-1">날짜</th>
                        <th className="item-1">시간</th>
                    </thead>
                    <tbody>

                        {!emps.length && (
                            <tr>
                                <td colSpan="3" className="empty-list">목록이 비어있습니다.</td>
                            </tr>
                        )}

                        {!emps.length && emps.map((emp) => {
                            return(
                                <tr>
                                    <td className="body=item-1"></td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </S.EmptimelistWrap>

        </>
    );
};

export default Emptimelist;