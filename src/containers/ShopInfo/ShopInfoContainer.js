
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import ShopInfo from "../../components/ShopInfo/ShopInfo";
import { getShopList, putShopInfo} from '../../lib/ShopInfo';


const ShopInfoContainer = () => {
    const history = useHistory();
    const [name, setName] = useState("");
    const [intro, setIntro] = useState("");
    const [open_time, setOpen_time] = useState("");
    const [close_time, setClose_time] = useState("");
    const [category, setCategory] = useState("");
    const [address, setAddress] = useState("");
    const [addressDetail, setAddressDetail] = useState("");
    const [isRsPos, setIsRsPos] = useState("");
    const [shop, setShop] = useState({
        id: "",
        name:"",
    });

    const handleName= (e) => {
        const value = e.target.value;
        setName(value);
    };
    const handleIntro= (e) => {
        const value = e.taeget.value;
        setIntro(value);
    }
    const handleOpen_time= (e) => {
        const value = e.target.value;
        setOpen_time(value);
    }
    const handleClose_time= (e) => {
        const value = e.target.value;
        setClose_time(value);
    }
    const handleCategory = (e) => {
        const value = e.taeget.value;
        setCategory(value);
    }
    const handleAddress = (e) => {
        const value = e.target.value;
        setAddress(value);
    }
    const handleAddressDetail = (e) => {
        const value = e.target.value;
        setAddressDetail(value);
    }
    const handleIsRsPos = (e) => {
        const value = e.target.value;
        setIsRsPos(value);
    }

    useEffect(() => {
        getShopInfo();
    },[]);

    const getShopInfo = () => {
        getShopList()
        .then((res) => {
            console.log(res.data);
            setShop(res.data);
            
        })
        .catch((err) => {
            alert("err");
        });
    };

    console.log(shop.id);
    

    return(
        <>
        <Header 
        
        />
        <OwnerNavbar 
        shopId={shop.id}
        />   
        <ShopInfo
        shopname={shop}
        handleName={handleName}
        handleIntro={handleIntro}
        handleOpen_time={handleOpen_time}
        handleClose_time={handleClose_time}
        handleCategory={handleCategory}
        handleAddress={handleAddress}
        handleAddressDetail={handleAddressDetail}
        handleIsRsPos={handleIsRsPos}
             />
        </>
    )

}


export default ShopInfoContainer;