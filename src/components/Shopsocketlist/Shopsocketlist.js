import React from "react";
import { requirelist, requirelist2 } from "../../lib/MyPage";
import * as S from "./style";
import $ from "jquery";
//npm install --save jquery 설치하기
window.$ = $;

const Shopsocketlist = ({ list }) => {
    $('.but-box').removeClass('hidden');
    $('.card').removeClass('hidden');
    // $(document).ready(function(){

    // })
    const accept = () => {
        $('.but-box').addClass('hidden');
    }
    return (
        <>
        <S.SocketListWrap>
        <body>
            <div class="jmalllist">
                <div className="titlebox">
                <p className="title">예약 현황 목록</p>
                </div>
                <div className="card-box">
                    {list.map((list) => {
                        console.log(list.length);
                        return (
                            <div className="card">
                                <p className="subtitle">예약 요청</p>
                                <hr width='95%' className="hr"></hr>
                                <div className="card-text">
                                <div className="item">
                                    <span className="label">● 아이디 &nbsp;&nbsp; : &nbsp;</span>
                                    {list.orderid}
                                </div>
                                <div className="item">
                                    <span className="label">● 주문번호 : &nbsp;</span>
                                    {list.ordernumber}
                                </div>
                                <div className="item">
                                    <span className="label">● 연락처 &nbsp;&nbsp; : &nbsp;</span>
                                    {list.orderphone}
                                </div>
                                <div className="item">
                                    <span className="label">● 예약메뉴 : &nbsp;</span>
                                    {list.orderlist}
                                </div>
                                </div>
                                <div className="but-box">

                                <button className="but1"
                                onClick={async () => {accept()}}>수락</button>

                                <button
                                    onClick={async () => {
                                        const res = await requirelist2(
                                            list.ordernumber,
                                            list.shopId
                                        ).then((res) => {
                                            alert("취소 완료");
                                            $('.card').addClass('hidden');
                                            console.log(res.data);
                                        });
                                    }}
                                    className="but2"
                                >
                                    거절
                                </button>
                                </div>
                            </div>
                        );
                    })}
                </div>
            </div>
        </body>
        </S.SocketListWrap>
        </>
    );
};

export default Shopsocketlist;