import React from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Event from "../../components/Event/Event"
import Header from "../../components/Header/Header";



const EventContainer = () => {
    return (
        <>
        <Header />

        <OwnerNavbar />
        
        <Event />
        </>
    );
}

export default EventContainer;