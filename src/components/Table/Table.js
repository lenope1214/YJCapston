import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import {
    postorder,
    postordermenu,
    patchordermenu,
    postorder1,
    paymentdone,
} from "../../lib/Table/index";
import * as S from "./style";

const Table = ({
    menu,
    handleMenu,
    jmMenu,
    handleDeleteMenu,
    reload,
    plus,
    minus,
    count,
    patchordertable,
    tablepeople,
    yetjmMenu,
    orderId,
}) => {
    let HOUSE_BASE_URL = "http://122.202.45.37:8088/";
    let SCHOOL_BASE_URL = "http://192.168.1.17:8088/";
    let AWS_BASE_URL = "http://3.34.55.186:8088/";
    let SCHOOL_BASE_URL2 = "http://192.168.0.24:8088/";

    const param = useParams();
    const history = useHistory();

    const [allPrice, setAllPrice] = useState();

    useEffect(() => {
        let a = jmMenu.reduce((prev, curr) => {
            return prev + curr.price * curr.count;
        }, 0);
        setAllPrice(a);
    }, [jmMenu]);
    return (
        <S.tableWrap>
            <div className="all-container">
                <div className="left-container">
                    <div className="left-1">
                        <br></br>
                        <div class="left-title1">주문 목록</div>
                        <div class="left-content1">
                            <table>
                                <thead>
                                    <th>메뉴</th>
                                    <th>수량</th>
                                    <th>가격</th>
                                    <th></th>
                                </thead>
                                <tbody>

                                    {jmMenu.map((jmlist) => {
                                        return (
                                            <tr>
                                                <td>{jmlist.name}</td>
                                                <td>
                                                    {jmlist.count}
                                                </td>
                                                <td>
                                                    {jmlist.price}원
                                                </td>
                                                <td>
                                                    <button
                                                        class="del-but"
                                                        onClick={() =>
                                                            handleDeleteMenu(jmlist.name)
                                                        }
                                                    >
                                                        삭제
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>

                        </div>
                        <div class="total-box">
                            <div class="total-price">Total : {allPrice}원</div>
                        </div>
                        <div className="button-box">
                            <button
                                class="button1"
                                onClick={async () => {
                                    const res = await postorder1(
                                        param.shopId,
                                        param.tableamount,
                                        count,
                                        allPrice
                                    )
                                        .then((res) => {
                                            const postlist2 = jmMenu.map((menu) => {
                                                return {
                                                    orderId: res.data.orderId,
                                                    shopId: param.shopId,
                                                    quantity: menu.count,
                                                    menuId: menu.menuId,
                                                    tabNo: param.tableamount,
                                                };
                                            });

                                            if (!(res.data.orderId == null)) {
                                                postordermenu(postlist2).then((res) => {
                                                    history.push(`/pos/${param.shopId}`);
                                                });
                                                // async () => {
                                                //     const res = await postordermenu(
                                                //         postlist
                                                //     ).then((res) => {
                                                //         console.log(res);
                                                //         history.push(`/pos/${param.shopId}`);
                                                //     });
                                                // };
                                            }
                                        })
                                        .catch((err) => {
                                            console.log(err);
                                        });
                                }}
                            >
                                주문하기
                            </button>
                            <button
                                class="button2"
                                onClick={async () => {
                                    const res = await postorder(
                                        param.shopId,
                                        param.tableamount,
                                        count,
                                        allPrice,
                                        orderId
                                    )
                                        .then((res) => {
                                            const postlist2 = jmMenu.map((menu) => {
                                                return {
                                                    orderId: res.data.orderId,
                                                    shopId: param.shopId,
                                                    quantity: menu.count,
                                                    menuId: menu.menuId,
                                                    tabNo: param.tableamount,
                                                };
                                            });

                                            if (!(res.data.orderId == null)) {
                                                postordermenu(postlist2).then((res) => {
                                                    history.push(`/pos/${param.shopId}`);
                                                });
                                                // async () => {
                                                //     const res = await postordermenu(
                                                //         postlist
                                                //     ).then((res) => {
                                                //         console.log(res);
                                                //         history.push(`/pos/${param.shopId}`);
                                                //     });
                                                // };
                                            }
                                        })
                                        .catch((err) => {
                                            console.log(err);
                                        });
                                }}
                            >
                                추가 주문하기
                            </button>
                        </div>
                    </div>
                    <div className="left-2">
                        <br />
                        <div className="left-title2">이전 주문 목록</div>
                        <div className="left-content2">
                            <table>
                                <thead>
                                    <th>메뉴</th>
                                    <th>수량</th>
                                    <th>가격</th>
                                </thead>
                                <tbody>
                                    {yetjmMenu.map((yetjmMenu) => {
                                        return (
                                            <tr>
                                                <td>
                                                    {yetjmMenu.name}
                                                </td>
                                                <td>
                                                    {yetjmMenu.count}
                                                </td>
                                                <td>
                                                    {yetjmMenu.price}
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="button-box">
                        <button
                            className="button3"
                            onClick={async () => {
                                const res = await paymentdone(orderId)
                                    .then((res) => {
                                        history.push(`/pos/${param.shopId}`);
                                    })
                                    .catch((err) => {
                                        console.log(err);
                                    });
                            }}
                        >
                            결제하기
                        </button>
                        {/* <button >취소하기</button> */}
                    </div>
                </div>
                <div className="right-container">
                    {" "}
                    <div className="table-num">{param.tableamount}번 테이블</div>
                    <div className="people-num">
                        <button onClick={minus}>-</button>
                        <span>인원수 : {count}</span>
                        <button onClick={plus}>+</button>
                    </div>
                    <div className="item-box">
                    {menu.map((menulist) => {
                        return (
                            <div className="item">
                                    <button
                                     className="item"
                                        onClick={() =>
                                            handleMenu(
                                                menulist.name,
                                                menulist.price,
                                                menulist.menuId
                                            )
                                        }
                                    >
                                        <img
                                            width=" 100px"
                                            src={AWS_BASE_URL + menulist.img}
                                        />
                                        <br/>
                                        {menulist.name}
                                    </button>
                            </div>
                        );
                    })}
                    </div>
                </div>
            </div>
        </S.tableWrap>
    );
};

export default Table;
