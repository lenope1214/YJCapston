import React, {useState, useEffect} from "react";
import Header from "../../components/Header/Header";
import Emptimelist from "../../components/Emptimelist/Emptimelist";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import Empbar from "../../components/Empbar/Empbar";
import { useHistory } from "react-router-dom";

const EmptimelistContainer = (props) => {

    const[shopId, setShopId] = useState("");

    useEffect(() => {
      
        setShopId(props.match.params.shopId);
    }, []);

    return(
        <>
            <Header/>
            <OwnerNavbar shopId={shopId}/>
            <PosNavbar shopId={shopId}/>
            <Empbar shopId={shopId}/>
            <Emptimelist
                shopId={shopId}
            />
        </>
    );
};

export default EmptimelistContainer;