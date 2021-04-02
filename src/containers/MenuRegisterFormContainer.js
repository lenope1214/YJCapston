import React, { useState } from "react";
import MenuRegisterForm from "../components/MenuRegisterForm/MenuRegisterForm";
import OwnerNavbar from "../components/OwnerMenubar/OwnerNavbar";
import axios from "axios";
import { useHistory } from "react-router";
import Header from "../components/Header/Header";
import { apiDefault } from "../lib/client";

const MenuRegisterFormContainer = () => {
    const history = useHistory();
    const [menuname, setMenuname] = useState("");
    const [price, setPrice] = useState("");
    const [img, setImg] = useState(null);
    const [menudesc, setMenudesc] = useState("");
    const [duration, setMenuduration] = useState("");

    const handleMenuname = (e) => {
        const value = e.target.value;
        setMenuname(value);
    };

    const handlePrice = (e) => {
        const value = e.target.value;
        setPrice(value);
    };

    const handleImg = (e) => {
        const files = e.target.files[0];
        // console.log("files="+files);
        setImg(files);
    };

    const handleMenudesc = (e) => {
        const value = e.target.value;
        setMenudesc(value);
    };

    const handleDuration = (e) => {
        const value = e.target.value;
        setMenuduration(value);
    };

    const menu_v1 = async () => {
        alert("추가확인 버튼");
        const formData = new FormData();
        formData.append("img", img);
        formData.append("shopId", '0223446783');
        formData.append("name", menuname);
        formData.append("price", price);
        formData.append("intro", menudesc);
        formData.append("duration", duration);
        // console.log(formData);
        
        const res = await apiDefault().post("/menu",
        // {   
            formData,
            // name: menuname,
            // price: price,
            // intro: menudesc,
        // },
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
                "content-type": "multipart/form-data",
            },
        }
        ).then((res) => {
            history.push("/menulist");
            alert("메뉴가 추가되었습니다.");
        })
        .catch((err) => {
            alert("Err");
        });
        console.log(res);
        // postMenu(
        //     formData,     
        // )
        // .then((res) => {
        //     history.push("/menulist");
        // })
        // .catch((err) => {
        //     alert("MenuRegisterContainer Err");
        // });
        
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
            duration={duration}
            handleDuration={handleDuration}
        />
        </>
    );
}

export default MenuRegisterFormContainer;