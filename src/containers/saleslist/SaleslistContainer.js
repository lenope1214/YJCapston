import React from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Saleslist from "../../components/saleslist/Saleslist";
import { useEffect, useState } from "react";
import { getshoporder } from "../../lib/saleslist/index";

const SaleslistContainer = (props) => {
    const [shopId, setShopId] = useState(props.match.params.shopId);
    const [allamount, setallmount] = useState([]);
    const [a, b] = useState([]);

    useEffect(() => {
        setShopId(props.match.params.shopId);

        shoporderlist();
    }, []);
    console.log(shopId);

    const shoporderlist = () => {
        getshoporder(shopId)
            .then((res) => {
                const sjmamount = res.data.map((sjmamount) => {
                    return {
                        jmmount: sjmamount.amount,
                    };
                });

                setallmount(sjmamount);
                console.log(sjmamount);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    console.log(allamount.length);

    let x = allamount.length;

    const test = allamount.map((test) => {
        return test.jmmount;
    });
    console.log(test);
    // b(test);
    // console.log(a);
    // const sum = allamount.reduce((a, b) => a + b);
    // console.log(sum);
    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <Saleslist shopId={shopId} x={x} />
        </>
    );
};

export default SaleslistContainer;
