import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import { Doughnut } from 'react-chartjs-2';
import * as S from "./style";
import {getmonsearch,getnowsearch} from "../../lib/saleslist"
import moment from 'moment';
import 'moment/locale/ko';

const Saleslist = ({ 
        shopId,
        x ,
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
        nowDate1,daysearch,
        resultDate,resultDate2,resultDate3,resultDate4,
        RSsales,RSsales1,RSsales2
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
                data: [RSsales.sumPd, RSsales1.sumPd, RSsales2.sumPd,sales3.sumPd],
            },
        ],
    };
    


    return (
        <>
            <S.saleslistWrap>
                <span class="left-container">
                    <div class="left-head">

                        <div>
                            {/* <button onClick={beforesearch}>
                            전일</button> */}
                                <input
                                    type="date"
                                    onChange={handlenowDate}
                                    value={nowDate1}
                                />
                                <button class="day" onClick={daysearch}>해당 일 조회</button>
                            {/* <button onClick={nextsearch}>
                            후일</button> */}
                        </div>
                        
                        <div>
                            <input
                                type="date"
                                onChange={handlemonth3}
                                value={month3}
                            />
                            <button onClick={weeksearch}>해당주 매출조회하기</button>
                        </div>

                        <div>
                            <input 
                                type="date"
                                onChange={handlemonth4}
                                value={month4}
                            />
                            <button onClick={monthsearch}>해당월 매출조회하기</button>
                        </div>
                    
                        <div>
                            <input
                                type="date"
                                onChange={handlemonth}
                                value={month}
                            />~
                            <input
                                type="date"
                                onChange={handlemonth2}
                                value={month2}
                            />
                            <button class="month-search"
                                onClick={termsearch}
                        
                            >
                            기간 조회하기</button>
                        </div>

                    </div>

                    
                    

                    <div class="left-body">
                        <div>{nowDate}:일 매출</div>
                            <div>{sales.sumPd}</div>

                        <div>{nowDate}:주 매출(월요일~일요일)</div>
                            <div>{sales1.sumPd}</div> 

                        <div>{nowDate}:월 매출</div>
                            <div>{sales2.sumPd}</div>

                        <br/>

                        {/* 일매출  검색*/}
                        {/* <div>{resultDate}</div>
                            <div>{RSsales.sumPd}</div> */}

                        {/* 주매출 검색     */}
                        {/* <div>{resultDate2}</div>
                            <div>{RSsales1.sumPd}</div>  */}

                        {/* 월매출 검색     */}
                        {/* <div>{resultDate3}</div>
                            <div>{RSsales2.sumPd}</div> */}

                        {/* 기간매출 검색     */}
                        {/* <div>{resultDate4}</div>
                            <div>{sales3.sumPd}</div> */}

                        <div>{resultDate}</div>
                            <div>{pay.sumPd}</div>
                            
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
