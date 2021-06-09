import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import * as S from "./style";

const Saleslist = ({ shopId, x }) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    });

    const data = {
        labels: [
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "July",
            "July",
        ],
        datasets: [
            {
                label: "월 매출",
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 1,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: [x, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4],
            },
        ],
    };

    return (
        <>
            <S.saleslistWrap>
                <div>가격</div>
                <div class="abc">
                    <Bar
                        data={data}
                        width={90}
                        height={90}
                        options={{
                            maintainAspectRatio: false,
                        }}
                    />
                </div>
            </S.saleslistWrap>
        </>
    );
};

export default Saleslist;
