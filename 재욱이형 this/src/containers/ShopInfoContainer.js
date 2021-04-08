import React from "react";
import Header from "../components/Header/Header";
import OwnerNavbar from "../components/OwnerMenubar/OwnerNavbar";
import ShopInfo from "../components/ShopInfo/Shopinfo";



const MenuListContainer = () => {
    return (
        <>
        <Header />
        <OwnerNavbar />
        
        <ShopInfo />

        </>
    );
}

export default MenuListContainer;