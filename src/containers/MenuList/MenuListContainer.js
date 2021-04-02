import React, { useEffect, useState } from 'react';
import axios from "axios";
import MenuList from '../../components/MenuList/MenuList';
import { useHistory } from 'react-router';
import { getMenuList } from '../../lib/MenuList';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";

const MenuListContainer = () => {
    const [menues, setMenues] = useState([]);

    useEffect(() => {
        console.log(menues);
    }, []);

    useEffect(() => {
        getMenuList()
            .then((res) => {
                
                setMenues(res.data);
                console.log("res.data 출력 - "+res.data);
                const menu = res.data.map((menu) => {
                    return {
                        imgUrl: menu.img,
                        id: menu.id,
                        name: menu.name,
                        intro: menu.intro,
                        price: menu.price,
                    };
                });
                setMenues(menu);
                console.log("MeLiCon에서 menu : "+menu);
                console.log("MeLiCon에서 menues : "+menues);
            })
            .catch((err) => {
                
                alert("MenuListContainer Err");
            });
    }, []);
    
    return (
        <>
        <Header />

        <OwnerNavbar />
        
        <MenuList menues={menues}/>
        </>
    );
}

export default MenuListContainer;

