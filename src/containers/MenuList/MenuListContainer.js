import React, { useEffect, useState } from 'react';
import axios from "axios";
import MenuList from '../../components/MenuList/MenuList';
import { useHistory } from 'react-router';
import { getMenuList, removeMenu } from '../../lib/MenuList';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import { apiDefault } from "../../lib/client";
import { removeMenues } from "../../lib/MenuList/index"

const MenuListContainer = () => {
    const [menues, setMenues] = useState([]);
    const history = useHistory();

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
                console.log("MeLiCon에서 menu : "+ menu);
                console.log("MeLiCon에서 menues : "+ menues);
            })
            .catch((err) => {
                
                alert("MenuListContainer Err");
            });
    }, []);

    const removeMenu = (id) => {
        removeMenues(id)
            .then((res) => {
                alert("삭제되었습니다.");
                history.push("/menulist");
                window.location.reload();
            })
            .catch((err) => {
                alert("메뉴 삭제 에러");
            });
    };
    
    return (
        <>
        <Header />

        <OwnerNavbar />
        
        <MenuList 
            menues={menues}
            removeMenu={removeMenu}
        />
        </>
    );
};

export default MenuListContainer;

