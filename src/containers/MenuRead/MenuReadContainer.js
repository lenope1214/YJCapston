import React, { useEffect, useState } from 'react';
import axios from "axios";
import MenuList from '../../components/MenuList/MenuList';
import { useHistory } from 'react-router';
import { getMenuList } from '../../lib/MenuList';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import MenuRead from '../../components/MenuRead/MenuRead';
import { getMenuRead } from '../../lib/MenuRead';

const MenuReadContainer = () => {
    const [menuRead, setMenuRead] = useState([]);
    
    useEffect(() => {
        // console.log(menues);
    }, []);

    useEffect(() => {
        getMenuRead()
            .then((res) => {              
                setMenuRead(res.data);
                // console.log(res.data);
                // console.log(menuRead);
            })
            .catch((err) => {
                alert("err");
            });
    });

    return (
        <>
        <Header />

       
        
        <MenuRead menuRead={menuRead}/>

        </>
    );

}

export default MenuReadContainer;