import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import { Doughnut } from 'react-chartjs-2';
import * as S from "./style";
import { getmonsearch, getnowsearch } from "../../lib/saleslist"
import moment from 'moment';
import 'moment/locale/ko';

const Saleslist = ({
    shopId,
    x,
    name,
    month,
    handlemonth,
    termsearch,
    nowdate,
    deleteSymbols,
    pay,
    nextsearch,
    month2,
    handlemonth2,
    month3,
    handlemonth3,
    month4,
    handlemonth4,
    monthsearch,
    weeksearch,
    sales,
    sales1,
    sales2,
    sales3,
    nowDate,
    beforesearch,
    handlenowDate,
    nowDate1, daysearch,
    resultDate, resultDate2, resultDate3, resultDate4,
    RSsales, RSsales1, RSsales2
}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    });


    const data = {
        labels: [
            "해당 일",
            "해당 주",
            "해당 월",
            "해당 기간",
        ],
        datasets: [
            {
                label: "매출 금액",
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 1,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: [RSsales.sumPd, RSsales1.sumPd, RSsales2.sumPd, sales3.sumPd],
            },
        ],
    };



    return (
        <>
            <S.saleslistWrap>
                <span class="left-container">
                    <div class="left-head">
                        <div className="search-box">
                            <div className="but-box">
                                {/* <button onClick={beforesearch}>
                            전일</button> */}
                                <input
                                    type="date"
                                    onChange={handlenowDate}
                                    value={nowDate1}
                                    className="input-box"
                                /><br/>
                                <button class="day" onClick={daysearch} className="button">날짜 조회</button>
                                {/* <button onClick={nextsearch}>
                            후일</button> */}
                            </div>
                            <hr width='530px' />
                            <div className="but-box">
                                <input
                                    type="date"
                                    onChange={handlemonth3}
                                    value={month3}
                                    className="input-box"

                                /><br/>
                                <button onClick={weeksearch} className="button">주간 매출 조회</button>
                            </div>
                            <hr width='530px' />
                            <div className="but-box">
                                <input
                                    type="date"
                                    onChange={handlemonth4}
                                    value={month4}
                                    className="input-box"
                                /><br/>
                                <button onClick={monthsearch} className="button">월간 매출 조회</button>
                            </div>
                            <hr width='530px' />
                            <div className="but-box">
                                <input
                                    type="date"
                                    onChange={handlemonth}
                                    value={month}
                                    className="input-box"
                                />~
                                <input
                                    type="date"
                                    onChange={handlemonth2}
                                    value={month2}
                                    className="input-box"
                                /><br/>
                                <button class="month-search"
                                    onClick={termsearch}
                                    className="button"
                                >
                                    기간 조회</button>
                            </div>
                            <hr width='530px' />
                        </div>
                    </div>
                    <div class="left-body">
                        <div className="sub-title">조회 매출</div>
                        <div className="result-box">
                        <div className="type">{resultDate}
                        <hr width='180px' /></div>
                        
                        <div className="money">{pay.sumPd}</div>
                        </div>
                    </div>

                </span>
                <span class="right-container">
                    <div class="shopname">{name}</div>
                    <div class="chart">
                        <Bar
                            data={data}
                            width={90}
                            height={90}
                            options={{
                                maintainAspectRatio: false,
                            }}
                        />

                    </div>
                </span>
            </S.saleslistWrap>
        </>
    );
};

export default Saleslist;
