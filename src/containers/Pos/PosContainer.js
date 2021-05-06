import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Pos from "../../components/Pos/Pos";
import { getShopOrder} from "../../lib/Pos/";
 
const PosContainer = (props) => {
    const [shopId, setShopId] = useState("");
    const [num, setNum] = useState("");
    const [Orders, setOrders] = useState([
        {

        }
    ]);

    const date= new Date();
    const dates = date.getMonth();
    console.log(dates);

    const handleNum = (e) => {
        const value = e.target.value;
        setNum(value);
        }

    useEffect(() => {
        ShowShopOrder(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    },[]);

    const ShowShopOrder = () => {
        getShopOrder(props.match.params.shopId)
        .then((res) => {
            setOrders(res.data);
            const order = res.data.map((order) => {

                return {
                    id: order.id,
                    // no:order.no,
                    // name:order.name,

                };
            });
            setOrders(order);
            console.log(res.data);
            })
            .catch((err) => {
                alert("err");
        });
    };

    
    return (
        <>
            <Header/>
            <OwnerNavbar/>
            <Pos
              shopId={shopId}
              Orders={Orders}
              handleNum={handleNum}
              num={num}
            />
        </>
    );
};

export default PosContainer;