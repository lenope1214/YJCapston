import React from "react";
import OwnerNavbar from "../components/OwnerMenubar/OwnerNavbar";
import Payment from "../components/Event/Payment"
import Header from "../components/Header/Header";



const EventContainer = () => {
    return (
        <>
        <Header />

        <OwnerNavbar />
        
        <Payment />
        </>
    );
}

export default EventContainer;