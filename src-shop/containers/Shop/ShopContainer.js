import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";

import Shop from "../../components/Shop/Shop";
import { postShop } from "../../lib/Shop";

const ShopContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [shopname, setShopname] = useState("");
    const [intro, setIntro] = useState("");
    const [open_time, setOpen_time] = useState("");
    const [close_time, setClose_time] = useState("");
    const [address, setAddress] = useState("");
    const [address1, setAddress1] = useState("");
    const [is_rs_pos, setIs_rs_pos] = useState("");

    // const [modal, setModal] = useState(false);

    // const openmodal = () => {
    //     setModal(true);
    //     console.log("true");
    // };
    // const closemodal = () => {
    //     setModal(false);
    //     console.log("false");
    // };

    // const handleIs_rs_pos = (e) => {
    //     const value = e.target.value;
    //     setInputStatus(value);
    // }


    const handleId = (e) => {
        const value = e.target.value;
        setId(value);
    };

    const handleShopname = (e) => {
        const value = e.target.value;
        setShopname(value);
    };

    const handleIntro = (e) => {
        const value = e.target.value;
        setIntro(value);
    };

    const handleOpen_time = (e) => {
        const value = e.target.value;
        setOpen_time(value);
    };
    const handleClose_time = (e) => {
        const value = e.target.value;
        setClose_time(value);
    };
    const handleAddress = (e) => {
        const value = e.target.value;
        setAddress(value);
    };
    const handleAddress1 = (e) => {
        const value = e.target.value;
        setAddress1(value);
        
    };
    const handleIs_rs_pos = (e) => {
        const value = e.target.value;
        setIs_rs_pos(value);
    };

    const shop_v1 = () => {
        postShop(
            id,
            shopname,
            intro,
            open_time,
            close_time,
            address,
            address1,
            is_rs_pos
        )
            .then((res) => {
                history.push("/shoplist");
            })
            .catch((err) => {
                console.log(err);
            });
    };

    //     const Shop = () => {
    //     postShop(
    //         id,
    //         shopname,
    //         intro,y
    //         open_time,
    //         close_time,
    //         address,
    //         address1,
    //         is_rs_pos
    //     )
    //     .then((res) => {

    //         alert("등록성록");
    //         history.push("/shoplist");
    //     })
    //     .catch((err) => {
    //         alert("등록실패");
    //     });
    // };
    // const search = () => {
    //     getLocation(address)
    //        .then((res) => {})
    //        .catch((err) => {});
    // };
    return (
        <Shop
            id={id}
            handleId={handleId}
            shopname={shopname}
            handleShopname={handleShopname}
            intro={intro}
            handleIntro={handleIntro}
            open_time={open_time}
            handleOpen_time={handleOpen_time}
            close_time={close_time}
            handleClose_time={handleClose_time}
            address={address}
            handleAddress={handleAddress}
            addressDetail={address1}
            handleAddress1={handleAddress1}
            is_rs_pos={is_rs_pos}
            handleIs_rs_pos={handleIs_rs_pos}
            shop_v1={shop_v1}
            //   search={search}
            //   modal={modal}
            //   openModal={openmodal}
            //   closeModal={closemodal}
        />
    );
};

export default ShopContainer;
