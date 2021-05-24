import React, { useState, useEffect } from "react";
import MenuRegisterForm from "../components/MenuRegisterForm/MenuRegisterForm";
import OwnerNavbar from "../components/OwnerMenubar/OwnerNavbar";
import { useHistory } from "react-router";
import Header from "../components/Header/Header";
import { apiDefault } from "../lib/client";

const MenuRegisterFormContainer = (props) => {
    const history = useHistory();
    const [menuname, setMenuname] = useState("");
    const [price, setPrice] = useState("");
    const [img, setImg] = useState(null);
    const [menudesc, setMenudesc] = useState("");
    const [duration, setMenuduration] = useState("");
    const [shopId, setShopId] = useState("");

    useEffect(()=> {
        setShopId(props.match.params.shopId);
    })

    function isName(menuname) {
        let menunameRegExp = /^[가-힣]{2,20}$/;
        return menunameRegExp.test(menuname);
    }

    function isPrice(price) {
        let priceRegExp = /^[0-9]{2,6}$/;
        return priceRegExp.test(price);
    }

    function isDuration(duration) {
        let durationRegExp = /^[0-9]{1,3}$/;
        return durationRegExp.test(duration);
    }

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
        if(!isName(menuname)) {
            return alert(
                "메뉴명은 한글 2~20자리로 입력해야 합니다."
            );
        }
        if(!isPrice(price)) {
            return alert(
                "가격은 숫자 2~6자리로 입력해야 합니다."
            );
        }

        if(!isDuration(duration)) {
            return alert(
                "예상 소요시간은 숫자 1~3자리로 입력해야 합니다."
            );
        }


        const formData = new FormData();
        formData.append("img", img);
        formData.append("shopId", shopId);
        formData.append("name", menuname);
        formData.append("price", price);
        formData.append("intro", menudesc);
        formData.append("duration", duration);
        
        const res = await apiDefault().post("/menu",
            formData,
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
                "content-type": "multipart/form-data",
            },
        }
        ).then((res) => {
            history.goBack();
            alert("메뉴가 추가되었습니다.");
        })
        .catch((err) => {
            alert("메뉴 추가 실패!");
        });
        console.log(res);
    };

    return (
        <>
        <Header />
        <hr />
        {/* <OwnerNavbar /> */}
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
            shopId={shopId}
            duration={duration}
            handleDuration={handleDuration}
        />
        </>
    );
}

export default MenuRegisterFormContainer;