import React from "react";
import { requirelist } from "../../lib/MyPage";

const Shopsocketlist = ({ list }) => {
    return (
        <body>
            <div class="jmalllist">
                <p>주문 현황 목록</p>

                <div>
                    {list.map((list) => {
                        console.log(list.length);
                        return (
                            <div>
                                <div>
                                    <span>주문자 명 : </span>
                                    {list.orderid}
                                </div>
                                <div>
                                    <span>주문 번호 : </span>
                                    {list.ordernumber}
                                </div>
                                <div>
                                    <span>주문자 연락처 : </span>
                                    {list.orderphone}
                                </div>
                                <div>
                                    <span>주문 목록 : </span>
                                    {list.orderlist}
                                </div>
                                <button>수락</button>
                                <button
                                    onClick={async () => {
                                        const res = await requirelist(
                                            list.ordernumber,
                                            list.shopId
                                        ).then((res) => {
                                            alert("취소 완료");
                                            console.log(res.data);
                                        });
                                    }}
                                >
                                    거절
                                </button>
                            </div>
                        );
                    })}
                </div>
            </div>
        </body>
    );
};

export default Shopsocketlist;
