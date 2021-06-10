import React, { useEffect, useState } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import PosMain from "../../components/PosMain/PosMain";

const PosMainContainer = (props) => {
    const [shopId, setShopId] = useState("");

    useEffect(() => {
        setShopId(props.match.params.shopId);
    }, []);
    console.log(shopId);

    const openwindow = () => {
        localStorage.setItem("token", sessionStorage.getItem("access_token"));
        window.open(
            `http://3.34.55.186:3000/Shopsocketlist/${shopId}`,
            "_blank",
            "location = no, toolbars= no, status= no, width = 500, height = 500 , scrollbars = no"
        );
    };

    return (
        <>
            <Header />
            <OwnerNavbar />
            <PosMain shopId={shopId} openwindow={openwindow} />
        </>
    );
};

export default PosMainContainer;
