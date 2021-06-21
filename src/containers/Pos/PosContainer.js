import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Pos from "../../components/Pos/Pos";
import { getShopOrder } from "../../lib/Pos/";
import { getShopInfo, gettablelength, getorderlist } from "../../lib/Pos";
import moment, { now } from "moment";
import "moment/locale/ko";
import { useInterval } from "react-use";
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import Tablebar from "../../components/Tablebar/Tablebar";
import { useParams } from "react-router";
//npm i react-use   설치하기

const PosContainer = (props) => {
    const [shopId, setShopId] = useState("");
    const [num, setNum] = useState("");
    const [shopInfo, setShopInfo] = useState({});
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [Orders, setOrders] = useState([{}]);
    const [realTime, setRealTime] = useState(Date.now());
    const [jmtable, setjmtable] = useState();
    const [ordershowlist, Setordershowlist] = useState([]);
    const [ordermenulist, Setordermenulist] = useState([]);

    const nowTime = moment().format("YYYY년MM월DD일 HH시mm분ss초");

    const [tableamount, setTableamount] = useState([]);

    const handleNum = (e) => {
        const value = e.target.value;
        setNum(value);
    };

    useInterval(() => {
        orderlist();
    }, 2000);

    const param = useParams();

    useEffect(() => {
        gettablelist();
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    useEffect(() => {
        orderlist();
    }, []);
    const gettablelist = () => {
        gettablelength(props.match.params.shopId)
            .then((res) => {
                const maptable = res.data.map((maptable) => {
                    return {
                        tableno: maptable.no,
                    };
                });
                setTableamount(maptable);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const orderlist = () => {
        getorderlist(param.shopId)
            .then((res) => {
                const pricelist = res.data.map((pricelist) => {
                    const a = pricelist.orderMenuList.map((list) => {
                        return {
                            menuName: list.menuName,
                            quantity: list.quantity,
                        };
                    });
                    if (
                        !(pricelist.table_id == null) &&
                        pricelist.status == "rd"
                    ) {
                        return {
                            amount: pricelist.amount,
                            status: pricelist.status,
                            arriveTime: pricelist.arriveTime,
                            id: pricelist.table_id.substring(10, 12),
                            menulist: a,
                        };
                    } else {
                        return {
                            amount: pricelist.amount,
                            status: pricelist.status,
                            arriveTime: pricelist.arriveTime,
                            menulist: a,
                        };
                    }
                });
                Setordershowlist(pricelist);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);
                setRoadAddr(res.data.address);
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };

    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <PosNavbar shopId={shopId} />
            <Tablebar shopId={shopId} />
            <Pos
                shopId={shopId}
                Orders={Orders}
                handleNum={handleNum}
                num={num}
                realTime={realTime}
                nowTime={nowTime}
                name={shopInfo.name}
                roadAddr={roadAddr}
                tableamount={tableamount}
                jmtable={jmtable}
                ordershowlist={ordershowlist}
                //   add_div={add_div}

                ordermenulist={ordermenulist}
            />
        </>
    );
};

export default PosContainer;
