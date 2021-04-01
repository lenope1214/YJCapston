import React, { useEffect, useState } from "react";
import axios from "axios";
import MenuList from "../../components/MenuList/MenuList";
import { useHistory } from "react-router";
import { getMenuList } from "../../lib/MenuList";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";

const MenuListContainer = () => {
    const [menues, setMenues] = useState([]);

    useEffect(() => {
        // console.log(menues);
    }, []);

    useEffect(() => {
        getMenuList()
            .then((res) => {
                setMenues(res.data);
                // console.log(res.data);
                const menu = res.data.map((menu) => {
                    return {
                        id: menu.id,
                        name: menu.name,
                        intro: menu.intro,
                        price: menu.price,
                    };
                });
                setMenues(menu);
            })
            .catch((err) => {
                alert("err");
            });
    }, []);

    return (
        <>
            <Header />

            <OwnerNavbar />

            <MenuList menues={menues} />
        </>
    );
};

export default MenuListContainer;
