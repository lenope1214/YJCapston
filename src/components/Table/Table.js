import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import {
    postorder,
    postordermenu,
    patchordermenu,
    postorder1,
    paymentdone,
} from "../../lib/Table/index";

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
        <div>
            <div>
                {" "}
                <div>{param.tableamount}번 테이블</div>
                <button onClick={plus}>+</button>
                <span>인원수 : {count}</span>
                <button onClick={minus}>-</button>
                {menu.map((menulist) => {
                    return (
                        <div>
                            <span>
                                <button
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
                                    {menulist.name}
                                </button>
                            </span>
                        </div>
                    );
                })}
            </div>
            <div class="showjm">
                <br></br>

                <div class="jmlist">주문 목록</div>
                <div class="jmcontent">
                    {jmMenu.map((jmlist) => {
                        return (
                            <div>
                                <div class="jmList_all">
                                    <span class="jmList_1">{jmlist.name}</span>
                                    <span class="jmList_3">
                                        {jmlist.count}개
                                    </span>
                                    <span class="jmList_2">
                                        {jmlist.price}원
                                    </span>

                                    <button
                                        class="jmList_4"
                                        onClick={() =>
                                            handleDeleteMenu(jmlist.name)
                                        }
                                    >
                                        삭제
                                    </button>
                                    <br></br>
                                </div>
                            </div>
                        );
                    })}
                </div>
                <div class="jmallprice">
                    <span>합계</span>
                    <div class="jmprice8">{allPrice}원</div>
                </div>

                <button
                    class="gojm"
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
                    class="gojm"
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
                    추가주문하기
                </button>
            </div>
            <div>
                <br />
                <div>이전 주문 목록</div>
                {yetjmMenu.map((yetjmMenu) => {
                    return (
                        <div>
                            메뉴이름 : {yetjmMenu.name + " "} {yetjmMenu.count}{" "}
                            가격:
                            {yetjmMenu.price + " "}
                        </div>
                    );
                })}
            </div>
            <div>
                <button
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
                <button>취소하기</button>
            </div>
        </div>
    );
};

export default Table;
