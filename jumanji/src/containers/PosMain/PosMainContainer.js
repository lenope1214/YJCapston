import React, { useEffect, useState } from 'react';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import PosMain from "../../components/PosMain/PosMain";


const PosMainContainer = (props) => {
    const [shopId, setShopId] = useState("");

    useEffect(() => {
        setShopId(props.match.params.shopId);
    },[]);
console.log(shopId);

    return(
        <>
            <Header/>
            <OwnerNavbar/>
            <PosMain
            shopId={shopId}
            />
        </>
    );
};

export default PosMainContainer;