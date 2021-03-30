import React, { useState } from "react";
import MenuRegisterForm from "../components/MenuRegisterForm/MenuRegisterForm";
import OwnerNavbar from "../components/OwnerMenubar/OwnerNavbar";
import axios from "axios";
import { useHistory } from "react-router";
import { postMenu } from "../lib/MenuRegister";
import Header from "../components/Header/Header";



const MenuRegisterFormContainer = () => {
    const history = useHistory();
    const [menuname, setMenuname] = useState("");
    const [price, setPrice] = useState("");
    const [img, setImg] = useState("");
    const [menudesc, setMenudesc] = useState("");

    const handleMenuname = (e) => {
        const value = e.target.value;
        setMenuname(value);
    };

    const handlePrice = (e) => {
        const value = e.target.value;
        setPrice(value);
    };

    const handleImg = (e) => {
        const value = e.target.value;
        setImg(value);
    };

    const handleMenudesc = (e) => {
        const value = e.target.value;
        setMenudesc(value);
    };

    const menu_v1 = () => {
        alert("추가확인 버튼");
        postMenu(
            menuname,
            price,
            img,
            menudesc,        
        )
        .then((res) => {
            history.push("/menulist");
        })
        .catch((err) => {
            console.log(err);
        });
        
    };

    return (
        <>
        <Header />
        <OwnerNavbar />
        <MenuRegisterForm 
            menuname={menuname}
            handleMenuname={handleMenuname}
            price={price}
            handlePrice={handlePrice}
            img={img}
            handleImg={handleImg}
            menudesc={menudesc}
            handleMenudesc={handleMenudesc}
            menu_v1={menu_v1}
            shopid='022344278'
        />
        </>
    );
}

export default MenuRegisterFormContainer;